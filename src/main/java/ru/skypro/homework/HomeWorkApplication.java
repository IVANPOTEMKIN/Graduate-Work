package ru.skypro.homework;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class HomeWorkApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeWorkApplication.class, args);
    }
}