Advance Cloud Computing - Big Project.
By Nagarajan, Sathish and Aravindhan Venkituswamy.

Cloud Computing - IOT Simulation with SaaS, Microservices, Containers and Kubernetes

Abstract:
Cloud computing, often referred to as simply “the cloud,” is the delivery of
on-demand computing resources — everything from applications to data centers —
over the internet on a pay-for-use basis. In this project, we explore the concepts of
Cloud Computing with Internet of Things, Dashboard Services simulation,
Software as a Service, Microservices, Containers and Kubernetes.

Index Terms:
System Design, application components, challenges, learnings

-----------------------------------------------------------------------------------------------

System Design:

https://goo.gl/NihstB

-----------------------------------------------------------------------------------------------

Application Components:

Dashboard: Display the simulated weather sensor data and future weather predictions

Middleware: 
Master Container - Spring Boot Micro Service to capture the data from available sensor stubs and publish the data to dashboard.
Data Analysis Container - Flask Micro Service to do future predictions
Database : Middleware stores and does analysis on data in Mongodb.
Deployment Tool : Container orchestration done via Kubernetes in GCP(Google Cloud Platform) .

-----------------------------------------------------------------------------------------------

Detailed report on components:

Data Analysis:
Flask web framework is used for the REST purpose. Machine Learning
Model Autoregressive fractionally Integrated Moving Average or simply ARIMA
is trained with the IOT historical collected data. The trained model is used to
predict the future weather data. Minimum, Maximum and Average temperatures
predicted using the model. Predicted results exposed as a REST endpoint and
consumed by Spring boot middleware.

Master Container:
Spring boot makes it easy to run spring based stand alone, production grade applications with its rich inbuilt methods. 
It is the main Controller of the application. 
It pulls out the IOT sensor data from various stations sends it to various containers as per system design.

Created various REST API endpoints for communication with different components - Lightweight architecture. 
Used different data files to simulate data point collection from different IoT devices.

Spring Boot Endpoints:
Endpoints enable us to establish pipeline between different components of
our application. Some of the spring boot endpoints are

● To fetch weather data from stub and send future predicted dataset for graph
to dashboard.
● To access MongoDB to process use cases, update & provide data on
demand.
● 0.0.0.0:8080/read, 0.0.0.0:8080/consume

Dashboard:
UI designed in bootStrap.js and used canvas.js to plot the graph of weather
readings from different stations. Number of options were provided like selecting
location, selecting temperature (average, minimum, maximum), number of days to
plot (10,20,30) and an option for Fahrenheit or Celsius.
Containers and Orchestration:

Dockers:
Docker uses a client-server architecture. Docker is a stable version of
containers with rich documentation. Docker client talks to the Docker daemon,
which does the heavy lifting of building, running, and distributing our Docker
containers. The Docker client and daemon can run on the same system, or you can
connect a Docker client to a remote Docker daemon.
All our components are fed into container. Basic idea is to
1. Use docker compose and build an image of our application.
2. Tag that image and push it to docker hub repository(just like github)

Kubernetes:
Kubernetes is an open-source system for automating deployment, scaling,
and management of containerized applications. It is very useful continuous
deployment by providing proper versioning in releases
Configuration Files:
Service.yaml:
apiVersion: v1
kind: Service
metadata:
name: weather-spring-boot-service
spec:
type: LoadBalancer
ports:
-
port: 8051
targetPort: 8051
selector:
app: weather-spring-boot
type: NodePort
Deployment.yaml:
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
name: weather-spring-boot-deployment
spec:
replicas: 2 # tells deployment to run 2 pods matching the template
minReadySeconds: 10
strategy:
type: RollingUpdate
4
Advance Cloud Computing - Big Project Nagarajan, Sathish and Aravind Venkit
rollingUpdate:
maxUnavailable: 1
maxSurge: 1
template:
metadata:
labels:
app: weather-spring-boot
spec:
containers:
- name: weather-spring-boot
image: ngopal/weather-flask-app:1.0.4
ports:
- containerPort: 8051

-----------------------------------------------------------------------------------------------

Learnings:
Spring boot: To build a stand alone application using spring boot with REST API
services.
Flask: To build a python based weather prediction tool with REST API endpoint.
Dockers: To dockerize different microservices.
K8s: For container orchestration - Auto deployment, load balancing,
communication between dockers and fault tolerance.
Mongodb: For database usage.
Swager: Tool used for API listing.
Dashboard: HTML, bootstrap and canvas.js for web UI construction.
Google cloud: Cloud platform were the Kubernetes engine is used.

-----------------------------------------------------------------------------------------------

Challenges:
1. Most of the tech stack were new to us, so it really took a long time to learn
stuffs.
2. Different ML models like tensorflow, keras and ARIMA were chosen.
3. Took a long processing time for the training of prediction models.
4. For each change we need to do Docker compose to docker images and basic
installation required for each microservices image.
5. Establishing communication with different dockers with in k8s.
6. In Kubernetes usage we faced lot of issues like load balancing between
pods, DNS access of the app url.
7. To host a dynamic dashboard in google cloud-storage.
