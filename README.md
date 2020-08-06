# student-crud-operation-api

In this microservice I have created the GET,POST,DELETE,PUT rest endpoint for Student. I have used JPA and in-memory DB(H2), for documentation I have used swagger-ui.
Also, used actuator for production ready app it has health and info related endpoint.   
```URL 
http://localhost:8080/api/v1/students/
``` 
For more details refer API docs section.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.truelayer.pokemon.PokemonShakespeareDescriptionApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```mvn
mvn spring-boot:run
```

## Test Case Introduction
Integration test has created for the API and scope is not enough for unit testing, so we didn't create unit test case.
Generally if any method or unit of code has some manipulation or calculation logic then we need to create unit test cases.
You can run below maven command to execute integration test.
 
```mvn
mvn test
```

## API documentation details

Documentation done using swagger, after running the application you will find the documentation of endpoint at below mentioned URL

Swagger UI URL
```url
http://localhost:8080/api-docs-ui
```
Json API docs URL
```url
http://localhost:8080/v3/api-docs
```
## Actuator end point details
Actuator endpoints let you monitor and interact with your application. Spring Boot includes a number of built-in endpoints and lets you add your own. For example, the health endpoint provides basic application health information.
Actuator endpoint URL:

```url
http://localhost:8080/actuator
```

## DockerFile 

[DockerFile url](https://github.com/viveklad1/pokemon-shakespeare-description/blob/develop/Dockerfile) for reference 

## Available endpoint
Here are the details below for the available end point

```url
Base URL: http://localhost:8080/
GET /api/v1/students                     -> retrive all students
GET /api/v1/fetchStudents?id={ID}        -> retrive specific student based on Id
GET /api/v1/fetchStudents?class={class}  -> retrive students based on the class
POST /api/v1/students                    -> cteate student record
PUT /api/v1/students/{id}                -> update student data
DELETE /api/v1/students/{id}             -> delete student 
```


## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
