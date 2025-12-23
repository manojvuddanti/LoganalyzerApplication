pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t loganalyzer .'
            }
        }

        stage('Stop Old Container') {
            steps {
                bat 'docker stop loganalyzer-container || exit 0'
                bat 'docker rm loganalyzer-container || exit 0'
            }
        }

        stage('Run New Container') {
            steps {
                bat 'docker run -d -p 8080:8080 --name loganalyzer-container loganalyzer'
            }
        }
    }
}