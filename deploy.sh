#!/bin/bash



echo "===== 5) Deploying Kafka ====="
kubectl apply -f k8s/kafka.yaml


echo "===== 6) Deploying Microservices ====="

echo "Deploying ORDER service..."
kubectl apply -f k8s/order-service.yaml

echo "Deploying PROCESSING service..."
kubectl apply -f k8s/processing-service.yaml

echo "Deploying NOTIFICATION service..."
kubectl apply -f k8s/notification-service.yaml


echo "===== 7) Deploying Ingress Controller ====="
kubectl apply -f k8s/ingress.yaml


echo "===== 8) Checking Kubernetes Resources ====="

echo "Pods:"
kubectl get pods -o wide

echo "Services:"
kubectl get svc

echo "Deployments:"
kubectl get deployments

echo "Ingress:"
kubectl get ingress

echo "===== Deployment finished successfully! 🚀 ====="
