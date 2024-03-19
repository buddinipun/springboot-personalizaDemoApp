# Spring Boot API with Keycloak Authentication

This project is a Spring Boot application that integrates with Keycloak for authentication and authorization. It provides a set of secured APIs and includes a SQL database setup, specifically designed to run on port 8081.

## Features

- Spring Boot API
- SQL database integration with initial setup script (`smapi2.sql`)
- Keycloak for authentication and authorization
- Predefined roles: `client_internal` and `client_external`
- OpenAPI documentation
- Comprehensive test cases for each endpoint

## Getting Started

These instructions will help you set up and run the project locally for development and testing purposes.

### Prerequisites

- JDK 1.8 or later
- Maven 3.2+
- MySQL Server 5.6 or later
- Keycloak Server

### Setup Instructions

#### 1. Clone the repository

```bash
git clone https://github.com/yourusername/yourrepository.git
cd yourrepository
```

#### 2. Database Setup

Import the `smapi2.sql` file to your MySQL database.

```bash
mysql -u username -p database_name < smapi2.sql
```

#### 3. Keycloak Configuration

1. Start your Keycloak server on port 8080.
2. Create a realm named `smweb`.
3. Under the `smweb` realm, create two roles: `client_internal` and `client_external`.

#### 4. Application Configuration

Update `src/main/resources/application.properties` with your database and Keycloak settings:

```properties
# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/your_database?useSSL=false
spring.datasource.username=your_username
spring.datasource.password=your_password

# Keycloak configuration
keycloak.realm=smweb
# other keycloak settings...
```

#### 5. Run the Application

```bash
mvn spring-boot:run
```
The application will start running on `http://localhost:8081`.

### Using the API

- **Swagger UI:** Access the API documentation and try out the endpoints at `http://localhost:8081/swagger-ui/index.html`.
- **OpenAPI Docs:** View the OpenAPI documentation JSON at `http://localhost:8081/v3/api-docs/`.

### Testing

The application includes test cases for each endpoint. Run the following command to execute the tests:

```bash
mvn test
```

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
- [Maven](https://maven.apache.org/) - Dependency Management
- [MySQL](https://www.mysql.com/) - Database
- [Keycloak](https://www.keycloak.org/) - Authentication and Authorization

## Authors

- **Name** - *Initial Work* - [buddinipun](https://github.com/YourUsername)
