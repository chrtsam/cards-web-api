# Cards RESTful web service

# Introduction
This web api allows users to create and manage tasks in the form of cards

## Build from source
### Prerequisites
In order to build the project you should have a machine with java installed, maven spring boot and a running MySQL database. Version is where the project is tested. As an alternative you can use the include mysql docker container ( docker should be installed in your system ). The project has been tested with
- Java 21.0.2
- Maven 3.3.9
- MySQL 8.0

The project is a maven project so in order to build the source run the following from the root folder:
```
mvn clean package
```

For the integration tests an in memory database is used.

## Execute

In order to execute the project you should have a running mysql database.
Before building the application you can set the database configuration using environment variables (powershell specific):
```
$env:MYSQL_DB_HOST = '<database hostname e.g localhost>'
$env:MYSQL_DB_PORT = '3306'
$env:MYSQL_DB_USERNAME = '<database username>'
$env:MYSQL_DB_PASSWORD = '<database password>'
```

The database schema will be automatically created. If you would like to create the schema on demand run the following (powershell specific):
```
$env:FLYWAY_URL = '<database url e.g jdbc:mysql://localhost:3306/card_db'> 
$env:FLYWAY_USER = '<database username>'
$env:FLYWAY_PASSWORD = '<database password>'
mvn flyway:migrate
```

Finaly to run the project execute the following command:

```
java -jar target\api-1.0.jar
```

After a successfull run you can visit the following url to read about how to consume the API
```
http://localhost:8080/swagger-ui/index.html
```


### Run the project from a container
You can also run the app using docker without the need to build the app manually.
As a prerequisite you hould have docker installed.

Start the containers using:
```
docker-compose up -d
```

During the start of the database container there may be an error regarding the port which is in use. If such an error occurs during the container creation, you can use a different port or alternative you can execute the following commands in order to free the 3306 port:
```
net stop winnat
netsh int ipv4 add excludedportrange protocol=tcp startport=3306 numberofports=1
net start winnat
```

After a successfull run you can visit the following url to read about how to consume the API
```
http://localhost:8080/swagger-ui/index.html
```


## Future work
- Fully support HATEOAS
- Implement PACTH endpoint for updates
- Add unit tests
- Add more integration tests
- Improve indexing (maybe some database design changes)
- Improve Documentation
- Version resources by using ETags