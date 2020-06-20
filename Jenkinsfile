pipeline{

   agent any
   stages{
         stage('SCM Checkout'){
                steps {
                    git branch: 'master', credentialsId: 'MyGitCred', url: 'https://github.com/AhmedGrati/BackEndCovoiturage'
                }
            }
            stage('Stop Server Container'){
                steps{
                    script{

                        def downCommand = "sudo docker-compose -f /home/ubuntu/wassalni/wasalni-docker/docker-compose.yml down"
                        sh "cd /home/ubuntu && pwd && sudo docker-compose -f /home/ubuntu/wassalni/wasalni-docker/docker-compose.yml down"
                    }
                }
            }
            stage('Mvn Package'){
                steps{
                    script {
                        sh "mvn  clean -Dmaven.test.skip=true package"
                    }
                }
            }
            stage('Build Docker Image') {
                steps{
                    script {
                        sh 'sudo -n docker build -t wassalni/wassalnibackend:1.0.0 .'
                    }
                }
            }
            stage('Push Docker Image') {
                steps {
                    script {
                        withCredentials([string(credentialsId: 'docker-new-pwd', variable: 'dockerHubNewPwd')]) {

                            sh "sudo -n docker login -u wassalni -p ${dockerHubNewPwd}"

                        }

                        sh 'sudo -n docker push wassalni/wassalnibackend:1.0.0'
                    }

                }
            }
            stage('Run Container On dev Server'){
                steps {
                    script {
                         def upCommand = "sudo docker-compose -f /home/ubuntu/wassalni/wasalni-docker/docker-compose.yml up -d"
                         sh "cd /home/ubuntu && pwd && sudo docker-compose -f /home/ubuntu/wassalni/wasalni-docker/docker-compose.yml up -d"
                    }

                }
            }
   }
    post {
        success {
              emailext (
                  from:"wassalni.tech@gmail.com",
                  to: "ahmedgrati1999@gamil.com",
                  subject: "Build Log ",
                  body: "The build was successful  and your product is on now . Check it out on http://3.84.152.145:8080/",
                  recipientProviders: [[$class: 'DevelopersRecipientProvider']]
                )
        }
        failure {
              emailext (
                  from:"wassalni.tech@gmail.com",
                  to: "ahmedgrati1999@gamil.com",
                  attachLog:true,
                  subject: "Build Log !",
                  body: "The build failed and your product is not on production now . To Debug it check out the last build on http://3.84.152.145:9090/job/WassalniCICD",
                  recipientProviders: [[$class: 'DevelopersRecipientProvider']]
                )
        }
    }

}


