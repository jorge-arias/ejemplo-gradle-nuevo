pipeline {
    agent any

    stages {
        stage('Build & Unit Test') {
            steps {
                println "Stage: ${env.STAGE_NAME}"
            }
        }
		
        stage('Sonar') {
            steps {
                println "Stage: ${env.STAGE_NAME}"
            }
        }
        
        stage('Run') {
            steps {
                println "Stage: ${env.STAGE_NAME}"
            }
        }

		stage('Test') {
			steps {
				println "Stage: ${env.STAGE_NAME}"
			}
        }

		stage('Nexus') {
			steps {
				println "Stage: ${env.STAGE_NAME}"
			}
		}
    }
}