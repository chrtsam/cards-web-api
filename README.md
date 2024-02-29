# Cards RESTful web service

# Introduction
This web api allows users to create and manage tasks in the form of cards

## Build from source
### Prerequisites
In order to build the project you should have a machine with java 21 installed, maven spring boot and a running MySQL database. Version 8.0 is where the project is tested. As an alternative you can use the include mysql docker container ( docker should be installed in your system ).

The project is a maven project so in orde to build the source run the following
```
TODO maven command to build
```

## Execute
In order to execute the project you should have a running mysql database, and set the connection properties in the folowing file
```

```
or else you can use the bundled mysql container with preexisting data

TO run the project execute the following command

```
TODO maven command to run
```

After a successfull run you can visit the following url to read about how to consume the API
```
http://localhost:8080/swagger-ui/index.html
```

### Run the project from a container
You can also run the project in a fully containerazied manner meaning that only docker is a prerequisite.

Run the following bash script which will build the source and spin up the web api.
```

```
The url for the documentation remains the same 
```
http://localhost:8080/swagger-ui/index.html
```


## Future work
- Fully support HATEOAS
- Implement PACTH endpoint for updates
- Add unit tests
- Add more integration tests
- Increase the operators support in the search
- Improve indexing (maybe some database design changes)
- Improve Documentation
- Version resources by using ETags