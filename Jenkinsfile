properties([pipelineTriggers([githubPush()])])
node{
    git branch: 'ahmedCICD', credentialsId: 'MyGitCred', url: 'https://github.com/AhmedGrati/BackEndCovoiturage'
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
        def downCommand = "sudo docker-compose -f /home/ubuntu/wasalni-docker/docker-compose.yml down"
        def upCommand = "sudo docker-compose -f /home/ubuntu/wasalni-docker/docker-compose.yml up -d"
        sh "${downCommand} && ${upCommand}"
    }

}