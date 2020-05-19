node{
    stage('SCM Checkout'){
        git branch: 'ahmedCICD', credentialsId: 'MyGitCred', url: 'https://github.com/AhmedGrati/BackEndCovoiturage'
    }
    stage('Mvn Package'){
        "jenkinsMaven.sh"
    }
    stage('Build Docker Image') {
        sh 'sudo -n docker build -t wassalni/wassalnibackend:1.0.0 .'
    }
    stage('Push Docker Image') {
        withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerHubPwd')]) {
            sh "sudo -n docker login -u wassalni -p ${dockerHubPwd}"
        }
        sh 'sudo -n docker push wassalni/wassalnibackend:1.0.0'
    }
    stage('Run Container On dev Server'){
        def redirectionCommand = "sudo -n sh -c 'cd /home/ubuntu/wasalni-docker'"
        def downCommand = "sudo -n docker-compose down"
        def upCommand = "sudo -n docker-compose up -d"
        sh "${redirectionCommand} && ${downCommand} && ${upCommand}"
    }

}