# Quiz Platform — Spring Boot + Docker PostgreSQL

A simple college-project quiz platform for **teachers and students**.

## Features
- Teacher creates quizzes with multiple-choice questions.
- Student sees all available quizzes and attempts them.
- Automatic score calculation.
- PostgreSQL database runs entirely in Docker.
- Spring Boot application also runs in Docker.
- No Maven or Gradle installation is required on the host computer.

## Requirements
Install **Docker Desktop**. Docker Desktop includes Docker Compose.

## Run — Windows PowerShell / CMD
Open a terminal in this project folder:

```powershell
docker compose up --build -d
```

Then open:

```text
http://localhost:8080
```

Check containers:

```powershell
docker compose ps
```

View application logs:

```powershell
docker compose logs -f app
```

Stop containers:

```powershell
docker compose down
```

Stop containers and delete the PostgreSQL data volume too:

```powershell
docker compose down -v
```

## Demo users
- Teacher: `teacher` / `teacher123`
- Student: `student` / `student123`

The current demo UI does not require a login screen; the teacher username is used when creating a quiz. The seeded users are included so the database already contains teacher/student records for future authentication work.

## Project structure
```text
quiz-platform/
├── compose.yaml
├── Dockerfile
├── build.gradle
├── settings.gradle
├── README.md
└── src/
    └── main/
        ├── java/com/example/quizplatform/
        │   ├── QuizPlatformApplication.java
        │   ├── config/DataSeeder.java
        │   ├── controller/QuizController.java
        │   ├── model/
        │   ├── repository/
        │   └── service/QuizService.java
        └── resources/
            ├── application.properties
            └── static/index.html
```

## Important
The build tool is **Gradle inside the Docker build container**. You do not need to install Maven or Gradle on your PC. Docker Compose builds the application image and starts both the app and PostgreSQL services.
