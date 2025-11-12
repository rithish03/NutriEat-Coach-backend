# ===== Stage 1: Build the app =====
FROM maven:3.9.6-eclipse-temurin-21 AS build
# Sets the working directory
WORKDIR /app

# Copy pom.xml and download dependencies (cached)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Stage 2: Run the app =====
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the built JAR from previous stage
COPY --from=build /app/target/SpringAIProject-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Use environment variables defined in Render or docker-compose
ENTRYPOINT ["java", "-jar", "app.jar"]
