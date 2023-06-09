package com.emte.cardmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.emte.*")
@EntityScan("com.emte.model")
@EnableFeignClients(basePackages = {"com.emte.client","com.emte.cardmanager.controller"})
public class CardManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardManagerApplication.class, args);
    }

}
