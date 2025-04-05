# 🏦 Account Microservice

This is a Spring Boot-based microservice that handles customer account operations such as creation, retrieval, updating, and deletion. It is part of a microservices architecture and communicates with other services via REST APIs.

---

## 🚀 Features

- Create new customer accounts
- Fetch customer details by mobile number
- Update account and customer information
- Delete customer accounts
- JPA Auditing for tracking creation and modification timestamps and users

---

## 📦 Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Lombok
- Hibernate
- MySQL (or your preferred database)
- MapStruct (for DTO mapping)

---

## 🧠 Architecture

This service is built using a layered architecture:
- **Controller Layer** – REST API endpoints
- **Service Layer** – Business logic
- **Repository Layer** – Data access using JPA
- **Entity/DTO Layer** – For model and data transfer separation

---

## ✅ API Endpoints

| Method | Endpoint             | Description                  |
|--------|----------------------|------------------------------|
| POST   | `/api/create`        | Create a new account         |
| GET    | `/api/fetch`         | Fetch customer by mobile     |
| PUT    | `/api/update`        | Update account info          |
| DELETE | `/api/delete`        | Delete account by number     |

---

## ⚙️ How to Run

```bash
# Clone the repository
git clone git@github.com:RANJEETJ06/Account.git

# Navigate into the project
cd Account

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
