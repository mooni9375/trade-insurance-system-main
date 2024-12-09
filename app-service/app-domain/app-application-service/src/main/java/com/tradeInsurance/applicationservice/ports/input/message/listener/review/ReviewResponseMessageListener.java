package com.tradeinsurance.appapplicationservice.ports.input.message.listener.review;

import com.tradeinsurance.appapplicationservice.dto.message.ReviewResponse;

public interface ReviewResponseMessageListener {

    void onReviewApproved(ReviewResponse reviewResponse);

    void onReviewRejected(ReviewResponse reviewResponse);
}
