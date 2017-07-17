pipeline {
  agent any
  
  stages {
    stage('Checkout') {
      checkout scm
    }

    stage('Build') {
      def mvnHome = tool 'M3'
      sh '${mvnHome}/bin/mvn package'
    }
  }
}
