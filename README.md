# 🛡️ Secure Spring Boot REST API with Keycloak OAuth2

This project is a secure and scalable Spring Boot-based REST API that implements **OAuth2 authentication and role-based access control (RBAC)** using **Keycloak**, with PostgreSQL as the database backend. It provides robust API endpoints for **Product (CRUD)** and **Category (CRD)** management, tailored for Admin and Seller roles.

---

## 🚀 Features

- 🔐 **OAuth2 Security with Keycloak**
  - Role-based access control: `ADMIN`, `SELLER`
  - Secured endpoints based on roles and permissions

- 📦 **Product & Category Management**
  - Product: Create, Read, Update, Delete
  - Category: Create, Read, Delete

- 🗃️ **PostgreSQL Database**
  - Schema managed via JPA/Hibernate
  - Efficient queries with proper indexing

- 📓 **Custom Logback Logging**
  - Structured and contextual logging for better debugging and monitoring

- 📑 **Swagger (OpenAPI) Integration**
  - Live API documentation at `/swagger-ui.html`

- 🔍 **Spring Boot Actuator**
  - Health checks, metrics, and runtime application insights

- 🔁 **DTO Mapping with Mappers**
  - Clean separation of entity and response models
  - Improved maintainability and security

- 🧪 **JUnit Test Coverage**
  - Comprehensive unit and integration tests
  - Robust error handling and validation

---

## 📁 Tech Stack

- **Java 24**
- **Spring Boot**
- **Spring Security + OAuth2**
- **Keycloak**
- **PostgreSQL**
- **Swagger / OpenAPI**
- **Spring Data JPA**
- **JUnit & Mockito**
- **Spring Boot Actuator**
- **Logback**
