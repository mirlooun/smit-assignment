# Test assignment for SMIT Java Full-Stack developer position

## Prerequisites

* Docker

## Installation

*Note: Spring Boot applications are already pre-built, so there's no need to use IntelliJ IDEA or Gradle to build the projects.*

To start the docker containers use the following command:

```bash
docker compose up
```

After the execution of this command 5 docker containers will be created:

1. *client* - Angular web application that runs on 8080 port
2. *api* - Spring boot main server instance that runs on 8090 port
3. *email-processor* - Spring boot microservice responsible for email sending logic that runs on 8091 port
4. *rabbitmq* - RabbitMQ instance that runs on 5672 port
5. *database* - PostgreSQL database that runs on 5432 port

*Note: Please ensure that these ports are available on your machine; otherwise, the application won't start up.*

## Credits

Developer - Ilja Ivanov

## License

Distributed under the MIT License. See LICENSE.txt for more information.
