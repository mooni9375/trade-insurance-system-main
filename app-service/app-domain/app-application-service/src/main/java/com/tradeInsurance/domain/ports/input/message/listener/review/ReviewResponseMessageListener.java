package com.tradeInsurance.domain.ports.input.message.listener.review;


import com.tradeInsurance.domain.dto.message.ReviewResponse;

public interface ReviewResponseMessageListener {

    void onReviewApproved(ReviewResponse reviewResponse);

    void onReviewRejected(ReviewResponse reviewResponse);
}
