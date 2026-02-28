# Wallet Service

A RESTful wallet service built using Java 17 and Spring Boot 3.

The application supports deposit, withdrawal, and balance retrieval operations.
The system is fully containerized and runs using Docker Compose.

---

## Tech Stack

- Java 17
- Spring Boot 3
- PostgreSQL 15
- Liquibase (Database migrations)
- Docker
- Docker Compose
- Maven

---

## Features

- Deposit funds into wallet
- Withdraw funds from wallet
- Retrieve wallet balance
- Concurrency-safe balance updates
- Proper HTTP error handling (400, 404)
- Fully containerized setup
- Configurable via environment variables

---

## How to Run

Make sure Docker is installed and running.

### Start the system:

docker-compose up --build

The application will be available at:

http://localhost:8080

PostgreSQL runs inside Docker on port 5432.

---

## API Endpoints

### 1. Deposit / Withdraw

POST /api/v1/wallet

Request body:

{
"walletId": "UUID",
"operationType": "DEPOSIT" or "WITHDRAW",
"amount": 1000
}

Response:
- 200 OK on success
- 400 Bad Request for invalid input
- 404 Not Found if wallet does not exist
- 409 Conflict for insufficient funds

---

### 2. Get Wallet Balance

GET /api/v1/wallets/{walletId}

Response:
{
"walletId": "UUID",
"balance": 1000
}

---

## Database Migration

Liquibase is used for schema versioning.

On application startup:
- Tables are created automatically
- Migrations are applied
- No manual database setup required

Liquibase tables:
- databasechangelog
- databasechangeloglock

---

## Concurrency Handling

To support high load (1000 RPS per wallet), balance updates are performed using atomic SQL update queries:

Withdraw operation:
UPDATE wallet
SET balance = balance - :amount
WHERE id = :walletId
AND balance >= :amount

This ensures:
- No negative balances
- No race conditions
- Safe concurrent updates

---

## Running in Development (Without Docker)

mvn clean package
mvn spring-boot:run

Configure database in application.yml.

---

## Author

Manish Sehrawat