package com.group.kamiloses.orderstreamapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "com.group.kamiloses.orderstreamapp.repository")
public class OrderStreamAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderStreamAppApplication.class, args);
    }

}
