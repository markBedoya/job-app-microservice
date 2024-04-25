Job-App-Microservice-Demo
  Java Spring Boot Microservice Rest APIs with PostgreSQL DB

Summary:
  This takes the job-app and separates the functionality of company/job/reviews
  into individual microservices.

Technologies:
  Service Registry - Spring cloud netflix eureka for service registry
  Spring Cloud     - Required for service registry and built-in load balancing for Rest Template
  OpenFeign        - Spring Cloud library to reduce REST request code for internal service communications
  Zipkin trace/log - Distributed Tracing and logging solution
  Config Server    - Spring cloud config server managing all env configs in one place.
  Gateway Server   - Spring cloud gateway server used to route external traffic into our microservices
  Java             - Primary language
  Spring Boot      - Web Framework
  PostgreSQL DB    - Primary relational DB
  H2 Database      - Rapid DB prototyping in memory with browser url /h2-console
  Test.http Files  - Rapid HTTP request/response testing within the project
  Maven            - Automation and dependency management
  Lombok           - Java library using known patterns to reduce code
  Spring Actuator  - Module that provides features to monitor and manage the application
  Docker           - docker-compose file contains postgres DB app containers required for this app

API Documentation:
  CompanyControllerTest.http
  JobControllerTest.http
  ReviewControllerTest.http

Setup PostgreSQL DB:
1. Clear local docker containers and images if starting from scratch to get latest images
2. Run the docker compose file
3. Config postgre with pgadmin4 by find the postgre IP by running cmd [docker inspect <container-id>]
4. Create the databases for the individual microservices (job,company,review)