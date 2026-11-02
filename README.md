# Betacomics

REST API backend for an e-commerce store selling **comics, board games and action figures**.
Built with Java and Spring Boot as the final team project of the Full Stack Web Developer bootcamp at Betacom.

## Features

- **Authentication & roles**: registration and login with JWT, stateless sessions, `CUSTOMER` and `ADMIN` roles
- **Product catalog**: comics, board games and action figures, with pagination and full CRUD for admins
- **Shopping cart**: add, update and remove items, with stock checks on every change
- **Checkout & orders**: transactional checkout that decrements stock and saves the price at purchase time; order history for customers
- **Admin tools**: update order status (`PENDING`, `PAID`, `SHIPPED`, `DELIVERED`, `CANCELLED`), list orders filtered by status or by user
- **Error handling**: one global handler that returns a consistent JSON error with a code, message and validation details
- **API docs**: interactive Swagger UI

## Tech stack

| Area | Technology |
|------|------------|
| Language | Java 21 |
| Framework | Spring Boot 4.1.0 (Web MVC, Data JPA, Validation) |
| Security | Spring Security, JWT (JJWT) |
| Database | PostgreSQL (H2 in-memory for tests) |
| API docs | springdoc-openapi (Swagger UI) |
| Build | Maven (wrapper included) |
| Other | Lombok, JUnit 5 |

## Getting started

### Prerequisites

- JDK 21
- PostgreSQL

### 1. Create the database

Create an empty PostgreSQL database, then run the schema script on it:

```bash
createdb betacomics
psql -d betacomics -f define.sql
```

### 2. Set the environment variables

| Variable | Required | Description |
|----------|----------|-------------|
| `db_url` | yes | e.g. `jdbc:postgresql://localhost:5432/betacomics` |
| `db_user` | yes | database user |
| `db_pwd` | yes | database password |
| `jwt_secret` | no | Base64 secret used to sign tokens. **Set your own in production.** |
| `jwt_expiration_ms` | no | token lifetime in milliseconds (default: 1 hour) |

Linux / macOS:

```bash
export db_url=jdbc:postgresql://localhost:5432/betacomics
export db_user=postgres
export db_pwd=your_password
```

Windows (PowerShell):

```powershell
$env:db_url="jdbc:postgresql://localhost:5432/betacomics"
$env:db_user="postgres"
$env:db_pwd="your_password"
```

### 3. Run the app

```bash
./mvnw spring-boot:run
```

On Windows use `mvnw.cmd spring-boot:run`.

The API is available at `http://localhost:8080` and the Swagger UI at
`http://localhost:8080/swagger-ui/index.html`.

### 4. Create an admin user

Every new registration gets the `CUSTOMER` role. To get an admin, register a user and promote it in the database:

```sql
UPDATE users SET role = 'ADMIN' WHERE username = 'your_username';
```

## Quick example

Register, log in and call a protected endpoint:

```bash
# Register
curl -X POST http://localhost:8080/api/v1/users/register \
  -H "Content-Type: application/json" \
  -d '{"username":"marco","email":"marco@example.com","password":"secret123"}'

# Login: the response contains the JWT in "accessToken"
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"marco","password":"secret123"}'

# Use the token
curl http://localhost:8080/api/v1/cart \
  -H "Authorization: Bearer <accessToken>"
```

## API overview

The full, interactive list is in the Swagger UI. Main endpoints:

| Area | Method | Endpoint | Access |
|------|--------|----------|--------|
| Auth | POST | `/api/v1/users/register` | public |
| Auth | POST | `/api/v1/auth/login` | public |
| Profile | GET / PUT | `/api/v1/users/profile` | logged in |
| Catalog | GET | `/product/list`, `/product/getById/{id}` | public |
| Catalog | GET | `/comic`, `/boardGame`, `/actionFigure` + `/list`, `/getById/{id}` | public |
| Catalog | POST / PATCH / DELETE | `/comic`, `/boardGame`, `/actionFigure` + `/create`, `/update`, `/delete/{id}` | admin |
| Cart | GET / DELETE | `/api/v1/cart` | logged in |
| Cart | POST / PUT | `/api/v1/cart/items` | logged in |
| Cart | DELETE | `/api/v1/cart/items/{productId}` | logged in |
| Orders | POST | `/api/v1/orders/checkout` | logged in |
| Orders | GET | `/api/v1/orders/history`, `/api/v1/orders/{orderId}` | logged in |
| Orders | PUT | `/api/v1/orders/{orderId}/status` | admin |
| Orders | GET | `/api/v1/orders/list`, `/api/v1/orders/listByUser` | admin |

## Project structure

```
src/main/java/com/betacomics
├── controllers/    REST endpoints
├── services/       business logic (interfaces + implementations)
├── repositories/   Spring Data JPA repositories
├── models/         JPA entities (Product is the base of Comic, BoardGame, ActionFigure)
├── dto/            request (input) and response (output) objects
├── maps/           entity <-> DTO mapping
├── security/       JWT filter and service, Spring Security configuration
├── exceptions/     custom exceptions and global exception handler
└── enums/          Role, OrderStatus
```

## Design notes

- **Product hierarchy**: `Product` is an abstract entity with JOINED inheritance; each product type has its own table with its specific fields.
- **Price snapshot**: each order item stores `price_at_purchase`, so later price changes do not alter past orders.
- **DTOs**: entities are never returned directly; requests and responses use dedicated DTO classes with validation groups for create and update.
- **Stateless security**: no HTTP session; every request is authenticated through the `Authorization: Bearer` header.
- **CORS**: configured for `http://localhost:4200` (a local frontend).

<!--
TEMPLATE FOR THE "MY ROLE" SECTION (invisible on GitHub).
Fill it in with what you did yourself, then delete the comment markers and move it above "Getting started".

## My role

In a team of N people I worked on:
- ...
- ...
-->
