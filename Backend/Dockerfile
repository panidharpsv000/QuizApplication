# backend/Dockerfile

# Step 1: Build JAR
FROM maven:3.9.6-eclipse-temurin-17 AS build


WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Run the JAR
FROM openjdk:17

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
