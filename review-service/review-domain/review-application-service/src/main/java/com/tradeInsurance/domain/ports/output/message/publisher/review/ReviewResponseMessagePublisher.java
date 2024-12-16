package com.tradeInsurance.domain.ports.output.message.publisher.review;

import com.tradeInsurance.domain.dto.message.ReviewAppMessage;

public interface ReviewResponseMessagePublisher {

    void publish(ReviewAppMessage reviewAppMessage);
}
