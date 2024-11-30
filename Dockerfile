FROM openjdk:21-jre-slim

WORKDIR /app

COPY target/classes.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
