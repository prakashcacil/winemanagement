pipeline {
  agent any

  stages {
   stage('Git pull') {
    steps {
           // Get some code from a GitHub repository
           git branch: 'main', url: 'https://github.com/prakashcacil/winemanagement.git'
           }
   }
    stage('Maven install') {

      steps {
        // Run Maven on a Unix agent.
     
          sh "mvn -Dmaven.test.failure.ignore=true package"
      }

      post {
        success {
          junit '**/target/surefire-reports/TEST-*.xml'
          archiveArtifacts 'target/*.jar'
        }
      }
    }

    stage('Docker Build') {
      steps {
        sh 'docker build -t winemanagement:latest .'
      }
    }
  }
}
