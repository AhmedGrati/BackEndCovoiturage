FROM openjdk:14-jdk-alpine
EXPOSE 8085
ADD  target/*.jar app.jar
ADD *.jks ./
ENTRYPOINT ["java","-jar","/app.jar"]