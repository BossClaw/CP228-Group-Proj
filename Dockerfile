FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/pixel-vault-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=qa", "-jar", "app.jar"]