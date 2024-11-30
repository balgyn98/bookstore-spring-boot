# Base image with Java runtime
FROM eclipse-temurin:21-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file from the main module
COPY bookstore-main/target/bookstore-*.jar app.jar

# Expose the application port (e.g., 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
