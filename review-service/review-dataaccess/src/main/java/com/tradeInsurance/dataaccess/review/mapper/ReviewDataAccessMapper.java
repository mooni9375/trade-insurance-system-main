package com.tradeInsurance.dataaccess.review.mapper;

import com.tradeInsurance.dataaccess.review.entity.ReviewEntity;
import com.tradeInsurance.domain.entity.Review;
import org.springframework.stereotype.Component;

import static com.tradeInsurance.domain.entity.Review.FAILURE_MESSAGE_DELIMITER;

@Component
public class ReviewDataAccessMapper {

    public Review reviewEntityToReview(ReviewEntity reviewEntity) {
        return Review.builder().build();
    }

    public ReviewEntity rviewToReviewEntity(Review review) {
        return ReviewEntity.builder()
                .id(review.getId().getValue())
                .appId(review.getAppId().getValue())
                .appAmount(review.getAppAmount().getAmount())
                .importerCountryCode(review.getImporterCountryCode())
                .discountRate(review.getDiscountRate())
                .surchargeRate(review.getSurchargeRate())
                .premiumRate(review.getPremiumRate())
                .insuranceCoverageRate(review.getInsuranceCoverageRate())
                .insuranceAmount(review.getInsuranceAmount().getAmount())
                .reviewOpinion(review.getReviewOpinion())
                .reviewStatus(review.getReviewStatus())
                .failureMessages(review.getFailureMessages() != null ?
                        String.join(FAILURE_MESSAGE_DELIMITER, review.getFailureMessages()) : "")
                .build();
    }

}
