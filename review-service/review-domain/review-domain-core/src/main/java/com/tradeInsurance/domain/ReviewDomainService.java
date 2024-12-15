package com.tradeInsurance.domain;

import com.tradeInsurance.domain.entity.Review;
import com.tradeInsurance.domain.event.ReviewCreatedEvent;

public interface ReviewDomainService {

    ReviewCreatedEvent validateAndInitiateReview(Review review);
}
