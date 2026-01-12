# Hiking Shop Backend

Hiking Shop Backend is an application developed with Spring Boot, designed as the backend for a hiking accessories store. The system supports multiple user types, order management, and asynchronous processing, simulating a real e-commerce environment.

The main objective of this project is to demonstrate the development of robust backend applications, applying principles of architecture, security, asynchrony, and best practices in the Spring ecosystem.

## technologies

* Java
* Spring Boot
* Spring Security
* JWT
* Kafka
* Jakarta Validation
* Asynchronous processing
* Design patterns
* REST architecture

## Main functionalities

**Purchasing user**
* Viewing the product catalog
* Purchasing hiking accessories
* Adding credits to your account
* Checking order history
* Asynchronous order processing

**Business user**
* Creating and managing product catalogs
* Price management
* Automatic product stock updates
* Managing published inventory


## Technical approach
The application was designed following a production-oriented backend architecture, integrating security, validations, asynchrony, and traceability

* **Asynchronous processing:** Orders placed by users are processed asynchronously using kafka
* **JWT Security:** Implementation of JSON Web Tokens (JWT) for User Authentication and Role-Based Authorization
* **Validations with Jakarta Validation:** Using declarative validations to ensure data integrity and reduce input errors
* **Interceptors and auditing:** Implementation of interceptors for audit log recording and action traceability within the application
* **Exception handling:** Definition of custom exceptions and centralized error handling
* **Design patterns:** Application of design patterns to improve architecture, pattern factory Used for dynamic product creation, allowing different types of hiking accessories to be managed without coupling business logic
* **Inheritance between entities:** Use of inheritance in Java entities to correctly model different types of products and users, promoting code reuse and clarity in the domain.
