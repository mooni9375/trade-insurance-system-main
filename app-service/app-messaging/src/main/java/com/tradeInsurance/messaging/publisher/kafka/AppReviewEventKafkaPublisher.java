package com.tradeInsurance.messaging.publisher.kafka;

import com.tradeInsurance.domain.config.AppServiceConfigData;
import com.tradeInsurance.domain.dto.message.AppReviewMessage;
import com.tradeInsurance.domain.ports.output.message.publisher.review.ReviewRequestMessagePublisher;
import com.tradeInsurance.messaging.kafka.model.ReviewRequestModel;
import com.tradeInsurance.messaging.kafka.producer.service.KafkaProducer;
import com.tradeInsurance.messaging.mapper.AppMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 현재는 AppDomainService.AppCreateCommandHandler.createApp() 에서 직접 호출
 */
@Slf4j
@Component
public class AppReviewEventKafkaPublisher implements ReviewRequestMessagePublisher {

    private final KafkaProducer<String, ReviewRequestModel> kafkaProducer;
    private final AppServiceConfigData appServiceConfigData;
    private final AppMessagingDataMapper appMessagingDataMapper;

    @Autowired
    public AppReviewEventKafkaPublisher(KafkaProducer<String, ReviewRequestModel> kafkaProducer,
                                        AppServiceConfigData appServiceConfigData,
                                        AppMessagingDataMapper appMessagingDataMapper) {
        this.kafkaProducer = kafkaProducer;
        this.appServiceConfigData = appServiceConfigData;
        this.appMessagingDataMapper = appMessagingDataMapper;
    }

    @Override
    public void publish(AppReviewMessage appReviewMessage) {

        ReviewRequestModel reviewRequestModel = appMessagingDataMapper.appReviewMessageToReviewRequestModel(appReviewMessage);

        try {
            kafkaProducer.send(appServiceConfigData.getReviewRequestTopicName(), reviewRequestModel);

            log.info("ReviewRequestModel sent to Kafka for order id: {}", reviewRequestModel.getAppId());
        } catch (Exception e) {
            log.error("Error while sending ReviewRequestModel " +
                            "to kafka with order id: {} and  error: {}", reviewRequestModel.getAppId(), e.getMessage());
        }


    }
}
