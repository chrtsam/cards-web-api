# Cards RESTful web service

# Introduction
This web api allows users to create and manage tasks in the form of cards

## Build from source
### Prerequisites
In order to build the project you should have a machine with java installed, maven spring boot and a running MySQL database. Version is where the project is tested. As an alternative you can use the include mysql docker container ( docker should be installed in your system ). The project has been tested with
- Java 21.0.2
- Maven 3.3.9
- MySQL 8.0

In order to execute the project you should have a running mysql database. For the integration tests an in memory database is created. If there is no MySQL available you can start one using the include image
```
cd test-bundle\
docker-compose up
```
In case of an error during the container creation, you can execute the following commands in order to free the 3306 port
```
net stop winnat
netsh int ipv4 add excludedportrange protocol=tcp startport=3306 numberofports=1
net start winnat
```

The project is a maven project so in order to build the source run the following from the root folder
```
mvn clean package
```

## Execute
In order to execute the project you should have a running mysql database, and set the connection properties in the folowing file
```
src\main\resources\application.properties
```
You should also initiate the database schema using the following commands(powershell specific)
```
$env:FLYWAY_URL = 'jdbc:mysql://localhost:3306/card_db'
$env:FLYWAY_USER = 'user'
$env:FLYWAY_PASSWORD = 'password'
mvn flyway:migrate
```

Finaly to run the project execute the following command

```
java -jar target\api-1.0.jar
```

After a successfull run you can visit the following url to read about how to consume the API
```
http://localhost:8080/swagger-ui/index.html
```

### Run the project from a container
TODO

## Future work
- Fully support HATEOAS
- Implement PACTH endpoint for updates
- Add unit tests
- Add more integration tests
- Increase the operators support in the search
- Containarize the application, so no need of java and maven depndencies are needed
- Improve indexing (maybe some database design changes)
- Improve Documentation
- Version resources by using ETags