# Build the Spring Boot application inside Docker so Maven/Gradle are not required on the host.
FROM gradle:8.14.4-jdk21 AS build
WORKDIR /workspace
COPY build.gradle settings.gradle ./
RUN gradle dependencies --no-daemon
COPY src ./src
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/build/libs/quiz-platform-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
