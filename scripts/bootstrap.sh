#!/usr/bin/env bash
# One-time cluster setup that must NOT live in git: namespace + DB credentials.
# Generates random passwords; re-running keeps the existing Secret.
set -euo pipefail
NS=car-rental

kubectl create namespace "$NS" --dry-run=client -o yaml | kubectl apply -f -
# Pod Security Admission: reject pods that run as root, privileged, etc.
kubectl label namespace "$NS" pod-security.kubernetes.io/enforce=restricted --overwrite

if ! kubectl -n "$NS" get secret car-rental-db >/dev/null 2>&1; then
  kubectl -n "$NS" create secret generic car-rental-db \
    --from-literal=mysql-root-password="$(openssl rand -hex 16)" \
    --from-literal=mysql-password="$(openssl rand -hex 16)"
  echo "Created secret $NS/car-rental-db"
else
  echo "Secret $NS/car-rental-db already exists, leaving it"
fi
