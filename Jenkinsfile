pipeline {
    agent any

    environment {
        REGISTRY = "your_dockerhub_username"
        IMAGE_NAME = "order-service"
    }

    stages {

        stage("Checkout") {
            steps {
                git branch: 'main', url: 'https://github.com/yourrepo.git'
            }
        }

        stage("Build Maven") {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage("Build Docker Image") {
            steps {
                sh "docker build -t $REGISTRY/$IMAGE_NAME:latest ."
            }
        }

        stage("Push to Docker Hub") {
            steps {
                sh "docker login -u $REGISTRY -p $DOCKER_HUB_PASSWORD"
                sh "docker push $REGISTRY/$IMAGE_NAME:latest"
            }
        }

        stage("Deploy to Kubernetes") {
            steps {
                sh "chmod +x deploy.sh"
                sh "./deploy.sh"
            }
        }
    }
}
