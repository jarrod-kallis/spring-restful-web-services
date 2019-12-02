# spring-restful-web-services

[https://github.com/in28minutes/spring-web-services](https://github.com/in28minutes/spring-web-services)

## HATEOAS - Hypermedia as the Engine of Application State
Each resource returned contains hyperlinks to other related resources

## SWAGGER2

###Links
[http://localhost:8085/v2/api-docs](http://localhost:8085/v2/api-docs) <br />
[http://localhost:8085/swagger-ui.html](http://localhost:8085/swagger-ui.html) <br />
[http://localhost:8085/swagger-resources/configuration/ui](http://localhost:8085/swagger-resources/configuration/ui)  <br />
[http://localhost:8085/swagger-resources/configuration/security](http://localhost:8085/swagger-resources/configuration/security)  <br />

## Nov 2019
You cannot use Springboot 2.2.2 with Swagger2 & HATEOAS, so you need to downgrade to 2.1.3 (See pom.xml)

## Actuator Monitoring
[http://localhost:8085/actuator](http://localhost:8085/actuator) <br />
[http://localhost:8085/actuator/metrics/system.cpu.usage](http://localhost:8085/actuator/metrics/system.cpu.usage)

### HAL Browser
[http://localhost:8085/browser/index.html](http://localhost:8085/browser/index.html#/)

## H2 - In-Memory DB

[http://localhost:8085/h2-console](http://localhost:8085/h2-console) <br/>
DB URL: jdbc:h2:mem:testdb