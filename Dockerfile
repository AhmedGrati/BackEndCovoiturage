FROM openjdk:14-oracle
COPY target/BackEndCovoiturage-0.0.1-SNAPSHOT.jar  /project
CMD java -jar /project/BackEndCovoiturage-0.0.1-SNAPSHOT.jar