package com.tradeInsurance.domain.ports.input.message.listener.app;

import com.tradeInsurance.domain.dto.message.ReviewRequest;

public interface ReviewRequestMessageListener {

    void completeReview(ReviewRequest reviewRequest);

}
