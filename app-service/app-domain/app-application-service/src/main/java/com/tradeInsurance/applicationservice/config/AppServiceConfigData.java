package com.tradeinsurance.appapplicationservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app-service")
public class AppServiceConfigData {

    private String reviewRequestTopicName;
}
