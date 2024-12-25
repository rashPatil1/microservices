# Microservices project with Spring Boot, Docker, Kubernetes

## Overview
This project is based on the original implementation by EazyBytes.  
[Original Project: EazyBytes Microservices](https://github.com/eazybytes/microservices)


### Section 2
- **Description**: Builds three microservices - Accounts, Loans, Cards.

### Section 4
- **Description**: Builds three microservices - Accounts, Loans, Cards using Docker images and containers.

### Section 6

#### v1 - Spring Boot
- **Description**: Builds Accounts, Loans, and Cards microservices.
- **Configuration Management**: Handled internally using Spring Boot.

#### v2 - Spring Cloud
- **Description**: Builds Accounts, Loans, and Cards microservices.
- **Configuration Management**:
  - Handled externally using Spring Cloud Config Server.
  - Reads configurations from various sources:
    - Classpath
    - File system
    - Git repository
  - Integrates the Config Server with all microservices.
  - Updates Docker Compose to include Spring Cloud Config Server for different profiles.
 
### Section 7
- **Description**: Replacing h2 with mysql db for each microservice .

### Section 8
- **Description**: Client side Service Discovery (service registration), load balancing using Eureka, openFeign.
  - Demonstrates incorpating these above changes by starting the microservices (accounts, cards, loans), configserver,eurekaserver manually.
  - Also Demonstrates incorpating these above changes by starting all (accounts, cards, loans), configserver,eurekaserver in the form of containers using Docker compose.
