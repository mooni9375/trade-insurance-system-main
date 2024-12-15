package com.tradeInsurance.domain.event;

import com.tradeInsurance.domain.entity.Review;

import java.time.ZonedDateTime;

public class ReviewCreatedEvent extends ReviewEvent {

    public ReviewCreatedEvent(Review review, ZonedDateTime createdAt) {
        super(review, createdAt);
    }
}
