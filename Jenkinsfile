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
        def redirectionCommand = "sudo cd wasalni-docker"
        def downCommand = "sudo docker-compose -d down"
        def upCommand = "sudo docker-compose -d up"
        sh 'chmod 400 WassalniNewAWS.pem'
        sh "ssh -o StrictHostKeyChecking=no -i 'WassalniNewAWS.pem' ubuntu@ec2-3-84-152-145.compute-1.amazonaws.com && ${redirectionCommand} && ${downCommand} && ${upCommand}"
    }

}