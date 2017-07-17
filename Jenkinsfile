pipeline {
  agent any
  
  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build') {
      steps {
        def mvnHome = tool 'M3'
        sh '${mvnHome}/bin/mvn package'
      }
    }
  }
}
