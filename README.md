# E-Commerce Project
Welcome to the E-Commerce project developed as part of the Programming Workshop IV course conducted at Cracow University of Economic! This project aims to provide you with hands-on experience in

- Test-Driven Development (TDD)
- Object-Oriented Programming (OOP) principles
- Best practices and practices in programming
- Java and Spring Boot environment

## Project Overview

### Technologies Used

- Java: A widely used programming language known for its robustness and scalability.
- Spring Boot: A powerful framework for developing Java applications, offering features like dependency injection and MVC architecture.
- JUnit: A popular framework for unit testing in Java, crucial for Test-Driven Development.

### Features

- Payment Processing: Integration with the PayU API for secure and reliable transaction handling.
- Shopping Cart Management: Enables users to add, remove, and update items in their shopping carts.
- Offer Calculation: Computes prices and generates quotations based on selected items and configurations.

### Facade Design Pattern

- Unified Interface: The SalesFacade provides a streamlined interface for operations related to shopping carts, offers, payments, and reservations, simplifying interactions with `Cart`, `OfferCalculator`, `PaymentGateway`, and `ReservationRepository`.
- Simplified Usage: Clients interact with higher-level SalesFacade methods (like `getCurrentOffer`, `acceptOffer`, and `addToCart`), which abstracts the complexity of lower-level components, promoting code clarity and reducing potential errors.

### Technologies Used
- Java 17
- Spring Boot 3.2.4
- JUnit 5.10.2
- AssertJ 3.25.1
- H2 Database
- PayU API
