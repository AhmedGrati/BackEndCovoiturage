node{
    stage('SCM Checkout'){
        git branch: 'ahmedCICD', credentialsId: '688dd2c1-4277-4d21-99fd-7e9c70ed32bf', url: 'https://github.com/AhmedGrati/BackEndCovoiturage'
    }
    stage('Mvn Package'){
        sh "jenkinsMaven.sh"
    }
}