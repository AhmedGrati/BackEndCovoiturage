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
                        sh "cd && pwd && ${downCommand}"
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
                        sh "pwd"
                         def upCommand = "sudo docker-compose -f /home/ubuntu/wassalni/wasalni-docker/docker-compose.yml up -d"
                         sh "cd && pwd && ${upCommand}"
                    }

                }
            }
   }
}


