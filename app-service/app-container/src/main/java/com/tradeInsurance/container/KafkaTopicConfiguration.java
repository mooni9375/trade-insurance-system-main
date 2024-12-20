package com.tradeInsurance.container;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic reviewRequestTopic() {
        return TopicBuilder.name("review-request-topic")
                .build();
    }
}
