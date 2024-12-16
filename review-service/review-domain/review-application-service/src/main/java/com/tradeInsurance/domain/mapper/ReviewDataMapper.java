package com.tradeInsurance.domain.mapper;

import com.tradeInsurance.commonlibrary.domain.valueobject.AppId;
import com.tradeInsurance.commonlibrary.domain.valueobject.Money;
import com.tradeInsurance.commonlibrary.domain.valueobject.ReviewStatus;
import com.tradeInsurance.domain.dto.create.CreateReviewCommand;
import com.tradeInsurance.domain.dto.create.CreateReviewResponse;
import com.tradeInsurance.domain.dto.message.ReviewAppMessage;
import com.tradeInsurance.domain.entity.Review;
import com.tradeInsurance.domain.event.ReviewCreatedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ReviewDataMapper {

    public Review createReviewCommandToReview(CreateReviewCommand createReviewCommand) {

        return Review.builder()
                .appId(new AppId(createReviewCommand.getAppId()))
                .appAmount(new Money(createReviewCommand.getAppAmount()))
                .importerCountryCode(createReviewCommand.getImporterCountryCode())
//                .discountRate(createReviewCommand.getDiscountRate())
//                .surchargeRate(createReviewCommand.getSurchargeRate())
//                .premiumRate(createReviewCommand.getPremiumRate())
//                .insuranceCoverageRate(createReviewCommand.getInsuranceCoverageRate())
//                .insuranceAmount(new Money(createReviewCommand.getInsuranceAmount()))
//                .reviewOpinion(new StringBuilder(createReviewCommand.getReviewOpinion()))
                .reviewStatus(ReviewStatus.REVIEWING)
//                .createdAt(ZonedDateTime.now(ZoneId.of("UTC")))
                .build();
    }

    public CreateReviewResponse reviewToCreateReviewResponse(Review review, String message) {

        return null;
    }

    public ReviewAppMessage reviewCreatedEventToReviewAppMessage(ReviewCreatedEvent reviewCreatedEvent) {

        return ReviewAppMessage.builder()
                .appId(reviewCreatedEvent.getReview().getAppId().toString())
                .reviewId(reviewCreatedEvent.getReview().getId().getValue().toString())
                .createdAt(reviewCreatedEvent.getCreatedAt())
                .build();
    }






}
