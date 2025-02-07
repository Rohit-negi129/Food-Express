FROM openjdk:11-jre-slim
WORKDIR usr/src
ADD ./target/UserAuthenticationService-0.0.1-SNAPSHOT.jar /usr/src/UserAuthenticationService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","UserAuthenticationService-0.0.1-SNAPSHOT.jar"]