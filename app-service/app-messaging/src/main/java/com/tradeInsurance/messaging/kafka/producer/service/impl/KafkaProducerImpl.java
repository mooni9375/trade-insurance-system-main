package com.tradeInsurance.messaging.kafka.producer.service.impl;

import com.tradeInsurance.messaging.kafka.producer.exception.KafkaProducerException;
import com.tradeInsurance.messaging.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaProducerImpl<String, V> implements KafkaProducer<String, V> {

    private final KafkaTemplate<String, V> kafkaTemplate;

    @Autowired
    public KafkaProducerImpl(KafkaTemplate<String, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topic, V message) {

        try {
            kafkaTemplate.send((java.lang.String) topic, message);
            log.info("Message sent to topic {}: {}", topic, message);
        } catch (KafkaException e) {
            log.error("Error on kafka producer with message: {} and exception: {}", message, e.getMessage(), e);
            throw new KafkaProducerException("Error on kafka producer with message: " + message);
        }
    }
}
