FROM openjdk:17.0-slim
COPY target/*.jar demo.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/demo.jar"]