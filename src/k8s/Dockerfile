# Build stage
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the POM file first to leverage Docker cache for dependencies
COPY pom.xml .
# Download dependencies (separate layer to leverage cache)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# Create a non-root user to run the application
RUN addgroup --system --gid 1001 appuser && \
    adduser --system --uid 1001 --gid 1001 appuser

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Set ownership of the application files
RUN chown -R appuser:appuser /app

# Use the non-root user
USER appuser

# Expose the application port (adjust if your app uses a different port)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
