package com.tradeInsurance.domain;

import com.tradeInsurance.domain.dto.message.ReviewRequest;
import com.tradeInsurance.domain.ports.input.message.listener.app.ReviewRequestMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReviewRequestMessageListenerImpl implements ReviewRequestMessageListener {

    private final ReviewRequestHelper reviewRequestHelper;

    @Autowired
    public ReviewRequestMessageListenerImpl(ReviewRequestHelper reviewRequestHelper) {
        this.reviewRequestHelper = reviewRequestHelper;
    }

    @Override
    public void completeReview(ReviewRequest reviewRequest) {
        reviewRequestHelper.persistReview(reviewRequest);
    }
}
