# TrackFlow – Job Application & Interview Tracker

TrackFlow is a full-stack web application designed to help job seekers organize and manage their job search journey in one place.

It allows users to manage job applications, track their progress, and organize interview details through a centralized platform.

## 🚀 Features

- **User Authentication:** User registration and login with Spring Security.
- **Job Application Management:** Add, view, update, and delete job applications.
- **Interview Management:** Organize and manage interview information.
- **Dashboard:** Access and manage job-search information from one place.
- **Database Integration:** Store application and interview information in a MySQL-compatible cloud database.
- **Responsive Interface:** Built using Bootstrap to support different screen sizes.

## 🛠️ Technologies Used

### Frontend
- HTML5
- CSS3
- JavaScript
- Bootstrap 5.3

### Backend
- Java 25
- Spring Boot 4.0.0
- Spring Security
- Spring Data JPA
- Hibernate
- Maven

### Database
- MySQL
- TiDB Cloud

### Development & Deployment Tools
- IntelliJ IDEA
- MySQL Workbench
- Git & GitHub
- Docker

## 🏗️ Application Architecture

TrackFlow follows a client-server architecture.

```text
        User
         |
         v
  Frontend (HTML, CSS,
  JavaScript, Bootstrap)
         |
         v
   Spring Boot Backend
         |
    +----+-----+
    |          |
    v          v
Spring      Spring Data
Security       JPA
    |          |
    +----+-----+
         |
         v
    TiDB Cloud
  (MySQL Compatible)
```

## ⚙️ Getting Started

Follow these instructions to run TrackFlow locally.

### Prerequisites

Install the following before starting:

- Java JDK 25
- Maven (or use the included Maven Wrapper)
- MySQL database or a compatible TiDB Cloud database
- Git

### 1. Clone the Repository

```bash
git clone https://github.com/GoutamNaik-dev/TrackFlow.git
```

### 2. Navigate to the Project

```bash
cd TrackFlow
```

### 3. Configure Database Connection

Open:

`src/main/resources/application.properties`

Configure the following environment variables:

| Variable | Description |
|---|---|
| DB_URL | JDBC database connection URL |
| DB_USERNAME | Database username |
| DB_PASSWORD | Database password |

Example configuration:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/trackflow_db}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=${PORT:8080}
```

Set the environment variables according to your database configuration. Never commit actual database credentials to GitHub.

### 4. Build the Application

On Windows:

```bash
mvnw.cmd clean package -DskipTests
```

On Linux or macOS:

```bash
./mvnw clean package -DskipTests
```

### 5. Run the Application

```bash
java -jar target/trackflow-0.0.1-SNAPSHOT.jar
```

Open your browser:

http://localhost:8080

## 🐳 Running with Docker

TrackFlow can also run inside a Docker container.

### 1. Build Docker Image

```bash
docker build -t trackflow:latest .
```

### 2. Configure Database Environment Variables

Set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` in your terminal.

### 3. Run Docker Container

```bash
docker run -d \
  --name trackflow-app \
  -p 8080:8080 \
  -e DB_URL \
  -e DB_USERNAME \
  -e DB_PASSWORD \
  trackflow:latest
```

### 4. Verify Container

```bash
docker ps
```

### 5. View Application Logs

```bash
docker logs trackflow-app
```

### 6. Stop the Container

```bash
docker stop trackflow-app
```

Open the application at:

http://localhost:8080

## 📸 Screenshots

Screenshots of the TrackFlow application will be added here.

### Login Page
![TrackFlow Login](Screenshots\loginPage.png)

### Registration Page
![TrackFlow Login](Screenshots\RegistrationPage.png)

### Dashboard
![TrackFlow Dashboard](Screenshots\DashboardPage.png)

### Job Applications
![TrackFlow Applications](Screenshots\MyApplicationPage.png)

### Interview Management
![TrackFlow Interviews](Screenshots\InterviewPage.png)

## 🔮 Future Improvements

- Advanced job application analytics and statistics.
- Job application reminders and notifications.
- Improved search and filtering capabilities.
- Resume management and integration.
- AI-powered job application insights.

## 👨‍💻 Author

**Goutam Naik**

GitHub: [GoutamNaik-dev](https://github.com/GoutamNaik-dev)

---

If you find this project interesting, feel free to explore the repository!

**Built with Java, Spring Boot and a passion for software development.**