# Pixel Vault

An e-commerce product management application built with Spring Boot, Thymeleaf, and Spring Security.

## What It Does
- Browse and search products
- User registration and login
- Admin panel for managing products (create, edit, delete)
- Role-based access control (admin vs regular user)

## How to Run with Docker (QA Mode)

### Prerequisites
- Docker Desktop installed and running
- Maven installed

### Steps
1. Build the application jar:
mvn package -DskipTests
2. Start the application with Docker Compose:
docker-compose up --build
3. Open your browser and go to: `http://localhost:8080`
4. To stop: `docker-compose down`

To remove saved data as well:
docker-compose down -v

## How to Run in Development Mode (H2)
mvn spring-boot:run -Dspring-boot.run.profiles=dev

The H2 console is available at `http://localhost:8080/h2-console`

## Environment Profiles

| Profile | Database | When to Use |
|---------|----------|-------------|
| `dev` | H2 (in-memory) | Local development |
| `qa` | MySQL (Docker) | Testing/deployment |

## Team Contributions

|  Member   |        Contributions              |
|-----------|-----------------------------------|
|  Jasmine  | Co-project Manager, Lead developer|               |
|  Darryl   | Secondary developer, Testing      |
| Clarance  | Co-project Manager                |
|   Nour    | Unavailable                       |