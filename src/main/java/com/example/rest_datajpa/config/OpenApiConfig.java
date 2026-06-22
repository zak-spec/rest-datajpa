package com.example.rest_datajpa.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * http://localhost:8080/swagger-ui/index.html
 */
@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI apiInfo(){
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot book API REST")
                        .description("Library API REST docs")
                        .version("1.0")
                        .termsOfService("https://www.google.com/")
                        .contact(new Contact().
                                name("hola")
                                .url("https://www.google.com/")
                                .email("hola@gmail.com"))
                );
    }
}
