## Introduction
This implementation uses an in memory database to store the drone and medications. Since there is no endpoint to register medications, the medications are loaded via a service method
call that relies on a json file with the desired information. The Aim of using the service is to allow for validation of the medications before saving them to the database.

## Running the application
### Prerequisites
This application requires Java 17 and Maven to be installed on your machine.

### Build the .jar file
To build the .jar file, run the following command in the root directory of the project:
```mvn clean install ```
This will create a .jar file in the target directory, but it also has the added benefit of running the tests.

### Run the application
To run the application, run the following command in the root directory of the project:
```java -jar target/drone-0.0.1-SNAPSHOT.jar```

## Test the Endpoints
With the previous steps completed, you can now test the endpoints using a tool like Postman or curl. The base url for the endpoints is `http://localhost:8080/api/v1/`


## Developer Note
In regard to error handling, the application uses a custom exception handler to catch exceptions and return a custom error message.
but I also made efforts to show a different kind of error handling in the delivery service, one that does not use the custom exception handler but aims to have a fixed response for every possible outcome.

