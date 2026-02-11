# 🧩 spring-cloud-hr-platform — Spring Cloud Microservices Suite

This repository contains a **complete microservices ecosystem** built with **Spring Boot + Spring Cloud**, focused on **distributed architecture**, **OAuth2/JWT security**, **service discovery**, **API Gateway**, and **centralized configuration**.

It simulates a **real-world enterprise backend platform**, emphasizing architectural decisions rather than simple CRUD operations.

---

## 🏗️ System Architecture

### Overview

The platform follows a **Spring Cloud–based microservices architecture**, composed of independent services communicating through an **API Gateway (Zuul)** and registered in a **Service Discovery Server (Eureka)**.

### Architecture Flow

1. The **Client** interacts only with the **API Gateway**
2. The **Gateway** routes requests to internal microservices
3. All microservices are dynamically registered in **Eureka Server**
4. Configuration is externalized via **Config Server**, backed by a Git repository
5. Each service owns its database

### Key Characteristics

* Loose coupling between services
* Horizontal scalability (multiple Worker instances)
* Centralized configuration management
* Gateway-based perimeter security

---

## 🔐 Security Architecture (OAuth2 + JWT)

### Authentication & Authorization Flow

The system implements **OAuth2 with JWT**, separating responsibilities between **Authorization Server** and **Resource Servers**.

1. Client sends **application credentials + user credentials** to the Authorization Server
2. Authorization Server validates credentials and issues a **JWT token**
3. Client accesses protected resources by sending the token
4. Resource Servers validate:

   * Token signature
   * Claims
   * Expiration time

### Security Design Decisions

* Stateless authentication (JWT)
* No server-side sessions
* Role-based access control (RBAC)
* Token propagation handled by the API Gateway

---

## 🧠 Conceptual Domain Model

### Core Entities

#### Worker

* `id: Long`
* `name: String`
* `dailyIncome: Double`

Managed by the **hr-worker** service.

---

#### Payment

* `name: String`
* `dailyIncome: Double`
* `days: Integer`
* `total: Double`

Computed by the **hr-payroll** service using data from `hr-worker`.

---

#### User & Role

* Users have a **many-to-many** relationship with roles
* Used exclusively by the **authentication domain**

This separation ensures clear **bounded contexts** between:

* Business domain (Worker / Payroll)
* Security domain (User / Role)

---

## 🧩 Architectural Patterns Applied

### Microservices Pattern

* Independent deployability
* Isolated business logic
* Database per service

---

### API Gateway Pattern

* Single entry point
* Centralized routing
* Security enforcement

---

### Service Discovery (Eureka)

* Dynamic service registration
* No hardcoded URLs
* Client-side load balancing

---

### Centralized Configuration

* Externalized configuration files
* Environment-specific profiles
* Runtime refresh using `@RefreshScope`

---

### Circuit Breaker (Hystrix)

* Fault isolation
* Graceful degradation
* Improved system resilience

---

## 🛠️ Technologies Used

* Java 11
* Spring Boot 2.3.4.RELEASE
* Spring Cloud Netflix (Hoxton)
* Spring Security OAuth2
* JWT
* Eureka
* Zuul
* Feign Client
* Hystrix
* Lombok
* Maven

---

spring-cloud-hr-platform/  
│       
├─ hr-api-gateway-zuul
├─ hr-config-server
├─ hr-eureka-server
├─ hr-oauth
├─ hr-user
├─ hr-worker
└─ hr-payroll

## 🐳 Containerization with Docker

All microservices in this platform are **fully containerized** using **Docker**, enabling consistent runtime environments and simplifying local execution and deployment.

Each service includes its own **Dockerfile**, following a standardized approach and aligned with the project’s Java and Spring Cloud versions.

### Docker Strategy

- **Base image:** `eclipse-temurin:11-jre`
- One Docker image per microservice
- JAR-based execution (`java -jar`)
- Lightweight runtime images (JRE only)
- Compatible with Spring Boot 2.3.x and Spring Cloud Hoxton

This ensures:
- Environment consistency across machines
- Easier local setup
- Readiness for container orchestration (Docker Compose / Kubernetes)

### Dockerized Services

- hr-api-gateway-zuul
- hr-config-server
- hr-eureka-server
- hr-oauth
- hr-user
- hr-worker
- hr-payroll

Each service can be built independently using:

```bash
docker build -t <service-name>:v1 .
```
---

## ▶️ How to Run

1. Start **Config Server**
2. Start **Eureka Server**
3. Start databases (PostgreSQL containers)
4. Start all business microservices
5. Start API Gateway

> The startup order is important to avoid configuration and registration issues.

---

## 🚀 Project Status

✔ Fully functional architecture  
✔ Security implemented  
✔ Inter-service communication enabled  
✔ Enterprise-grade patterns applied

This project is designed to demonstrate **real microservices architecture skills**, not just framework usage.

---

```
