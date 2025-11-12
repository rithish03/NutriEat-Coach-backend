# Stage 1: Build the JAR using Java 21
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app
COPY . .

# ✅ Give execute permission to mvnw
RUN chmod +x mvnw

# Build the project
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the JAR
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
