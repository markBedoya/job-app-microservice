Job-App-Microservice-Demo
  Java Spring Boot Microservices Rest/MQ APIs with PostgreSQL DB

Summary:
  This is a full microservice architecture project.
  This takes the job-app application and separates the functionality of
  company/job/reviews into individual microservices.

MicroServices:
  Functional Services:
    1. job-service
    2. review-service
    3. company-service
  Supporting Services:
    1. service-registry
    2. gateway-server
    3. config-server

Technologies:
  Service Registry - Spring cloud netflix eureka for service registry
  Spring Cloud     - Required for service registry and built-in load balancing for Rest Template
  OpenFeign        - Spring Cloud library to reduce code for writing REST requests for
                      internal service communications
  Zipkin trace/log - Distributed Tracing and logging solution
  Config Server    - Spring cloud config server example to manage env configs separately outside
                      of the packaged microservice job jar/container
  Gateway Server   - Spring cloud gateway server used to route and load balance external traffic
                      into our microservices
  Resilience4j     - Implement Circuit breaker, REST call retries, API rate limiter options
                      Lightweight, easy-to-use fault tolerance library for spring-boot apps
  RabbitMQ         - Async MQ messaging between review and company microservices
  Java             - Primary language
  Spring Boot      - Web Framework
  PostgreSQL DB    - Primary relational DB
  H2 Database      - Rapid DB prototyping in memory with browser url /h2-console
  Test.http Files  - Rapid HTTP request/response testing within the project
  Maven            - Automation and dependency management
  Lombok           - Java library using known patterns to reduce code
  Spring Actuator  - Module that provides features to monitor and manage the application
  Docker           - docker-compose file contains all app containers required for this app

API Documentation:
  CompanyControllerTest.http
  JobControllerTest.http
  ReviewControllerTest.http
  GatewayControllerTest.http

Setup PostgreSQL DB:
  1. Clear local docker containers and images if starting from scratch to get latest images
  2. Run the docker compose file
  3. Config postgre with pgadmin4 by find the postgre IP by running cmd [docker inspect <container-id>]
  4. Create the databases for the individual microservices (job,company,review)

How to create Docker Images of these services and push to Docker Registry:
  0. Generate Maven Wrapper files - In root project folder, run cmd
    [mvn wrapper:wrapper] if not present already to run the cmd below.
  1. Startup Docker engine in order to run the cmd below in the root directory of the project.
  2. Create docker image from project root with
    [./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=markbedoya/service-registry"]
  3. Login with [docker login]
  4. Check current docker images and their tags with [docker images]
  5. Run image with [docker run -d -p 8080:8080 markbedoya/job-app-image]
  6. See docker containers running with [docker ps]
  7. See application logs with [docker logs <container-id>]
  8. Stop docker container with [docker stop <container-id>]
  9. See docker all containers with [docker ps -a]
  10. remove docker container with [docker rm <container-id>]
  11. Push image to docker hub with [docker push markbedoya/job-app-image:latest]