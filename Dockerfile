FROM maven:3.6.3-openjdk-14-slim as MAVEN_BUILD
WORKDIR /tmp/project
CMD [ "bash" , "/scripts/back.sh" ]