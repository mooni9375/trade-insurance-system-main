package com.tradeInsurance.domain.event;

import com.tradeInsurance.commonlibrary.domain.event.DomainEvent;
import com.tradeInsurance.domain.entity.Review;

import java.time.ZonedDateTime;

public class ReviewEvent implements DomainEvent<Review> {

    private final Review review;
    private ZonedDateTime createdAt;

    public ReviewEvent(Review review, ZonedDateTime createdAt) {
        this.review = review;
        this.createdAt = createdAt;
    }

    public Review getReview() {
        return review;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
