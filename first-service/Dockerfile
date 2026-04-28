FROM openjdk:27-ea-jdk-slim
VOLUME /tmp
COPY target/first-service-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]