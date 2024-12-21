package com.tradeInsurance.messaging.listener.kafka;

import com.tradeInsurance.domain.ports.input.message.listener.app.ReviewRequestMessageListener;
import com.tradeInsurance.messaging.kafka.consumer.KafkaConsumer;
import com.tradeInsurance.messaging.kafka.model.ReviewRequestModel;
import com.tradeInsurance.messaging.mapper.ReviewMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReviewRequestKafkaListener implements KafkaConsumer<ReviewRequestModel> {

    private final ReviewRequestMessageListener reviewRequestMessageListener;
    private final ReviewMessagingDataMapper reviewMessagingDataMapper;

    @Autowired
    public ReviewRequestKafkaListener(ReviewRequestMessageListener reviewRequestMessageListener,
                                      ReviewMessagingDataMapper reviewMessagingDataMapper) {
        this.reviewRequestMessageListener = reviewRequestMessageListener;
        this.reviewMessagingDataMapper = reviewMessagingDataMapper;
    }


    @Override
    @KafkaListener(topics = "review-request-topic", groupId = "tradeInsurance")
    public void receive(ReviewRequestModel reviewRequestModel) {
        System.out.println("***** reviewRequestModel.toString() = " + reviewRequestModel.toString());
    }
}
