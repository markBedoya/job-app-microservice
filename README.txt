Job-App-Microservice-Demo
  Java Spring Boot Microservice Rest APIs with PostgreSQL DB

Summary:
  This takes the job-app and separates the functionality of company/job/reviews
  into individual microservices.

Technologies:
  Service Registry  - Spring cloud netflix eureka for service registry
  Spring Cloud      - Required for service registry and built-in load balancing for Rest Template
  Java              - Primary language
  Spring Boot       - Web Framework
  PostgreSQL DB     - Primary relational DB
  H2 Database       - Rapid DB prototyping in memory with browser url /h2-console
  Test.http Files   - Rapid HTTP request/response testing within the project
  Maven             - Automation and dependency management
  Lombok            - Java library using known patterns to reduce code
  Spring Actuator   - Module that provides features to monitor and manage the application
  Docker            - docker-compose file contains postgres DB app containers required for this app

API Documentation:
  CompanyControllerTest.http
  JobControllerTest.http
  ReviewControllerTest.http