# Project Name

> A brief description of your Java Spring Boot project.

## Table of Contents

- [About](#about)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Database Configuration](#database-configuration)
- [Build & Deployment](#build--deployment)
- [License](#license)

## About

This is a Java Spring Boot application that provides the RESTful API for a Product management System

## Tech Stack

- Java 21
- Spring Boot 3.4.5
- Maven
- JPA / Hibernate 
- PostgreSQL
- Swagger (for API documentation)

### Prerequisites

- Java 21
- Apache Maven 3.9.6
- PostgreSQL 16.2
- IDE IntelliJ

### Run the project with Maven Command

- mvn spring-boot:run -Dspring-boot.run.profiles=local


### Run the projects with the Jar

- mvn clean install -DskipTests
- java -jar target/product-0.0.1-SNAPSHOT.jar --spring.profiles.active=local