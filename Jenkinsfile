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

        stage('API Test') {
            steps {
                bat 'curl -X POST http://localhost:8080/validatelogsapi/logs/analyze -H "Content-Type: text/plain" -d "2024-12-21T10:00:00 ERROR Something broke"'
            }
        }

        stage('File Upload Test') {
            steps {
                bat 'curl -X POST http://localhost:8080/validatelogsapi/logs/upload -F "file=@logs.txt"'
            }
        }
    }
}