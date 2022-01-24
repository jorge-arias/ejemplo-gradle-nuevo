/*
	forma de invocación de método call:
	def ejecucion = load 'maven.groovy'
	ejecucion.call()
*/

def call(){

	stage('Compile') {
		sh 'mvn clean compile -e'
	}

	stage('Test') {
		sh 'mvn clean test -e'
	}

	stage('Package') {
		sh 'mvn clean package -e'
	}

	stage('Sonar') {
		def scannerHome = tool 'sonar-scanner';
		withSonarQubeEnv('sonar-server') {
			sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-maven -Dsonar.sources=src -Dsonar.java.binaries=build"
		}
	}

	stage('uploadNexus') {
		nexusArtifactUploader artifacts: [[artifactId: 'DevOpsUsach2020', classifier: '', file: '/diplomado/modulo3/ejemplo-maven/build/DevOpsUsach2020-0.0.1.jar', type: 'jar']], credentialsId: 'nexus-taller10', groupId: 'com.devopsusach2020', nexusUrl: 'f5dd-200-126-115-114.ngrok.io', nexusVersion: 'nexus3', protocol: 'http', repository: 'test-nexus', version: '0.0.1'
	}
}

return this;