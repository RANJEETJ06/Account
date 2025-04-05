#base image that is java libraries
FROM openjdk:17-jdk-slim

MAINTAINER RanjeetJena

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#this command java -jar accounts-0.0.1-SNAPSHOT.jar will use to run manually on cmd
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]
