# SWA Developer Prototype Application

This project is an example application built using Spring Boot. The application works by reading data from a PostgreSQL database and a JSON file. It includes both REST and SOAP web services and stores data in an in-memory cache.

## Features
- Project created using Maven.
- Reading data from a JSON file or PostgreSQL database and loading it into an in-memory cache.
- RESTful API for GET, POST, PUT, and DELETE operations.
- Returning all subscribers and specific subscribers through SOAP web services.
- Periodic data updates.

## Requirements

- Java 8 or higher
- Maven 3.6.0 or higher
- PostgreSQL database

## Technologies Used

- Spring Boot
- Spring Data JPA
- Spring Web Services
- JAXB
- H2 Database (for testing)
- Maven

## Setup

1. Clone the project:
    ```bash
    git clone https://github.com/elauysal/subscriber.git
    
    ```

2. Configure the `application.properties` file:
   Open the `src/main/resources/application.properties` file and set up the PostgreSQL database settings:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
    spring.datasource.username=postgres
    spring.datasource.password=123456
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Download Maven dependencies and build the project:
    ```bash
    mvn clean install
    ```

4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

5. Check the `src/main/resources/data.json` file to load data from the JSON file when the application starts.

## API Usage

### REST Services

#### Get All Subscribers
- URL: `GET /api/subscribers/getAllSubscribers`
- Example Usage: `http://localhost:8080/api/subscribers/getAllSubscribers`

#### Get Active Subscribers
- URL: `GET /api/subscribers/getActiveSubscribers`
- Example Usage: `http://localhost:8080/api/subscribers/getActiveSubscribers`

#### Get Subscriber by ID
- URL: `GET /api/subscribers/getSubscriberById/{id}`
- Example Usage: `http://localhost:8080/api/subscribers/getSubscriberById/1`

#### Create a New Subscriber
- URL: `POST /api/subscribers/subscriber`
- Body (JSON):
    ```json
    {
        "id": 4,
        "name": "Stephany Kirk",
        "msisdn": "905558887965",
        "status": "ACTIVE"
    }
    ```
- Example Usage: `http://localhost:8080/api/subscribers/subscriber`

#### Update a Subscriber
- URL: `PUT /api/subscribers/subscriber/{id}`
- Body (JSON):
    ```json
    {
        "id": 4,
        "name": "Stephany Kirk",
        "msisdn": "905558887965",
        "status": "ACTIVE"
    }
    ```
- Example Usage: `http://localhost:8080/api/subscribers/subscriber/4`

#### Delete a Subscriber
- URL: `DELETE /api/subscribers/subscriber/{id}`
- Example Usage: `http://localhost:8080/api/subscribers/subscriber/4`

### SOAP Services

You can access SOAP services at `http://localhost:8080/ws/subscribers.wsdl`.

#### Get All Subscribers
- Request:
    ```xml
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sub="http://example.com/subscriber">
       <soapenv:Header/>
       <soapenv:Body>
          <sub:getAllSubscribersRequest/>
       </soapenv:Body>
    </soapenv:Envelope>
    ```
- Response:
    ```xml
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sub="http://example.com/subscriber">
       <soapenv:Header/>
       <soapenv:Body>
          <sub:getAllSubscribersResponse>
             <sub:subscriber>
                <sub:id>1</sub:id>
                <sub:name>Stephan King</sub:name>
                <sub:msisdn>905552551122</sub:msisdn>
                <sub:status>ACTIVE</sub:status>
             </sub:subscriber>
             <!-- Other subscribers -->
          </sub:getAllSubscribersResponse>
       </soapenv:Body>
    </soapenv:Envelope>
    ```

#### Get Active Subscribers
- Request:
    ```xml
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sub="http://example.com/subscriber">
       <soapenv:Header/>
       <soapenv:Body>
          <sub:getActiveSubscribersRequest/>
       </soapenv:Body>
    </soapenv:Envelope>
    ```
- Response:
    ```xml
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sub="http://example.com/subscriber">
       <soapenv:Header/>
       <soapenv:Body>
          <sub:getActiveSubscribersResponse>
             <sub:subscriber>
                <sub:id>1</sub:id>
                <sub:name>Stephan King</sub:name>
                <sub:msisdn>905552551122</sub:msisdn>
                <sub:status>ACTIVE</sub:status>
             </sub:subscriber>
             <!-- Other active subscribers -->
          </sub:getActiveSubscribersResponse>
       </soapenv:Body>
    </soapenv:Envelope>
    ```

#### Get Subscriber by ID
- Request:
    ```xml
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sub="http://example.com/subscriber">
       <soapenv:Header/>
       <soapenv:Body>
          <sub:getSubscriberByIdRequest>
             <sub:id>1</sub:id>
          </sub:getSubscriberByIdRequest>
       </soapenv:Body>
    </soapenv:Envelope>
    ```
- Response:
    ```xml
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sub="http://example.com/subscriber">
       <soapenv:Header/>
       <soapenv:Body>
          <sub:getSubscriberByIdResponse>
             <sub:subscriber>
                <sub:id>1</sub:id>
                <sub:name>Stephan King</sub:name>
                <sub:msisdn>905552551122</sub:msisdn>
                <sub:status>ACTIVE</sub:status>
             </sub:subscriber>
          </sub:getSubscriberByIdResponse>
       </soapenv:Body>
    </soapenv:Envelope>
    ```

## Logging

All API calls and SOAP requests are logged to the `app.log` file. Logs are stored in the following format:
```plaintext
11.06.2021 10:19:44 /getAllSubscribers[GET]							
11.06.2021 10:19:46 /getActiveSubscribers[GET]
11.06.2021 10:22:47 /getSubscriberById[GET] id=2