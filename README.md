# DCA COMPANY SERVICE

It's a RESTful service responsible for mmanipulating companies' data. 

In this application, there is an embedded application server and a database.

This application is running under Spring Boot and it's deployed on Heroku. You can test the application [here](https://dca-company-web.herokuapp.com).

**Note that it is running on a free dyno in the heroku, so it may take some time before it responds.**

## Used Technologies

**1. Java version 8.**

**2. Google Guava:** It's used for define preconditions for the validations.

**3. JPA / Hibernate:** Mapping persistent entities in domains objects.

**4. Lombok:** Generation of the getters, setters, toString and hashCode.

**5. Bean Validations:** Framework used for rules validations in JPA entities using annotations.

**6. Logback:** Generation of logs.

**7. Spring Data JPA:** It's used to generate part of the code of the persistence layer. In the application was described the persistence contracts,performing the creation of manipulation's command (CRUD) and queries simple and complex.

**8. Spring Web MVC:** Web Framework used as MVC solution for definition of components following the REST architecture model.

**9. Jackson:** API for convert the Java data in Json and vice versa.

**10. Thymeleaf:** Engine for generation of web pages based in the definition of templates and fragments. I use it for the integration between Spring Web MVC and HTML pages.

**11. JQuery:** Access the REST layer by Ajax, responsible for the data manipulation in the HTML structure.

**12. Bootstrap:** CSS solution to facilitate the layer view construction.

## Additional Technologies

**Database:** HSQLDB embeded in the application. The database is created during the startup of the application. I offer the file src/main/resources/data.sql where we have the script for insert the data in the tables of the database. This data are used in the components of the tests (mock) and they are disponible for the web pages. In the end of the execution is destroyed the database.

**Tests:** The tests are defined as use case of the Junit. The tests of rest services have: Spring Web MVC for mock of the web infrastructure; JsonPath e hamcrest are used for access and assertions in the Json conteudo. The tests have been made available in the structure: src/test/java.

**Spring Boot:** Technology used for create a embeded enviroment of the execution, I used this technology for simplify the use of the Spring and for controle the scope of the database. In the file src/main/resources/application.yml have properties of the Spring Boot for the project.

**Tomcat embutido:** Provided by Spring Boot.

**Maven:** Life cycle management and project build.

## Considerations

The labels of the pages were defined in the file src/main/resources/messages_en.properties.

The messages of the beans validations were defined in the file src/main/resources/ValidationMessages.properties.

The integration of the pages with the data occurs asynchronously, always making access to REST services available.

## Usage In Local Machine

###### Pré-requisitos
```
JDK - Java version 1.8.

Any Java IDE with support Maven.

Maven for build and dependecies.
```
###### After download the fonts, for execute the application execute the maven command:
```
$ mvn clean package spring-boot:run
```
###### For visualize the application, open the browser of your preference and type it:
```
http://localhost:8188/
```

## Final Considerations

###### Suggestions for Authentication's REST API 

**oAuth2:** I would use because it provides a standardized mechanism for Identity Management, where all system components can interact in a safe way, usually where a client application needs access to a protected resource, acting in place of the user. They provides a token for access for allow the client application can access the rest apis

###### Suggestions for redundant services

I would create micro services to distribute in multiples servers, so that can perform load balancing and the redundancy of the micro services, we could use a gateway, which would be responsible for receiving the request and distribute the load among the servers that are up.
This gateway (which could be a Eureka) would be responsible can check on which  server is on and free to make the call to service and if you had one server off, it would direct calls to the other servers, that it would also facilitate the deploy the services because we could stop a server perform the deploy and if it's all ok, would stop the other server and make deploy in the server and so on would.