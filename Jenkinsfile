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
                        sh "${downCommand}"
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
            stage('Run Container On dev Server'){
                steps {
                    script {
                         def upCommand = "sudo docker-compose -f /home/ubuntu/wassalni/wasalni-docker/docker-compose.yml up -d"
                         sh "${upCommand}"
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


