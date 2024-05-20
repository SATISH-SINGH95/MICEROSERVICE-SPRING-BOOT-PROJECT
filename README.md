
# **Microservices Project with Spring Boot**

## **Overview**
This project demonstrates a microservices architecture using Spring Boot. The system consists of the following components:

- **User Service:** Manages user-related operations.
- **Address Service:** Manages address-related operations.
- **Config Server:** Centralized configuration management.
- **Eureka Server:** Service registry for service discovery.
- **API Gateway:** Routes requests to the appropriate microservice.


## **Table of Content**
- Architecture
- Prerequisites
- Setup Instructions
- Services Overview
    - User Service
    - Address Service
    - Config Server
    - Eureka Server
    - API Gateway
- Usage
- Endpoints
- Contributing
- Contact

## **Architecture**
The architecture of this project is depicted in the diagram below:

```bash
 Client
   |
   v
API Gateway
   |
   v
Eureka Server (Service Registry)
   |
   +-------------------+
   |                   |
   v                   v
User Service      Address Service
```

## **Prerequisites**

- Use Java 17 or latest version for better outcomes
- Maven 3.6.3 or higher

## **Setup Instructions**

- **Clone the repository:**

```bash
git clone https://github.com/SATISH-SINGH95/MICEROSERVICE.git
cd microservices-spring-boot

```

- **Build all services:**

```bash
mvn clean install
```

- **Run the Config Server:**

```bash
cd config-server
mvn spring-boot:run
```

- **Run the Eureka Server:**
```bash
cd eureka-server
mvn spring-boot:run
```

- **Run the API Gateway:**
```bash
cd api-gateway
mvn spring-boot:run
```

- **Run the User Service:**
```bash
cd user-service
mvn spring-boot:run
```

- **Run the Address Service:**
```bash
cd address-service
mvn spring-boot:run
```

## **Services Overview**
- **User Service**
    - **Port:** 8081
    - **Description:** Manages user-related data and operations.
    - **Communication:** Uses Feign client to communicate with the Address Service.

- **Address Service**
    - **Port:** 8082
    - **Description:** Manages address-related data and operations.

- **Config Server**
    - **Port:** 8084
    - **Description:** Provides centralized configuration for all services.
    - **Configuration Repository:** This can be set up to read from a Git repository or local files.

- **Eureka Server**
    - **Port:** 8761
    - **Description:** Service registry for locating services for the purpose of load balancing and failover of middle-tier servers.

- **API Gateway**
    - **Port:** 8083
    - **Description:** Routes requests to the appropriate microservice based on the URI pattern.
    - **Circuit Breaker:** Implements a circuit breaker pattern to handle the unavailability of the any of the Services and give proper response to the end users.


## **Usages**

- **Access Eureka Dashboard:**
    Navigate to http://localhost:8761 to view the Eureka dashboard and check the status of registered services.

- **API Gateway Routes:**
    - **User Service**: 
    ```bash
    http://localhost:8083/users/userId/2
    ```

    - **Address Service**:
    ```bash
    http://localhost:8083/addresses/address/4
    ```
    
## **Endpoints:**
- **User Service Endpoints**
    - **`POST** **/users/create** : to create user
    - **`GET** **/users/** : to get all users
    - **`GET** **/users/userId/{userId}** : to single user by their userId
- **Address Service Endpoints**
    - **`POST** **/addresses/** : to create address
    - **`GET** **/addresses/addressId/{AddressId}** : to get single address by addressId
    - **`GET** **/addresses/adddress/userId/{userId}** : to get list of addresses by userId














## **Contributing**
Contributions are welcome! Please fork the repository and create a pull request with your changes.

## **Contact**
For questions or feedback, please contact sk95satish@gmail.com


