package com.tradeInsurance.domain.ports.output.message.publisher.review;

import com.tradeInsurance.domain.dto.message.ReviewRequest;

public interface ReviewResponseMessagePublisher {

    void publish(ReviewRequest reviewRequest);
}
