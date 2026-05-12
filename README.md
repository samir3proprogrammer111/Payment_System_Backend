# Payment System Backend

## Overview

A production-level backend project built using Java, Spring Boot, and MySQL for secure money transfer between accounts.

It supports sending and receiving money with real business logic and uses `@Transactional` to ensure safe and reliable transactions.

## Tech Stack

* Java
* Spring Boot
* MySQL
* Spring Data JPA
* Hibernate
* Lombok
* Maven

## Features

* Create and manage accounts
* Deposit and withdraw money
* Secure money transfer between accounts
* Transaction management using `@Transactional`
* Real-world business logic with proper validation
* RESTful API design

## Example

If one account sends money to another, the amount is deducted from one account and added to the other. If any step fails, the full transaction is rolled back automatically.

## Author

Backend project focused on production-level payment system development.
