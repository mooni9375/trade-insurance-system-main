package com.tradeInsurance.dataaccess.review.mapper;

import com.tradeInsurance.dataaccess.review.entity.ReviewEntity;
import com.tradeInsurance.domain.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewDataAccessMapper {

    public Review reviewEntityToReview(ReviewEntity reviewEntity) {
        return Review.builder().build();
    }

    public ReviewEntity rviewToReviewEntity(Review review) {
        return ReviewEntity.builder()
                .id(review.getId().getValue())
                .appId(review.getAppId().getValue())
                .build();
    }
}
