Job-App-Microservice-Demo
  Java Spring Boot Microservices Rest/MQ APIs with PostgreSQL DB

Summary:
  This is a full microservice architecture project.
  This takes the job-app application and separates the functionality of
  company/job/reviews into individual microservices.

This App Services:
  Microservices:
    1. job-service
    2. review-service
    3. company-service
  Supporting Services:
    1. service-registry
    2. gateway-server
    3. config-server

API Documentation:
  CompanyControllerTest.http
  JobControllerTest.http
  ReviewControllerTest.http
  GatewayControllerTest.http

Running this app:
  1. IDE Intellij - run individual microservices and run [docker compose up -d] for all required
      supporting services that are not included in the apps microservices.
      Note: Setup postgre DB is required for first time and steps are listed below.
  2. Docker Containers - run cmd [docker compose up -d] for all within docker compose file.
      Note: Setup postgre DB is required for first time and steps are listed below.
  3. Kubernetes Cluster - using minikube following the steps for "start k8s cluster" below.
      Note: TODO - job/company/review have unknownHostException: postgres when connecting
                   to postgres from k8 cluster.
      Note: service-registry, gateway-server, config-server are not required when running from
            k8 files as service discovery, gateway / load balancing, env configs
            are handled innately.

Technologies:
  Kubernetes (K8s) - Service discovery, load balancing, horizontal scaling,
                      automated rollouts and rollbacks, self-healing, secret and config management,
  Minikube         - minikube is local kubernetes, focusing on making it easy to learn and develop
                      for Kubernetes. Not for production.
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

Docker Support:
  Setup PostgreSQL DB docker container:
    1. Clear local docker containers and images if starting from scratch to get latest images
    2. Run the docker compose file
    3. Config postgre with pgadmin4 by find the postgre IP by running cmd [docker inspect <container-id>]
    4. Create the databases for the individual microservices (job,company,review)
    5. Use pgAdmin for the database GUI
  Example of how to create Docker Images of these microservices and push to Docker Registry:
    0. Generate Maven Wrapper files - In root project folder, run cmd
      [mvn wrapper:wrapper] if not present already to run the cmd below.
    1. Startup Docker engine in order to run the cmd below in the root directory of the project.
    2. Create docker image from project root with
      [./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=markbedoya/service-registry"]
      [./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=markbedoya/job-service"]
      Note: if build fails, make sure postgre DB is running as that is a common error I ran into.
    3. Push image to docker hub with [docker push markbedoya/service-registry:latest]
  Run individual docker images in containers:
    1. Login with [docker login]
    2. Check current docker images and their tags with [docker images]
    3. Run image with [docker run -d -p 8761:8761 markbedoya/service-registry]
    4. See docker containers running with [docker ps]
    5. See application logs with [docker logs <container-id>]
    6. Stop docker container with [docker stop <container-id>]
    7. See docker all containers with [docker ps -a]
    8. remove docker container with [docker rm <container-id>]

Kubernetes(K8s) Support:
  Setup PostgreSQL DB from minikube k8 files:
    1. Ensure minikube is running
    2. Start postgres k8 files [kubectl apply -f k8s\support-services\postgres\]
    3. login to postgres k8 pod terminal [kubectl exec -it postgres-0 -- psql -U sa]
    4. list postgres DBs [\l]
    5. create databases from postgres pod terminal [create database job;] (review/company also needed)
    6. to exit terminal [ctrl+d]
  For local development and testing k8s, download minikube
    Note: Eureka and API gateway services are not required when running from k8 files
    Download link [https://minikube.sigs.k8s.io/docs/start/]
    Start minikube by running docker engine, then [minikube start --driver=docker]
    Check kubectl [kubectl cluster-info]
    View minikube GUI [minikube dashboard] and it will open in browser
    Start one file or all k8 files in directory [kubectl apply -f k8s\support-services\postgres\]
    cmd to get all pods [kubectl get pods] and add -w for watch mode
    cmd to get services [kubectl get service]
    cmd to get replicaSet [kubectl get replicaset]
    delete pod [kubectl delete my-replicaset]
    cmd to get deployments [kubectl get deployments]
    view event details of a pod [kubectl describe deployment <my-deployment>]
    view logs of a pod [kubectl logs <my-deployment>]
    get all running k8 running items [kubectl get all]
    delete all deployments [kubectl delete deployments --all --all-namespaces]
    delete all services [kubectl delete services --all --all-namespaces]
    delete all pods [kubectl delete pods --all --all-namespaces]
    delete all replicasets [kubectl delete replicasets --all --all-namespaces]
    delete all statefulsets [kubectl delete statefulsets --all --all-namespaces]
  Start Kubernetes Cluster:
    Ensure minikube is running [minikube start --driver=docker]
    [kubectl apply -f .\k8s\support-services\zipkin\]
    [kubectl apply -f .\k8s\support-services\rabbitmq\]
    [kubectl apply -f .\k8s\support-services\postgres\]
    [kubectl apply -f .\k8s\microservices\job-service\]
    [kubectl apply -f .\k8s\microservices\company-service\]
    [kubectl apply -f .\k8s\microservices\review-service\]
    to stop all run [minikube stop]
  Run in isolated terminals to get the respective urls for testing
    [minikube service job-service --url]
    [minikube service company-service --url]
    [minikube service review-service --url]
    [minikube service zipkin --url]


