pipeline {
    agent any
	
	environment {
	    STAGE = ''
	}
	
	parameters {
		choice choices: ['gradle', 'maven'], description: 'Indique la herramienta de construcción', name: 'buildTool'
	}

    stages {
        stage('Pipeline') {
            steps {
                println "Pipeline"
				script{				
					if (params.buildTool == 'gradle'){
						def ejecucion = load 'gradle.groovy'
						ejecucion.call()
					}
					else{
						def ejecucion = load 'maven.groovy'
						ejecucion.call()
					}
				}
            }
        }
    }
	post {
        failure {
            slackSend color: '#FF0000', message: "Jorge Arias | ${env.JOB_NAME} | ${env.buildTool} | Ejecución fallida en el stage: ${env.STAGE}")
        }
		success{
			slackSend color: '#00FF00', message: "Jorge Arias | ${env.JOB_NAME} | ${env.buildTool} | Ejecución exitosa")
		}
    }
}
