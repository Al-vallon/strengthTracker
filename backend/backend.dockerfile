# Build stage
FROM maven:3.9.9-amazoncorretto-21-alpine AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

# Run stage
FROM amazoncorretto:21-alpine
WORKDIR /app
COPY --from=build /app/target/strengthTracker-0.0.1-SNAPSHOT.jar app.jar
COPY .env .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]