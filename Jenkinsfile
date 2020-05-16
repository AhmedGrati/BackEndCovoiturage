node{
    stage('SCM Checkout'){
        git branch: 'ahmedCICD', credentialsId: '688dd2c1-4277-4d21-99fd-7e9c70ed32bf', url: 'https://github.com/AhmedGrati/BackEndCovoiturage'
    }
    stage('Mvn Package'){
        "jenkinsMaven.sh"
    }
    stage('Build Docker Image') {
        sh 'docker build -t wassalni/wassalnibackend:1.0.0 .'
    }
    stage('Push Docker Image') {
        withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerHubPwd')]) {
            sh "docker login -u wassalni -p ${dockerHubPwd}"
        }
        sh 'docker push wassalni/wassalnibackend:1.0.0'
    }
    stage('Run Container On dev Server'){
        //def dockerRun = 'docker run -p 8080:8080 -d --name wassalnibackend wassalni/wassalnibackend:1.0.0'${dockerRun}
          sh 'chmod 400 EC2WassalniInstance.pem'
          sh 'ssh -o StrictHostKeyChecking=no -i "EC2WassalniInstance.pem" ubuntu@ec2-54-174-148-166.compute-1.amazonaws.com'
          sh 'docker run -p 8085:8085 -d --name wassalnibackend wassalni/wassalnibackend:1.0.0'

    }

}