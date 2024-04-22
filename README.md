<!-- Project Title -->
<h1 align="center">Post Management API</h1>

<!-- Project Description -->
<p align="center">This Spring Boot application provides API endpoints for managing posts. It uses Spring Data JPA with MySQL for database interaction, Spring Security for security management, and JSON Web Token (JWT) for authentication.</p>

<!-- Technologies Used -->
<h2 align="center">Technologies Used</h2>
<p align="center">
  <img src="https://img.shields.io/badge/Spring_Boot-3.1.10-green" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Spring_Data_JPA-2.5.5-green" alt="Spring Data JPA">
  <img src="https://img.shields.io/badge/MySQL-blue" alt="MySQL">
  <img src="https://img.shields.io/badge/Spring_Security-6.0-blue" alt="Spring Security">
  <img src="https://img.shields.io/badge/JSON_Web_Token_(JWT)-0.11.5-orange" alt="JSON Web Token (JWT)">
</p>

<!-- Getting Started -->
<h2 align="center">Getting Started</h2>

### Prerequisites
- Java 8 or higher
- Maven
- MySQL

Installation
Follow these steps to set up and run the project:

Clone the Repository:
git clone https://github.com/duyddong/post_management.git

Navigate to the Project Directory:
cd post_management

Set up the MySQL Database:
Create a MySQL database named post_management.
Configure the database connection in the application.properties file.
Build and Run the Application:
./mvnw spring-boot:run

Test the API Endpoints:
Use tools like Postman to test the API endpoints provided by the application.

<h2 align="center">API Endpoints</h2>
<p align="center">
  <b>/api/posts</b>: CRUD operations for posts.<br>
  <b>/api/comments</b>: CRUD operations for comments.<br>
  <b>/api/photos</b>: CRUD operations for photos.<br>
  <b>/api/users</b>: CRUD operations for users.<br>
  <b>/api/tasks</b>: CRUD operations for tasks.
</p>

<h2 align="center">Authentication</h2>
<p align="center">The application uses JSON Web Tokens (JWT) for authentication. Clients must include a valid JWT token in the Authorization header of their requests to access protected endpoints.</p>

<h2 align="center">Contributing</h2>
<p align="center">Contributions are welcome! Feel free to open issues or submit pull requests for any improvements or bug fixes you'd like to contribute.</p>
