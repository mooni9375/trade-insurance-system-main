package com.tradeinsurance.appapplicationservice.ports.output.message.publisher.review;

import com.tradeinsurance.appapplicationservice.dto.message.AppReviewMessage;

public interface ReviewRequestMessagePublisher {

    void publish(AppReviewMessage appReviewMessage);
}
