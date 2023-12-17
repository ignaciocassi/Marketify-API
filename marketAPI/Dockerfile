FROM openjdk:11-jre-slim

COPY target/*.jar marketapi.jar

EXPOSE 8090

ENTRYPOINT ["java","-jar","marketapi.jar"]