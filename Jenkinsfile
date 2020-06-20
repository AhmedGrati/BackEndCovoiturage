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
                        sh "cd /home/ubuntu/images && ls && sudo docker-compose -f /home/ubuntu/wassalni/wasalni-docker/docker-compose.yml down"
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
                  subject: "Build Log ",
                  body: "The build was successful  and your product is on now . Check it out on http://3.84.152.145:8080/",
                  recipientProviders: [[$class: 'DevelopersRecipientProvider']]
                )
        }
        failure {
              emailext (
                  attachLog:true,
                  subject: "Build Log !",
                  body: "The build failed and your product is not on production now . To Debug it check out the last build on http://3.84.152.145:9090/job/WassalniCICD",
                  recipientProviders: [[$class: 'DevelopersRecipientProvider']]
                )
        }
    }

}


