package com.tradeInsurance.applicationservice.ports.input.message.listener.review;


import com.tradeInsurance.applicationservice.dto.message.ReviewResponse;

public interface ReviewResponseMessageListener {

    void onReviewApproved(ReviewResponse reviewResponse);

    void onReviewRejected(ReviewResponse reviewResponse);
}
