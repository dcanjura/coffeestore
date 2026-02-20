# CoffeeStore API

REST API for managing coffee orders, built with Spring Boot 3.

This project demonstrates clean architecture practices, DTO mapping, unit testing, integration testing, and observability with Actuator and Prometheus.

## Tech Stack

- Java 21
- Spring Boot 3.3.5
- Spring Data JPA
- H2 Database
- SpringDoc (Swagger)
- Micrometer + Prometheus
- JUnit 5 + Mockito
- JaCoCo
- Maven

## Project Structure

com.exercise.coffeestore
├── controller        # REST controllers
├── service           # Business logic
├── repository        # JPA repositories
├── model             # Entities
├── dto               # Data Transfer Objects
├── mapper            # Entity-DTO mapping logic
├── integration       # Integration tests
└── test              # Unit tests

## Project Structure

com.exercise.coffeestore
├── controller        # REST controllers
├── service           # Business logic
├── repository        # JPA repositories
├── model             # Entities
├── dto               # Data Transfer Objects
├── mapper            # Entity-DTO mapping logic
├── integration       # Integration tests
└── test              # Unit tests


---

## API Documentation

```md
## Swagger UI

Available at:

http://localhost:8080/swagger-ui.html

## Observability

Actuator endpoints:

http://localhost:8080/actuator

Prometheus metrics:

http://localhost:8080/actuator/prometheus

Metrics include:
- JVM memory
- HTTP request metrics
- Database metrics

## Testing

Run unit tests:

```bash
mvn test


---

## Architecture Notes

- DTO pattern used to decouple API from persistence layer.
- Static mapper methods used for entity conversion.
- Integration tests validate full HTTP flow.
- Unit tests isolate service layer using Mockito.
- H2 in-memory database is used for testing.

## Example - Create Coffee

POST /coffees

{
  "name": "Latte",
  "description": "Milk coffee",
  "price": 3.5
}

