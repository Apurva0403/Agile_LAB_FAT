pipeline {
    agent any
    
    environment {
        GITHUB_REPO = 'https://github.com/Apurva0403/Agile_LAB_FAT.git'
        DOCKER_HUB_USERNAME = 'apurva0409'
        DOCKER_HUB_PASSWORD = credentials('docker-hub-password')
        DOCKER_IMAGE_NAME = "${DOCKER_HUB_USERNAME}/matrix-multiplication"
        DOCKER_IMAGE_TAG = "${BUILD_NUMBER}"
    }
    
    stages {
        stage('Checkout GitHub') {
            steps {
                echo '========== STAGE: Checkout GitHub =========='
                echo "Repository: ${GITHUB_REPO}"
                git branch: 'main',
                    url: 'https://github.com/Apurva0403/Agile_LAB_FAT.git'
                echo 'Code checked out successfully!'
            }
        }
        
        stage('Build with Maven') {
            steps {
                echo '========== STAGE: Build with Maven =========='
                bat 'mvn clean package -DskipTests'
                echo 'Build completed successfully!'
            }
        }
        
        stage('Test with JUnit') {
            steps {
                echo '========== STAGE: Test with JUnit =========='
                bat 'mvn test'
                echo 'Tests completed successfully!'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Create Docker Image') {
            steps {
                echo '========== STAGE: Create Docker Image =========='
                script {
                    bat '''
                        docker build -t %DOCKER_IMAGE_NAME%:%DOCKER_IMAGE_TAG% .
                        docker tag %DOCKER_IMAGE_NAME%:%DOCKER_IMAGE_TAG% %DOCKER_IMAGE_NAME%:latest
                        echo Docker image created: %DOCKER_IMAGE_NAME%:%DOCKER_IMAGE_TAG%
                    '''
                }
                echo 'Docker image created successfully!'
            }
        }
        
        stage('Push to Docker Hub') {
            steps {
                echo '========== STAGE: Push to Docker Hub =========='
                script {
                    bat '''
                        echo %DOCKER_HUB_PASSWORD% | docker login -u %DOCKER_HUB_USERNAME% --password-stdin
                        docker push %DOCKER_IMAGE_NAME%:%DOCKER_IMAGE_TAG%
                        docker push %DOCKER_IMAGE_NAME%:latest
                        docker logout
                        echo Docker image pushed successfully!
                    '''
                }
                echo 'Push to Docker Hub completed successfully!'
            }
        }
    }
    
    post {
        success {
            echo '✓ Pipeline executed successfully!'
        }
        failure {
            echo '✗ Pipeline failed!'
        }
    }
}
