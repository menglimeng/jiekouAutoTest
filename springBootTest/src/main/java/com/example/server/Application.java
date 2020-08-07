package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController("com.example.server")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
