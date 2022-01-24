/*
	forma de invocación de método call:
	def ejecucion = load 'gradle.groovy'
	ejecucion.call()
*/

def call(){
  
          stage('Build & Unit Test') {
			println "Stage: ${env.STAGE_NAME}"
			sh "gradle build"
        }
		
        stage('Sonar') {
			println "Stage: ${env.STAGE_NAME}"
			def scannerHome = tool 'sonar-scanner';
			withSonarQubeEnv('sonar-server') {
				sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle-staller12 -Dsonar.sources=src -Dsonar.java.binaries=build"
			}
        }
        
        stage('Run') {
			println "Stage: ${env.STAGE_NAME}"
			sh "gradle bootRun &"
			sleep 20
        }

		stage('Test') {
			println "Stage: ${env.STAGE_NAME}"
			sh "curl -X GET 'http://localhost:8082/rest/mscovid/test?msg=testing'"
        }

		stage('nexus') {
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

return this;