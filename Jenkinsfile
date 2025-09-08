pipeline {
    agent any

    tools {
        jdk 'javac 21.0.8'
        maven 'Maven 3.9.11'
    }

    environment {
        REPORT_DIR = 'test-output/ExtentReports'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Report') {
            steps {
                archiveArtifacts artifacts: "${REPORT_DIR}/**", allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
    }
}