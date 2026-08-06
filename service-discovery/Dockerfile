# Build stage
FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /app
VOLUME /tmp

COPY pom.xml ./pom.xml
COPY src ./src

RUN mvn clean compile package -DskipTests=true

# Run stage
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar service-discovery.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app/service-discovery.jar"]