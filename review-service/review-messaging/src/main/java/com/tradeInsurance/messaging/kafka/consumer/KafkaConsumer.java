package com.tradeInsurance.messaging.kafka.consumer;

public interface KafkaConsumer<T> {

    void receive(T t);
}
