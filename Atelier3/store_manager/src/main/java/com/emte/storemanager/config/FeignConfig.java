package com.emte.storemanager.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.openfeign.FeignContext;

@Configuration
@EnableFeignClients(basePackages = "com.emte.client")
public class FeignConfig {
    @Bean
    public FeignContext feignContext() {
        return new FeignContext();
    }
}
