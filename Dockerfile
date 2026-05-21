# ---- Build Stage ----
FROM eclipse-temurin:25-jdk-alpine AS build

WORKDIR /app

# Copy Maven wrapper and pom first (better layer caching)
COPY mvnw mvnw.cmd pom.xml ./
COPY .mvn .mvn

# Download dependencies (cached unless pom.xml changes)
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN ./mvnw package -DskipTests -q

# ---- Runtime Stage ----
FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

# Copy the built jar from the build stage (wildcard for flexibility)
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 9090

# Run the application with memory limit for free tier safety (256MB max)
ENTRYPOINT ["java", "-Xmx256m", "-jar", "app.jar"]
