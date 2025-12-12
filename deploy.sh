#!/bin/bash

echo "===== 1) Building Docker images ====="

echo "Building ORDER image..."
docker build -t order:latest ./order

echo "Building PROCESSING image..."
docker build -t processing:latest ./processing

echo "Building NOTIFICATION image..."
docker build -t notification:latest ./notification


echo "===== 2) Tagging images (for Docker Hub) ====="

docker tag order:latest ghada477/order:latest
docker tag processing:latest ghada477/processing:latest
docker tag notification:latest ghada477/notification:latest


echo "===== 3) Pushing images to Docker Hub ====="

docker push ghada477/order:latest
docker push ghada477/processing:latest
docker push ghada477/notification:latest


echo "===== 4) Deploying Kubernetes Configurations ====="



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
