pipeline {
    agent any

    stages {
        stage('Build & Unit Test') {
            steps {
                println "Stage: ${env.STAGE_NAME}"
				sh "gradle build"
            }
        }
		
        stage('Sonar') {
            steps {
                println "Stage: ${env.STAGE_NAME}"
				script{
					def scannerHome = tool 'sonar-scanner';
					withSonarQubeEnv('sonar-server') {
						sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle-staller12 -Dsonar.sources=src -Dsonar.java.binaries=build"
					}
				}
            }
        }
        
        stage('Run') {
            steps {
                println "Stage: ${env.STAGE_NAME}"
				sh "gradle bootRun &"
				sleep 30
            }
        }

		stage('Test') {
			steps {
				println "Stage: ${env.STAGE_NAME}"
				sh "curl -X GET 'http://localhost:8082/rest/mscovid/test?msg=testing'"
			}
        }

		stage('nexus') {
            steps {
                nexusPublisher nexusInstanceId: 'nexus-server',
                nexusRepositoryId: 'test-nexus',
                packages: [
                    [
                        $class: 'MavenPackage',
                        mavenAssetList: [
                            [classifier: '', extension: '', filePath: "${env.WORKSPACE}/build/libs/DevOpsUsach2020-0.0.1.jar"]
                        ],
                        mavenCoordinate: [
                            artifactId: 'DevOpsUsach2020',
                            groupId: 'com.devopsusach2020',
                            packaging: 'jar',
                            version: '0.0.1'
                        ]
                    ]
                ]
            }
        }
    }
}