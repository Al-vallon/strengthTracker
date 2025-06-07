# Build stage
FROM maven:latest AS build

WORKDIR /app
# Copy from backend directory since Docker context is at project root
COPY backend/pom.xml .
COPY backend/src ./src
RUN mvn package -DskipTests

# Run stage
FROM openjdk:21-jdk
WORKDIR /app
COPY --from=build /app/target/strengthTracker-0.0.1-SNAPSHOT.jar app.jar

#COPY .env .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]