FROM openjdk:21-jre

WORKDIR /app

COPY target/bookstore-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port your Spring Boot application runs on (default 8080)
EXPOSE 8080

# Step 5: Specify the command to run the application
CMD ["java", "-jar", "app.jar"]
