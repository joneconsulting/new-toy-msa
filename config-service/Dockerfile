FROM openjdk:21-ea-jdk-slim

WORKDIR /app
VOLUME /tmp

COPY ./target/config-service-1.0.jar config-service.jar
COPY ./apiEncryptionKey.jks apiEncryptionKey.jks

ENTRYPOINT ["java", "-jar", "config-service.jar"]