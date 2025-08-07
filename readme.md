# Parking Lot System (Java)

A minimal, framework-free parking lot system built with Java 21 and Maven.

## Features

- Start/end parking tickets
- Price calculation (hourly/daily)
- Validation & exception handling
- In-memory storage with thread safety
- Modular & testable design

## Tech Stack

- Java 21
- Maven
- JUnit 5, Mockito
- Lombok, SLF4J
- JaCoCo (coverage)

## Build & Test

```bash
mvn clean install
mvn test
mvn verify   # for coverage report
````

## Modules

* `TicketService` → Parking logic
* `CalculationService` → Price formula
* `ValidationService` → Input checks
* `InMemoryDatabase` → ConcurrentMap-based store
* `AppConfig` → Config constants
