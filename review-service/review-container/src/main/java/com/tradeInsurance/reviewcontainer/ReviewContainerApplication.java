package com.tradeInsurance.reviewcontainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
//@EnableJpaRepositories(basePackages = {"com.tradeInsurance.dataaccess"})
//@EntityScan(basePackages = {"com.tradeInsurance.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.tradeInsurance")
public class ReviewContainerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewContainerApplication.class, args);
    }

}
