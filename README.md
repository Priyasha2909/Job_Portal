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

![image](https://github.com/user-attachments/assets/4a4b5585-458d-48a4-99f6-17005d8e6bc8) 

![image](https://github.com/user-attachments/assets/25fb1233-e2bc-44f2-b51a-089517203e9a) 

![image](https://github.com/user-attachments/assets/6771e6d4-6f5c-45f7-aab7-2c4c7e336b67)

![image](https://github.com/user-attachments/assets/51b6b133-6301-42a5-a468-18ad8724f69e)



