# DCA COMPANY SERVICE

It's an RESTful service responsible for manipulating data of the companies. 

In this application, I embeded application server and database.

This is a application that run a Spring Boot application on Heroku. You can test the application [here](https://dca-company-web.herokuapp.com).

**Note that it is only running on a free dyno in the heroku, so it may take some time before it responds.**

## Used Technologies

**1. Java version 8.**

**2. Google Guava:** Used for define preconditions for the validations.

**3. JPA / Hibernate:** For mapping persistent entities in domains objects.

**4. Lombok:** For generation of the getters, setters, toString and hashCode.

**5. Bean Validations:** Framework for definion of rules of validation in JPA entities by annotations.

**6. Logback:** For generation of logs.

**7. Spring Data JPA:** Tecnologia utilizada gerar parte do código relacionado a camada de persistência. Na aplicação foi escrito os contratos de persistência, que realizam a criação dos comandos de manipulação (CRUD), consultas simples e complexas.

**8. Spring Web MVC:** Web Framework used as MVC solution for definition of components following the REST architecture model.

**9. Jackson:** API for convert the Java data in Json and vice versa.

**10. Thymeleaf:** Engine for generation of web pages based in the definition of templates and fragments. I use for the integration to Spring Web MVC and for create HTML pages of the application.

**11. JQuery:** Access the REST layer by Ajax, responsible by the data manipulation in the HTML structure.

**12. Bootstrap:** CSS solution to facilitate the construction of the layout of the HTML pages.

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

JDK - Java version 1.8.

Any Java IDE with support Maven.

Maven for build and dependecies.

###### After download the fonts, for execute the application execute the maven command:
```
$ mvn clean package spring-boot:run
```
###### For visualize the application, open the browser of your preference and type it:
```
http://localhost:8188/
```