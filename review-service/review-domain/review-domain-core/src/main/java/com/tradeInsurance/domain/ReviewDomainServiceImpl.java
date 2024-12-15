package com.tradeInsurance.domain;

import com.tradeInsurance.domain.entity.Review;
import com.tradeInsurance.domain.event.ReviewCreatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class ReviewDomainServiceImpl implements ReviewDomainService {

    private static final String UTC = "UTC";

    @Override
    public ReviewCreatedEvent validateAndInitiateReview(Review review) {

        review.validateReview();
        review.initializeReview();

        log.info("review with id: {} is initialized", review.getId().getValue());

        return new ReviewCreatedEvent(review, ZonedDateTime.now(ZoneId.of(UTC)));
    }
}
