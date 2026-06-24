# RideBooking Application

A Spring Boot-based backend application for a ride-booking platform, providing robust APIs for managing users, drivers, rides, and authentication.

## Tech Stack

* **Java Version:** 25
* **Framework:** Spring Boot 4.0.6
* **Build Tool:** Gradle
* **Database:** PostgreSQL with Hibernate Spatial for geo-spatial data support (e.g., location tracking, distance calculation).
* **Security:** Spring Security with JWT (JSON Web Tokens) for authentication and authorization.
* **API Documentation:** OpenAPI/Swagger (via Springdoc).
* **Other Tools:** Lombok, ModelMapper, Spring Mail (SMTP).

## Project Structure

The project follows a standard multi-layer architecture under `src/main/java/com/RideBooking`:
* **Controller**: REST API endpoints for handling incoming HTTP requests.
* **Service**: Business logic implementation.
* **Repository**: Spring Data JPA interfaces for database interactions.
* **Entity**: JPA entities mapping to database tables.
* **DTO**: Data Transfer Objects for request/response payloads.
* **Security**: JWT filters, authentication, and authorization configurations.
* **Strategy**: Implementations of the Strategy pattern for algorithms (e.g., ride matching, pricing).
* **Advice**: Global exception handling.

## Getting Started

### Prerequisites

* Java 25
* PostgreSQL database

### Configuration

1. Make sure your PostgreSQL database is running.
2. Configure your database connection properties (URL, username, password) and SMTP settings in `src/main/resources/application.yml` or `application.properties`.
3. If necessary, ensure the PostGIS extension is enabled in your PostgreSQL database to support Hibernate Spatial operations.

### Running the Application

You can run the application using Gradle:

```bash
./gradlew bootRun
```

Or build the executable JAR and run it:

```bash
./gradlew build
java -jar build/libs/RideBooking-0.0.1-SNAPSHOT.jar
```

## API Documentation

Once the application is running, you can access the Swagger UI to view and test the API endpoints:

`http://localhost:8080/swagger-ui.html` (Assuming default port 8080)
