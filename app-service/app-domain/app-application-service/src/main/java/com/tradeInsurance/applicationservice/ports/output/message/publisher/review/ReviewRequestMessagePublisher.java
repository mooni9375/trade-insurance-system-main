package com.tradeInsurance.applicationservice.ports.output.message.publisher.review;

import com.tradeInsurance.applicationservice.dto.message.AppReviewMessage;

public interface ReviewRequestMessagePublisher {

    void publish(AppReviewMessage appReviewMessage);
}
