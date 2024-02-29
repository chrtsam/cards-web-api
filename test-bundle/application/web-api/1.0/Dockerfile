# Use an official Maven image as the base image
FROM eclipse-temurin:21-jdk-alpine AS build
# Set the working directory in the container
WORKDIR /opt
# Copy the pom.xml and the project files to the container
COPY pom.xml ./..
COPY src ./../src
# Build the application using Maven
RUN mvn clean package -DskipTests
# Use an official OpenJDK image as the base image
FROM openjdk:21-slim
# Set the working directory in the container
WORKDIR /opt
# Copy the built JAR file from the previous stage to the container
COPY - from=build /opt/target/api-1.0.jar .
# Set the command to run the application
CMD ["java", "-jar", "api-1.0.jar"]