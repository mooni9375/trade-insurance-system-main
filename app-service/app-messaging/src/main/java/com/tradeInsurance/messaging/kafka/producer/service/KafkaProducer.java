package com.tradeInsurance.messaging.kafka.producer.service;

public interface KafkaProducer<String, V> {

    public void send(String topic, V message);
}
