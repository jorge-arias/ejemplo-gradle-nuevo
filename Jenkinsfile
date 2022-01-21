pipeline {
    agent any

    stages {
        stage('Build & Unit Test') {
            steps {
                println "Stage: ${env.STAGE_NAME}"
				gradle build
            }
        }
		
        stage('Sonar') {
            steps {
                println "Stage: ${env.STAGE_NAME}"
				script{
					def scannerHome = tool 'sonar-scanner';
					withSonarQubeEnv('sonar-server') {
						sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-taller12 -Dsonar.sources=src -Dsonar.java.binaries=build"
					}
				}
            }
        }
        
        stage('Run') {
            steps {
                println "Stage: ${env.STAGE_NAME}"
				gradle bootRun &
            }
        }

		stage('Test') {
			steps {
				println "Stage: ${env.STAGE_NAME}"
				curl -X GET 'http://localhost:8082/rest/mscovid/test?msg=testing'
			}
        }

		stage('Nexus') {
			steps {
				println "Stage: ${env.STAGE_NAME}"
			}
		}
    }
}