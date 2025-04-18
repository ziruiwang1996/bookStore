# Textbook Exchange Platform

**TEP** is a work-in-progress web application designed to manage a textbook store using a microservices architecture. The application supports features such as user authentication, cart management, book inventory, order processing, and payment handling. It leverages **RabbitMQ** for event-driven communication between services.

---

## Features

### Authentication
- User registration and login.
- JWT-based authentication for secure API access.
- Automatic cart creation upon user login.

### Cart Management
- Add or remove books to/from the cart.
- Place orders from the cart.
- Persist user cart items.

### Order Management
- Reserve, buy, or sell books.
- Handle book inventory with states like `AVAILABLE`, `RESERVED`, and `SOLD` (Finite State Machine pattern).
- Process orders and integrate with the payment service.

### Payment Processing
- Mock payment service that randomly decides success or failure.
- Generate invoices for successful payments.
- Publish payment success or failure events for downstream services.

### Book Inventory
- Manage books with attributes like ISBN, title, authors, publisher, price, and condition.
- Support for both paper books and eBooks.
- Depreciation strategies for used books based on condition (Strategy design pattern).

---

## Technologies Used

### Backend
- Java 21 with Spring Boot for microservices.
- JPA/Hibernate for database interactions.
- RabbitMQ for event-driven communication.
- Jackson for JSON serialization/deserialization.

### Frontend
- HTML/CSS/JavaScript for basic UI.
- Thymeleaf for server-side rendering.
- Future work upgrade with a Vue.js web app.

### Database
- MySQL for persistent storage.

### Messaging
- RabbitMQ for asynchronous communication between services.

---

## Microservices Overview

### 1. Authentication Service
- Handles user registration, login, and logout.
- Publishes events like `auth.user.loggedIn` and `auth.user.loggedOut`.
- Automatically creates or deletes carts for users.

### 2. Cart Service
- Manages user carts.
- Handles commands like `AddItem`, `RemoveItem`, and `PlaceOrder`.
- Publishes events like `cart.user.order_placed`.

### 3. Order Service
- Processes orders and manages book inventory.
- Handles commands like `ReserveBook`, `BuyBook`, and `SellBook`.
- Publishes events like `order.book.bought` and `order.book.sold`.

### 4. Payment Service
- Processes payments for orders.
- Publishes events like `payment.succeeded` and `payment.failed`.
- Generates invoices for successful payments.

---

## Event-Driven Architecture

The application uses RabbitMQ to enable communication between services. Below are some key routing keys and their purposes:

| Routing Key             | Description                          |
|-------------------------|--------------------------------------|
| `auth.user.loggedIn`    | Published when a user logs in.       |
| `auth.user.loggedOut`   | Published when a user logs out.      |
| `cart.user.order_placed`| Published when an order is placed.   |
| `order.book.bought`     | Published when a book is bought.     |
| `order.book.sold`       | Published when a book is sold.       |
| `payment.succeeded`     | Published when a payment succeeds.   |
| `payment.failed`        | Published when a payment fails.      |


## Folder Structure
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── cse687/
│   │           └── zirui/
│   │               └── bookstore/
│   │                   ├── auth/         # Authentication service
│   │                   ├── cart/         # Cart service
│   │                   ├── order/        # Order service
│   │                   ├── payment/      # Payment service
│   │                   ├── shared/       # Shared utilities and models
│   │                   └── BookstoreApplication.java
│   └── resources/
│       ├── application.properties        # Application configuration
│       └── templates/                    # HTML templates
└── test/
    └── java/
        └── com/
            └── cse687/
                └── zirui/
                    └── bookstore/
                        └── BookstoreApplicationTests.java

## Current Limitations
- The application is still under development.
- Some features, such as advanced error handling and user notifications, are not yet implemented.
- The frontend is minimal and may require enhancements for better user experience.

## Future Enhancements
- Add support for user roles (e.g., admin, customer).
- Implement retry mechanisms for failed RabbitMQ messages.
- Enhance the frontend with modern frameworks like React or Angular.
- Add unit and integration tests for all services.
- Deploy the application using Docker and Kubernetes.