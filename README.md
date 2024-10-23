# Job Portal Web Application

A comprehensive **Job Portal Web Application** built with **Spring Boot** allowing both Recruiters and JobSeekers to hire talents and apply to the desired job roles.

## Features

- **Job Listings**: Allows recruiters to post jobs and job seekers to search for available jobs.
- **User Roles**: Supports two types of users: Job Seekers and Recruiters.
- **User Authentication**: Secure login and registration for both job seekers and recruiters.
- **Profile Management**: Job seekers can create profiles, upload resumes, and recruiters can view applicants.
- **Job Application**: Job seekers can apply to job postings directly through the portal.
- **Responsive Design**: Optimized for both desktop and mobile views.

## Technologies Used

- **Spring Boot**: For backend development and RESTful API.
- **Spring Security**: To handle authentication and authorization.
- **Thymeleaf**: As the templating engine for server-side rendered pages.
- **JPA (Hibernate)**: For data persistence and interaction with the database.
- **MySQL**: As the relational database to store user, job, and application data.
- **Bootstrap**: For frontend styling and responsive design.
- **Maven**: For project build and dependency management.

## Prerequisites

- **Java**: Version 8 or above.
- **Maven**: Installed and properly set up.
- **MySQL**: Set up a MySQL instance locally or on the cloud.

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/Priyasha2909/jobPortal.git
cd jobPortal
```

### Configure MySQL Database
- Create a MySQL database named jobportal (or use your preferred name).
- Update the application.properties file located in src/main/resources/ with your MySQL database credentials:

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/jobportal
spring.datasource.username=your-username
spring.datasource.password=your-password

# Hibernate settings

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Build and Run the Application
1. Use Maven to build the project:
   ```bash
   mvn clean install
   ```
2. Run the application:
   ```bash
   mvn spring-boot:run
   ```  

### Screenshots

![image](https://github.com/user-attachments/assets/ff9c3790-d1b5-48c7-b7bb-2896d0acb1f1)

![image](https://github.com/user-attachments/assets/3733236e-8a1a-4722-becc-5bb01afc3ffc)

![image](https://github.com/user-attachments/assets/7638e850-3fdd-4942-ad5f-1e52f19a6b4b)

![image](https://github.com/user-attachments/assets/254e903e-3d41-48cb-b403-d7523a3e3e46)

![image](https://github.com/user-attachments/assets/ac747366-7ccc-41d2-a7e2-17d8764778a6)

![image](https://github.com/user-attachments/assets/d73cef0d-1db8-4de4-a2ab-65049383d3b0)

![image](https://github.com/user-attachments/assets/bc705d6f-2b54-4e51-9a61-f7d4461031f3)







