FROM openjdk:17.0-slim
COPY target/*.jar demo.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "/demo.jar"]

#RUN docker build --platform linux/amd64 -t liuzongrui1999/gateway-ms:ec2 .