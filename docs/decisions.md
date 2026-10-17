# Decisions and trade-offs

| Area | Choice | Why | Alternative and when it would be better |
|---|---|---|---|
| Cluster | kind (1 control plane + 2 workers) | Free, runs on Docker, multi-node so spreading/PDBs mean something | EKS/GKE when real users, LoadBalancers and managed storage are needed |
| Packaging | Helm chart | One templated chart, environment differences in one small values file | Kustomize if no templating logic is needed |
| App workload | Deployment, 2 replicas | Stateless; any pod can serve any request | HPA on top once metrics-server and real load exist |
| DB workload | StatefulSet + PVC, 1 replica | Needs stable identity and its own disk | Managed DB (RDS/Cloud SQL) or an operator with replication for HA |
| Exposure | NodePort mapped to localhost | kind has no cloud load balancer | Ingress/Gateway API + TLS in a real cluster |
| CI | GitHub Actions | Code is on GitHub, free, no server to run | Jenkins/GitLab CI if self-hosted is required |
| CD | Argo CD (pull-based GitOps) | GitHub cannot reach a laptop cluster; git becomes the record of what runs; drift is auto-reverted | Push-based `helm upgrade` from CI for a reachable cluster with no GitOps need |
| Approval | GitHub Environment with required reviewer on the tag-bump job | Everything is automatic up to "release"; a human decides when users get it | Fully automatic deploy when test coverage and rollback are trusted |
| Release strategy | Rolling update, `maxSurge 1`, `maxUnavailable 0` | Zero downtime, simplest to reason about with 2 replicas | Canary (Argo Rollouts) when traffic is high enough for metrics to judge a small slice |
| Rollback | `git revert` of the deploy commit | Same audited path as any change; Argo re-syncs the old SHA | `argocd app rollback` in an emergency (pauses auto-sync) |
| Version traceability | Image tag = full git SHA, shown in `/actuator/info`, pod label and dashboard | Any running pod maps to one commit | SemVer tags in addition, for human-friendly releases |
| Secrets | Created in-cluster by scripts, never in git | No credentials in the repo or history | Sealed Secrets / External Secrets Operator for GitOps-managed secrets |
| Security checks | gitleaks, Trivy (fail on fixable HIGH/CRITICAL), SHA-pinned actions, PSA `restricted`, NetworkPolicies | Covers source, dependencies, image, CI supply chain and runtime | Image signing (cosign) + admission policy (Kyverno) as the next step |
| Metrics | Micrometer → Prometheus, kube-prometheus-stack | Standard Kubernetes stack, Spring support built in | Managed Prometheus in the cloud |
| Tracing | OpenTelemetry (OTLP) → Jaeger v2 | Vendor-neutral standard; JDBC spans show DB time | Grafana Tempo if already on the Grafana stack |
| Logs | stdout → Grafana Alloy → Loki (single binary, filesystem, 72h) | Search across pods and releases in Grafana; logs survive pod restarts | Loki simple-scalable mode on object storage (S3/GCS) for production volume |
| Log collector | Alloy as one Deployment tailing logs through the Kubernetes API | No hostPath mounts or privileged DaemonSet; Promtail is end-of-life | DaemonSet reading `/var/log/pods` on each node for high log volume (the API path doesn't scale to big clusters) |
| Logs ↔ traces | `trace_id` kept as Loki structured metadata; Grafana links log → trace and span → logs | One click from an error line to its trace and back; trace ids are unique, so labels would explode Loki's index | Exemplars on metrics for metric → trace |
| Tests in CI | H2 in MySQL mode | Fast, no DB service | Testcontainers MySQL for exact production parity |
| Alert delivery | Alertmanager → webhook receiver in the cluster; routes by `service` + `severity`; resolved messages on | No external account needed; same routing as a real setup | Slack (warning) + PagerDuty (critical) receivers; only the receiver URLs change |
| Alert noise | Inhibit symptom alerts while MySQL is down (`for` ordering + `keep_firing_for`); kind-only clock alerts disabled | One outage = one page, about the cause | Per-team routing trees and silences during maintenance |
| SLO | 99% non-5xx, p95 < 500 ms, health checks excluded | Reflects what users feel | Multi-window burn-rate alerts with more traffic |
