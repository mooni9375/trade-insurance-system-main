package com.tradeInsurance.domain.mapper;

import com.tradeInsurance.commonlibrary.domain.valueobject.AppId;
import com.tradeInsurance.commonlibrary.domain.valueobject.CountryCode;
import com.tradeInsurance.commonlibrary.domain.valueobject.Money;
import com.tradeInsurance.commonlibrary.domain.valueobject.ReviewStatus;
import com.tradeInsurance.domain.dto.create.CreateReviewCommand;
import com.tradeInsurance.domain.dto.create.CreateReviewResponse;
import com.tradeInsurance.domain.dto.message.ReviewRequest;
import com.tradeInsurance.domain.entity.Review;
import com.tradeInsurance.domain.event.ReviewCreatedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Component
public class ReviewDataMapper {

    public Review reviewRequestToReview(ReviewRequest reviewRequest) {

        return Review.builder()
                .appId(new AppId(UUID.fromString(reviewRequest.getAppId())))
                .appAmount(new Money(reviewRequest.getAppAmount()))
                .importerCountryCode(CountryCode.fromCode(reviewRequest.getImporterCountryCode()))
//                .importerCountryCode(CountryCode.fromCode(reviewRequest.getImporterCountryCode()))
//                .discountRate(0)
//                .surchargeRate(0)
//                .premiumRate(new BigDecimal(BigInteger.ZERO))
//                .insuranceCoverageRate(0)
//                .insuranceAmount(new Money(BigDecimal.ZERO))
//                .reviewOpinion(new StringBuilder())
//                .reviewStatus(ReviewStatus.REVIEWING)
                .build();
    }

    public CreateReviewResponse reviewToCreateReviewResponse(Review review, String message) {

        return null;
    }

}
