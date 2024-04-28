package com.estudos.restfullwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfullWebServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfullWebServicesApplication.class, args);
    }

}
/*http://localhost:8080/swagger-ui/index.html#/
        <!-- https://www.bezkoder.com/spring-boot-swagger-3/ -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.0.3</version>
        </dependency>

para pegarmos o json:
http://localhost:8080/v3/api-docs

*/
