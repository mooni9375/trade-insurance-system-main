package com.tradeInsurance.messaging.mapper;

import com.tradeInsurance.domain.dto.message.AppReviewMessage;
import com.tradeInsurance.messaging.kafka.model.ReviewRequestModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppMessagingDataMapper {

    public ReviewRequestModel appReviewMessageToReviewRequestModel(AppReviewMessage appReviewMessage) {

        return ReviewRequestModel.builder()
                .id(UUID.randomUUID().toString())
                .appId(appReviewMessage.getAppId())
                .appAmount(appReviewMessage.getAppAmount())
                .importerCountryCode(appReviewMessage.getImporterCountryCode())
                .createdAt(appReviewMessage.getCreatedAt())
                .build();

    }

}
