package com.tradeInsurance.domain;

import com.tradeInsurance.domain.dto.CreateReviewCommand;
import com.tradeInsurance.domain.dto.CreateReviewResponse;
import com.tradeInsurance.domain.event.ReviewCreatedEvent;
import com.tradeInsurance.domain.mapper.ReviewDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReviewCreateCommandHandler {

    private final ReviewCreateHelper reviewCreateHelper;
    private final ReviewDataMapper reviewDataMapper;

    @Autowired
    public ReviewCreateCommandHandler(ReviewCreateHelper reviewCreateHelper, ReviewDataMapper reviewDataMapper) {
        this.reviewCreateHelper = reviewCreateHelper;
        this.reviewDataMapper = reviewDataMapper;
    }

    /**
     * Separate Transaction processing and Event publishing.
     *  Transaction processing : Processed by Helper
     *  Event publishing : Published by Handler
     */
    public CreateReviewResponse createReview(CreateReviewCommand createReviewCommand) {

        // Transaction processing
        ReviewCreatedEvent reviewCreatedEvent = reviewCreateHelper.persistReview(createReviewCommand);
        log.info("Review is created with id: {}", reviewCreatedEvent.getReview().getId().getValue());

        // Event publishing
        // Mapping : AppCreatedEvent -> AppReviewMessage
//        AppReviewMessage appReviewMessage = reviewDataMapper.appCreatedEventToAppReviewMessage(appCreatedEvent);
//        reviewRequestMessagePublisher.publish(appReviewMessage);

        // Response return
        // Mapping : ReviewCreatedEvent -> CreateReviewResponse
        return reviewDataMapper.reviewToCrateReviewResponse(reviewCreatedEvent.getReview(), "Review created successfully.");
    }

}
