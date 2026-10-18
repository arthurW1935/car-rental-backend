#!/usr/bin/env bash
# Install Argo CD into the kind cluster and register the car-rental Application.
set -euo pipefail
ARGOCD_CHART_VERSION=10.10.5
cd "$(dirname "$0")/.."

helm repo add argo https://argoproj.github.io/argo-helm >/dev/null
helm repo update argo >/dev/null
helm upgrade --install argocd argo/argo-cd --version "$ARGOCD_CHART_VERSION" \
  -n argocd --create-namespace -f argocd/values.yaml --wait --timeout 10m

kubectl apply -f argocd/application.yaml

echo "Argo CD UI: https://localhost:8443  (user: admin)"
echo "Password:   kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath='{.data.password}' | base64 -d"
