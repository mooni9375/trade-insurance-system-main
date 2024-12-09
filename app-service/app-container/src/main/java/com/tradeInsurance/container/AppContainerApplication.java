package com.tradeInsurance.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.tradeInsurance.application"})
public class AppContainerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppContainerApplication.class, args);
    }

}
