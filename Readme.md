# **Product Service Overview**

Product Service is a microservice responsible for managing product-related operations. It provides RESTful APIs for creating, retrieving, updating, and deleting products. The service relies on UserService for authentication.

### **Endpoints**

**Retrieve Product by ID**

    Method: GET
    
    Endpoint: /product/{id}
    
    Description: Fetches details of a specific product by its ID.

**Retrieve All Products**

    Method: GET
    
    Endpoint: /product
    
    Description: Fetches a list of all available products.

**Create Product**

    Method: POST
    
    Endpoint: /product
    
    Description: Creates a new product.

**Delete Product**

    Method: DELETE
    
    Endpoint: /product/{id}
    
    Description: Deletes a product by its ID.

**Partially Update Product**

    Method: PATCH
    
    Endpoint: /product/{id}
    
    Description: Partially updates a product's attributes.

**Dependencies**

    User Service: Required for authentication and authorization.
    
    Eureka Server: Service discovery and registration.
    
    Redis: Caching for improved performance.
    
    MySQL: Database for product storage.

**Technologies Used**

    Spring Boot 3.3.2
    
    Java
    
    RESTful APIs
    
    Spring Security (OAuth2)
    
    Hibernate & JPA
    
    MySQL
    
    Redis
    
    Eureka Service Discovery
    
    OpenAPI (Swagger)

**Setup and Running**

    Prerequisites
    
    Java 17+
    
    Gradle
    
    MySQL Database
    
    Redis Server
    
    Eureka Server
    
    User Service up and running

**Configuration**

Ensure the following environment variables are set:

Build and Run

gradle clean build
gradle bootRun

**API Documentation**

    The service provides OpenAPI documentation using Swagger.
    
    API Docs: /swagger/json
    
    Swagger UI: /swagger/ui.html

**Authentication**

    The service relies on OAuth2 authentication via UserService. The authentication provider is defined by spring.security.oauth2.resourceserver.jwt.issuer-uri.

**License**

    This project is licensed under the MIT License.

