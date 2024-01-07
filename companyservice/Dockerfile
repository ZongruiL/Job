
FROM openjdk:17.0-slim
COPY target/*.jar demo.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/demo.jar"]