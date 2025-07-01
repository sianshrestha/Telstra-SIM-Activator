# Telstra Sim Card Activator Microservice

A Spring Boot-based microservice for activating SIM cards, managing their activation state, and recording activation results in a database. This project includes behavior-driven development (BDD) tests using Cucumber to ensure functional correctness.

---

## Internship Context

This project was developed as part of the **Telstra Australia Virtual Internship** program. The internship focused on applying software engineering principles, clean code practices, and using tools like SonarQube for code quality analysis. The task involved building a microservice for SIM card activation, incorporating robust error handling, logging, and persistence.

---

## Features

* Activate functional SIM cards and record activation state in a database.
* Handle failed activation attempts for broken SIM cards gracefully.
* Persistence layer implemented with Spring Data JPA and H2 in-memory database.
* Behavior-driven tests using Cucumber to describe and verify service behavior.
* Logging with SLF4J for better traceability and debugging.
* Easy to extend for additional SIM card operations.

---

## Getting Started

### Prerequisites

* Java 17 or above (tested with JDK 23)
* Maven 3.x
* Git

### Running Locally

1. Clone the repository:

   ```bash
   git clone https://github.com/sianshrestha/Telstra-SIM-Activator.git
   cd Telstra-SIM-Activator
   ```

2. Build the project:

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   mvn spring-boot:run
   ```

4. Access the API (e.g., POST requests to activate SIM cards) at:

   ```
   http://localhost:8080/
   ```

5. To run the Cucumber BDD tests:

   ```bash
   mvn test
   ```

---

## Project Structure

* `/src/main/java` – Main application source code.
* `/src/test/java` – Unit and integration tests.
* `/src/test/resources/features` – Cucumber feature files describing behavior.
* `/src/test/java/stepDefinitions` – Step definitions implementing Cucumber tests.
* `pom.xml` – Maven configuration.

---

## Technologies Used

* Java 17+
* Spring Boot 2.7.x
* Spring Data JPA with H2 Database
* Cucumber JVM for BDD testing
* SLF4J + Logback for logging
* Maven for build automation

---

## Notes

* The H2 in-memory database resets on each application start for testing convenience.
* Logging replaces all `System.err` usages for improved error handling.
* Please ensure your Java and Maven versions are compatible.

---

## License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.
