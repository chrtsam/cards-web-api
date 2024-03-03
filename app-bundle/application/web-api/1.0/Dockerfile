FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /opt

COPY ./pom.xml .
COPY ./src ./src
RUN mvn clean package

FROM openjdk:21-slim
WORKDIR /opt
COPY --from=build /opt/target/api-1.0.jar .
CMD ["java", "-jar", "api-1.0.jar"]