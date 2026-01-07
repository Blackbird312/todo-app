FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom first (for dependency caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Run tests + package
RUN mvn clean test package


FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy compiled jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Run the app
CMD ["java", "-jar", "app.jar"]
