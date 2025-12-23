pipeline {
    agent any

    environment {
        DOCKER_HUB = credentials('docker-hub-credentials')
        SERVICES = "order notification processing"
    }

    stages {
        stage("Build & Push Docker") {
            steps {
                script {
                    sh "echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin"

                    def serviceList = SERVICES.split(' ')
                    for (service in serviceList) {
                        def imageName = "${DOCKER_HUB_USR}/${service}:latest"
                        sh "docker build -t ${imageName} ./${service}"
                        sh "docker push ${imageName}"
                    }
                }
            }
        }

        stage("Deploy to K8s") {
            steps {
                sh "chmod +x deploy.sh"
                sh "./deploy.sh"
            }
        }
    }
}