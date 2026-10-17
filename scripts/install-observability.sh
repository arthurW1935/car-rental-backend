#!/usr/bin/env bash
# Install the monitoring stack (Prometheus, Alertmanager, Grafana), Loki + Alloy and Jaeger into kind.
set -euo pipefail
KPS_CHART_VERSION=92.10.1
LOKI_CHART_VERSION=7.3.0
ALLOY_CHART_VERSION=1.13.1
NS=monitoring
cd "$(dirname "$0")/.."

kubectl create namespace "$NS" --dry-run=client -o yaml | kubectl apply -f -
if ! kubectl -n "$NS" get secret grafana-admin >/dev/null 2>&1; then
  kubectl -n "$NS" create secret generic grafana-admin \
    --from-literal=admin-user=admin --from-literal=admin-password="$(openssl rand -hex 12)"
fi

helm repo add prometheus-community https://prometheus-community.github.io/helm-charts >/dev/null
helm repo add grafana https://grafana.github.io/helm-charts >/dev/null
helm repo update prometheus-community grafana >/dev/null

# Loki first: Grafana's Loki datasource and Alloy both point at it.
helm upgrade --install loki grafana/loki --version "$LOKI_CHART_VERSION" \
  -n "$NS" -f observability/loki-values.yaml --wait --timeout 10m
helm upgrade --install alloy grafana/alloy --version "$ALLOY_CHART_VERSION" \
  -n "$NS" -f observability/alloy-values.yaml --wait --timeout 5m

helm upgrade --install monitoring prometheus-community/kube-prometheus-stack \
  --version "$KPS_CHART_VERSION" -n "$NS" -f observability/kube-prometheus-stack-values.yaml \
  --wait --timeout 10m

kubectl apply -f observability/jaeger.yaml -f observability/alert-receiver.yaml
kubectl -n "$NS" rollout status deploy/jaeger --timeout=3m
kubectl -n "$NS" rollout status deploy/alert-receiver --timeout=3m

echo "Grafana: http://localhost:3000  (user: admin)"
echo "Password: kubectl -n $NS get secret grafana-admin -o jsonpath='{.data.admin-password}' | base64 -d"
echo "Logs:     Grafana -> Explore -> Loki, {app=\"car-rental-backend\"}"
echo "Alerts:   kubectl -n $NS logs deploy/alert-receiver -f"
echo "Jaeger:   kubectl -n $NS port-forward svc/jaeger 16686  -> http://localhost:16686"
