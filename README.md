# Pedido-API

This REST API project was developed for a delivery application. In this API the following tools were used:

- Java 11
- Spring Boot 2.7.3
- Banco de Dados: Mysql
- Maven
- OpenAPI 3.0 ( Swagger )
- Flyway ( Migrações )

## Steps to execution

### Pre-Requirements

- Java 11 ( Installed on the machine or server )
- Mysql ( Installed on the machine or server )
- Maven ( Installed on the machine or server )

### First Step

- Create the application database, as shown below:

```CREATE DATABASE restaurante```

OBS: The base's name and path can be changed.

### Second Step

- Set the bank settings in the *application.properties* file

### Third Step

- Run the following commands:

``mvn install``

`` mvn package``

- After running the commands, initialize the application from the IDE or via the java command line.
## Documentação da API

- The documentation with the API routes is available at the link below.

``http://localhost:8081/swagger-ui/#/``