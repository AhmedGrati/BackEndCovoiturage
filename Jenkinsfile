node{
    stage('SCM Checkout'){
        git branch: 'ahmedCICD', credentialsId: 'MyGitCred', url: 'https://github.com/AhmedGrati/BackEndCovoiturage'
    }
    stage('Mvn Package'){
        "jenkinsMaven.sh"
    }
    stage('Build Docker Image') {
        sh 'sudo docker build -t wassalni/wassalnibackend:1.0.0 .'
    }
    stage('Push Docker Image') {
        withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerHubPwd')]) {
            sh "sudo docker login -u wassalni -p ${dockerHubPwd}"
        }
        sh 'sudo docker push wassalni/wassalnibackend:1.0.0'
    }
    stage('Run Container On dev Server'){
        def redirectionCommand = "sudo cd /home/ubuntu/wasalni-docker"
        def downCommand = "sudo docker-compose -d down"
        def upCommand = "sudo docker-compose -d up"
        sh "${redirectionCommand} && ${downCommand} && ${upCommand}"
    }

}