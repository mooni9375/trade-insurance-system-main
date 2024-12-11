package com.tradeInsurance.domain.ports.output.message.publisher.review;

import com.tradeInsurance.domain.dto.message.AppReviewMessage;

public interface ReviewRequestMessagePublisher {

    void publish(AppReviewMessage appReviewMessage);
}
