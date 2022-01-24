pipeline {
    agent any
	
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
}
