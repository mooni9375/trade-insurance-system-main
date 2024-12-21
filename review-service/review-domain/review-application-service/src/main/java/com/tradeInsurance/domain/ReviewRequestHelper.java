package com.tradeInsurance.domain;

import com.tradeInsurance.domain.dto.create.CreateReviewCommand;
import com.tradeInsurance.domain.dto.message.ReviewRequest;
import com.tradeInsurance.domain.entity.Review;
import com.tradeInsurance.domain.event.ReviewCreatedEvent;
import com.tradeInsurance.domain.exception.ReviewDomainException;
import com.tradeInsurance.domain.mapper.ReviewDataMapper;
import com.tradeInsurance.domain.ports.output.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ReviewRequestHelper {

    private ReviewDomainService reviewDomainService;
    private ReviewRepository reviewRepository;
    private ReviewDataMapper reviewDataMapper;

    public ReviewRequestHelper(ReviewDataMapper reviewDataMapper,
                               ReviewDomainService reviewDomainService,
                               ReviewRepository reviewRepository) {
        this.reviewDataMapper = reviewDataMapper;
        this.reviewDomainService = reviewDomainService;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public ReviewCreatedEvent persistReview(ReviewRequest reviewRequest) {

        // Mapping.
        Review review = reviewDataMapper.reviewRequestToReview(reviewRequest);

        // Validate and Initiate Review and get the Event.
        ReviewCreatedEvent reviewCreatedEvent = reviewDomainService.validateAndInitiateReview(review);

        // Save Review
        saveReview(review);
        log.info("Review is created with id: {}", review.getId());

        // Return the Event for Publishing it.
        return reviewCreatedEvent;
    }

    private Review saveReview(Review review) {
        Review reviewResult = reviewRepository.save(review);

        if (reviewResult == null) {
            log.error("Could not save review!");
            throw new ReviewDomainException("Could not save review!");
        }

        log.info("Review is saved with id: {}", review.getId());

        return reviewResult;
    }
}
