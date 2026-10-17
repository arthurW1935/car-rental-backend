# Operations and evidence

All output below is from this project's kind cluster, captured on 2026-10-17.

## Health: what "healthy" means here

- Argo CD application `car-rental` is **Synced / Healthy**, and its sync revision is the PR head commit.
- 2/2 app pods are ready and 1/1 MySQL is ready (`kubectl -n car-rental get pods`).
- `scripts/user-journey.sh` prints `JOURNEY OK`. That means a real user can register, add a vehicle, get a quote, book, and see the booking.
- Dashboard: availability ≥ 99% and p95 < 500 ms. None of the `CarRental*` alerts is firing.

## Fault demo 1: MySQL pod lost

- **Symptom expected:** requests that need the DB fail while MySQL restarts.
- **Hypothesis:** the StatefulSet recreates `car-rental-mysql-0` on the same PVC, so no data is lost. App pods should not restart, because liveness does not depend on the DB.
- **Evidence:**

```
== 07:53:11 before: journey
JOURNEY OK
== 07:53:12 fault: delete MySQL pod
pod "car-rental-mysql-0" deleted from car-rental namespace
07:53:12 mysql_ready=true app_ready=[true true ] GET /customers -> 200
07:53:18 mysql_ready=true app_ready=[true true ] GET /customers -> 000
07:53:24 mysql_ready=false app_ready=[true true ] GET /customers -> 200
07:53:27 mysql_ready=true app_ready=[true true ] GET /customers -> 200
== 07:53:27 after: journey
JOURNEY OK
restarts: 0 0 
```

- **Result:** one request failed (it timed out during roughly 6 s of DB downtime). Then everything recovered by itself with **0 app restarts**, and data created before the fault was still there (the journey reads existing rows).
- **Why readiness did not flip:** the outage (about 6 s) was shorter than readiness failure detection (3 checks × 5 s = 15 s). In a longer outage the pods go not-ready, the Service stops routing to them, and `CarRentalNoReadyPods` and `CarRentalDatabaseDown` fire.
- **What I'd change:** for more availability, run MySQL with a replica (operator or managed DB). Tuning readiness to react faster trades that for more flapping.

## Fault demo 2: configuration drift

```
$ kubectl -n car-rental scale deploy car-rental-app --replicas=1
Argo CD reverted to replicas=2
```

Self-heal is on, so a manual change in the cluster is overwritten by what git says.

## Fault demo 3: alerting, from outage to page to resolved

Alertmanager routes alerts carrying `team=car-rental` to the `car-rental-critical` and `car-rental-warning` receivers (split by severity). Everything else goes to `platform`. The receiver is a webhook running in the cluster; to see what it got, run `kubectl -n monitoring logs deploy/alert-receiver`. In a real team, these would be PagerDuty and Slack.

Drill: take MySQL down. The goal is **one page about the root cause, not a storm of symptom alerts**. Argo CD auto-sync was paused during the drill so self-heal wouldn't undo the outage.

```
== 09:07:09 FAULT: scale MySQL StatefulSet to 0
== 09:08:46 CarRentalDatabaseDown delivered
== 09:09:37 CarRentalNoReadyPods is active in Prometheus but suppressed in Alertmanager
== 09:09:37 user impact: GET /customers -> 000
   CarRentalNoReadyPods  team=car-rental  severity=critical  state=suppressed
   CarRentalDatabaseDown  team=car-rental  severity=critical  state=active
== 09:09:37 RECOVER: scale MySQL back to 1
== 09:09:46 app pods ready 2/2
== 09:12:49 CarRentalDatabaseDown resolved (kept firing 2m after recovery)
== 09:14:19 journey: JOURNEY OK
== 09:14:19 CarRental alerts still active: 0

What the on-call channel received:
[car-rental-critical] FIRING   critical CarRentalDatabaseDown | MySQL is not ready | runbook: kubectl -n car-rental logs car-rental-mysql-0; ...
[car-rental-critical] RESOLVED critical CarRentalDatabaseDown | MySQL is not ready | ...
```

Getting to one page took three fixes, each found by running the drill:

1. **Ordering race.** `NoReadyPods` and `DatabaseDown` both had `for: 1m`, so they became active in the same evaluation and the symptom was sent before the inhibition applied. Fix: give the symptom a longer `for` (2m) than the cause (1m).
2. **Recovery tail.** MySQL-down resolved before Prometheus saw the app pods ready again (scrape plus evaluation lag). That lifted the inhibition and the symptom fired after the outage was over. Fix: `keep_firing_for: 2m` on `DatabaseDown`. The cost is that "resolved" arrives about 2 minutes late.
3. **Label collision.** Routing on `service=car-rental` overwrote the `service` label that kube-state-metrics alerts already carry, and it was fragile. Fix: a dedicated `team` label.

The kind-only `NodeClockNotSynchronising` and `NodeClockSkewDetected` rules are disabled. kind nodes share the Docker VM clock, so these fire permanently and only teach people to ignore alerts.

## Rollback procedure (bad release)

1. Find the deploy commit: `git log --oneline -- deploy/values-kind.yaml`
2. `git revert <that commit> && git push`. The `deploy/**` path does not trigger CI.
3. Argo CD syncs the previous image tag within about 60 s. The rolling update keeps the old pods serving until new ones are ready.
4. Verify: `curl localhost:8080/actuator/info` shows the old SHA, and `scripts/user-journey.sh` prints `JOURNEY OK`.

If a release never becomes ready (crashing image), the rollout stalls with the old pods still serving, because of `maxUnavailable: 0`. Users are not affected, and the same revert fixes it.

## Security checks in place

| Layer | Control | Evidence |
|---|---|---|
| Source | gitleaks on every push/PR | CI job "Secret scan (gitleaks)" |
| Dependencies + image | Trivy, fails on fixable HIGH/CRITICAL | CI job "Image build, scan…"; accepted CVEs documented in `.trivyignore` |
| CI supply chain | Actions pinned to commit SHAs; token read-only by default | `.github/workflows/ci.yml` |
| Delivery | Manual approval before deploy; only `devops`/`main` may deploy | GitHub environment `kind` |
| Secrets | Generated in-cluster, never in git | `scripts/bootstrap.sh`, `scripts/install-observability.sh` |
| Runtime | PSA `restricted`, non-root, read-only root filesystem, no capabilities, no SA token | namespace label, chart `securityContext` |
| Network | Default-deny ingress; MySQL reachable only from app pods | Test: a non-app pod connecting to MySQL → `BLOCKED` |
