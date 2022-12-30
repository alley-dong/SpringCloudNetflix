package com.example.eurekaclinent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class EurekaClinentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClinentApplication.class, args);
    }

}
