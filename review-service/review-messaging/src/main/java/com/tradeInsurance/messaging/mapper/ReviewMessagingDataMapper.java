package com.tradeInsurance.messaging.mapper;

import com.tradeInsurance.domain.dto.message.ReviewRequest;
import com.tradeInsurance.messaging.kafka.model.ReviewRequestModel;
import org.springframework.stereotype.Component;

@Component
public class ReviewMessagingDataMapper {

    public ReviewRequest reviewRequestMessageToReviewRequest(ReviewRequestModel reviewRequestModel) {

        return ReviewRequest.builder()
                .id(reviewRequestModel.getId())
                .appId(reviewRequestModel.getAppId())
                .appAmount(reviewRequestModel.getAppAmount())
                .importerCountryCode(reviewRequestModel.getImporterCountryCode().toString())
                .createdAt(reviewRequestModel.getCreatedAt())
                .build();
    }
}
