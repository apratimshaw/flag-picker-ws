# Flag Picker RESTful Web Services

This sample application uses Spring Boot to build a generic set of services to retrieve flag emoji for select countries. 

  - load data from json
  - allow retrieval of all countries and flags
  - filter by continent
  - filter by country
  - test the search function for various inputs
  - enable audit trail for data changes
  - enable metrics for services 
  - DDL for Tables

# Governing Principles

  - DRY  
  - YAGNI (limit scope to specified requirements and add capabilities as new requirements are provided) 
  - Provide for future extension (API versioning, additional country data)
  - Test under the skin
  - Sanitize inputs
  - Self-documenting code
  - Handle Exception gracefully and uniformly
  

 # Quick Start Guide (Eclipse)

Clone the project
 ```sh
$ git clone https://github.com/apratimshaw/flag-picker-ws.git
``` 
Use Eclipse IDE to Import Project
  - File > Import > Maven > Existing Maven Project > Next
  - Select file path to the flag-picker-ws project
  - Done
  
Use Eclipse to build and run the project
  - Make sure you have the maven plugin installed
  - Build the project (if your IDE is not set up to build automatically)
  - The Spring Boot Application is at /flag-picker-ws/src/main/java/flagpicker/FlagPickerApplication.java
  - Right Click on the Spring Boot Application File and click on "Run as java application"
  - Done
  
Use Postman to test the application
  - The postman collection is at /flag-picker-ws/FlagPicker.postman_collection.json
  - Import it on Postman
  - Execute
  
# URLs for locally running application
CURL Example - Get flag for country
 ```sh
curl -i  -H "Content-Type: application/json" -X GET http://localhost:8080/v1/countries?countryName=India
``` 
Get Countries By Name
```sh
http://localhost:8080/v1/countries?countryName=India
```
Get Countries By Continent
```sh
http://localhost:8080/v1/countries?continentName=Asia
```
Get Flag for Country
```sh
http://localhost:8080/v1/countries/flag?countryName=India
```
# H2 DB (used for portability of the project only)
URL to access H2 on localhost
```sh
http://localhost:8080/h2-console/
```
- JDBC URL - jdbc:h2:mem:testdb
- User Name - sa
- Password - (empty)
  
# Metrics using Actuator
```sh
http://localhost:8080/actuator/
```

#Database Schema DDL File
- The database schema file (h2) is located at /flag-picker-ws/DB_SCHEMA_DDL.sql
