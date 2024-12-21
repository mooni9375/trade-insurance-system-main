package com.tradeInsurance.domain;

import com.tradeInsurance.domain.dto.create.CreateReviewCommand;
import com.tradeInsurance.domain.dto.create.CreateReviewResponse;
import com.tradeInsurance.domain.event.ReviewCreatedEvent;
import com.tradeInsurance.domain.mapper.ReviewDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReviewCreateCommandHandler {

    private final ReviewRequestHelper reviewRequestHelper;
    private final ReviewDataMapper reviewDataMapper;

    @Autowired
    public ReviewCreateCommandHandler(ReviewRequestHelper reviewRequestHelper,
                                      ReviewDataMapper reviewDataMapper) {
        this.reviewRequestHelper = reviewRequestHelper;
        this.reviewDataMapper = reviewDataMapper;
    }

    /**
     * Separate Transaction processing and Event publishing.
     *  Transaction processing : Processed by Helper
     *  Event publishing : Published by Handler
     */
    public CreateReviewResponse createReview(CreateReviewCommand createReviewCommand) {


        // Transaction processing
//        ReviewCreatedEvent reviewCreatedEvent = reviewRequestHelper.persistReview(createReviewCommand);
//        log.info("Review is created with id: {}", reviewCreatedEvent.getReview().getId().getValue());

        // Event publishing
        // Mapping : ReviewCreatedEvent -> ReviewAppMessage
//        ReviewAppMessage reviewAppMessage = reviewDataMapper.reviewCreatedEventToReviewAppMessage(reviewCreatedEvent);
//        reviewRequestMessagePublisher.publish(reviewAPpMessage);

        // Response return
        // Mapping : ReviewCreatedEvent -> CreateReviewResponse
//        return reviewDataMapper.reviewToCreateReviewResponse(reviewCreatedEvent.getReview(), "Review created successfully.");
        return null;
    }

}
