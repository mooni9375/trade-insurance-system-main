package com.tradeInsurance.domain.entity;

import com.tradeInsurance.commonlibrary.domain.entity.AggregateRoot;
import com.tradeInsurance.commonlibrary.domain.valueobject.*;
import com.tradeInsurance.domain.exception.ReviewDomainException;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class Review extends AggregateRoot<ReviewId> {

    private final AppId appId;
    private final Money appAmount;
    private final CountryCode importerCountryCode;

    // 할인율
    private Integer discountRate;

    // 할증률
    private Integer surchargeRate;

    // 보험요율
    private BigDecimal premiumRate;

    // 부보율
    private Integer insuranceCoverageRate;

    // 책정금액
    private Money insuranceAmount;

    // 심사의견
    private StringBuilder reviewOpinion = new StringBuilder();

    private ReviewStatus reviewStatus;

    private List<String> failureMessages;
    public static final String FAILURE_MESSAGE_DELIMITER = ",";
    private ZonedDateTime createdAt;


    // 심사 생성
    public void initializeReview() {
        setId(new ReviewId(UUID.randomUUID()));
        createdAt = ZonedDateTime.now(ZoneId.of("UTC"));
        reviewStatus = ReviewStatus.REVIEWING;

        // 고위험국인 경우 부보율과 할증률 각각 50%, 30%로 생성.
        if (validateHighRiskCountry()) {
            changeInsuranceCoverageRate(50);
            changeSurchargeRate(30);
        }
    }

    // ***** Review Status 변경 영역 START *****
    private void approve() {
        if (reviewStatus != ReviewStatus.REVIEWING) {
            throw new ReviewDomainException("Review is not in correct state for approve operation.");
        }
        reviewStatus = ReviewStatus.APPROVED;
    }

    private void reject() {
        if (reviewStatus != ReviewStatus.REVIEWING) {
            throw new ReviewDomainException("Review is not in correct state for reject operation.");
        }
        reviewStatus = ReviewStatus.REJECTED;
    }

    // ***** Review Status 변경 영역 END *****


    // ***** 심사 검증 영역 START *****
    public void validateReview() {
        validateInitialReview();
        validateAppAmount();
        validateMinimumInsuranceAmount();
        validateInsuranceAmountUnit();
    }

    private void validateInitialReview() {
        if (reviewStatus != null || getId() != null) {
            throw new ReviewDomainException("Review is not in correct state for initialization.");
        }
    }
    private void validateAppAmount() {
        if (appAmount == null || !appAmount.isGraterThanZero()) {
            throw new ReviewDomainException("App amount must be greater than zero.");
        }
    }

    public void validateMinimumInsuranceAmount() {
        if (insuranceAmount != null && insuranceAmount.getAmount().compareTo(new BigDecimal(5000)) < 0) {
            throw new ReviewDomainException("Insurance amount is Greater than $ 5,000.");
        }
    }

    public void validateInsuranceAmountUnit() {
        BigDecimal remainder = insuranceAmount.getAmount().remainder(BigDecimal.valueOf(1000));
        if (remainder.compareTo(BigDecimal.ZERO) != 0) {
            throw new ReviewDomainException("Insurance amount is not multiple of $ 1,000.");
        }
    }

    private void validateInsuranceCoverageRate(Integer newValue) {
        if (newValue < 50 || newValue > 100) {
            throw new ReviewDomainException("Insurance Coverage Rate must be between 50 and 100.");
        }
    }

    private boolean validateHighRiskCountry() {
        return importerCountryCode.isHighRiskCountry();
    }
    // ***** validation 영역 END *****


    // ***** calculate 영역 START *****
    private BigDecimal calculatePremiumRate(Integer discountRate, Integer surchargeRate) {
        if (discountRate == null || surchargeRate == null) {
            throw new ReviewDomainException("There is no discount rate or surcharge rate value.");
        }

        BigDecimal result = BigDecimal.valueOf((100 - discountRate) * (100 + surchargeRate) * 0.01);

        String message = String.format("Discount Rate : %s / Surcharge Rate : %d -> Final Premium Rate : %f",
                discountRate.toString(), surchargeRate.toString(), result.toString());
        reviewOpinion.append(message);

        return result;
    }
    // ***** calculate 영역 END *****



    // ***** change & modify 영역 START *****
    // 할인율 변경
    private void changeDiscountRate(Integer newDiscountRate) {
        if (newDiscountRate == null) throw new ReviewDomainException("There is no discount rate value.");
        this.discountRate = newDiscountRate;
    }

    // 할증률 변경
    private void changeSurchargeRate(Integer newSurchargeRate) {
        if (newSurchargeRate == null) throw new ReviewDomainException("There is no surcharge rate value.");
        if (validateHighRiskCountry()) throw new ReviewDomainException("High risk country cannot change surcharge rate.");
        this.discountRate = newSurchargeRate;
    }

    // 보험요율 변경
    private void changePremiumRate(BigDecimal newPremiumRate) {
        if (newPremiumRate == null) throw new ReviewDomainException("There is no premium rate value.");
        this.premiumRate = newPremiumRate;
    }

    // 부보율 변경
    private void changeInsuranceCoverageRate(Integer newInsuranceCoverageRate) {
        if (newInsuranceCoverageRate == null) throw new ReviewDomainException("There is no insurance coverage rate value.");
        if (validateHighRiskCountry()) throw new ReviewDomainException("High risk country cannot change insurance coverage rate.");
        validateInsuranceCoverageRate(newInsuranceCoverageRate);
        this.insuranceCoverageRate = newInsuranceCoverageRate;
    }

    // 책정금액 변경
    private void changeInsuranceAmount(Money newInsuranceAmount) {
        if (newInsuranceAmount == null) throw new ReviewDomainException("There is no insurance amount value.");
        validateMinimumInsuranceAmount();
        validateInsuranceAmountUnit();
        this.insuranceAmount = newInsuranceAmount;
    }

    // 심사의견 수정
    private void modifyReviewOpinion(String newReviewOpinion) {
        if (newReviewOpinion == null) throw new ReviewDomainException("There is no review opinion content.");
        this.reviewOpinion = new StringBuilder(newReviewOpinion);
    }
    // ***** change & modify 영역 END *****


    // ***** getter 영역 START *****
    public AppId getAppId() {
        return appId;
    }

    public Money getAppAmount() {
        return appAmount;
    }

    public CountryCode getImporterCountryCode() {
        return importerCountryCode;
    }

    public Integer getDiscountRate() {
        return discountRate;
    }

    public Integer getSurchargeRate() {
        return surchargeRate;
    }

    public BigDecimal getPremiumRate() {
        return premiumRate;
    }

    public Integer getInsuranceCoverageRate() {
        return insuranceCoverageRate;
    }

    public Money getInsuranceAmount() {
        return insuranceAmount;
    }

    public StringBuilder getReviewOpinion() {
        return reviewOpinion;
    }

    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    // ***** getter 영역 END *****

    // ***** builder 영역 START *****
    private Review(Builder builder) {
        super.setId(builder.reviewId);
        appId = builder.appId;
        appAmount = builder.appAmount;
        importerCountryCode = builder.importerCountryCode;
        discountRate = builder.discountRate;
        surchargeRate = builder.surchargeRate;
        premiumRate = builder.premiumRate;
        insuranceCoverageRate = builder.insuranceCoverageRate;
        insuranceAmount = builder.insuranceAmount;
        reviewOpinion = builder.reviewOpinion;
        reviewStatus = builder.reviewStatus;
        failureMessages = builder.failureMessages;
        createdAt = builder.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ReviewId reviewId;
        private AppId appId;
        private Money appAmount;
        private CountryCode importerCountryCode;
        private Integer discountRate;
        private Integer surchargeRate;
        private BigDecimal premiumRate;
        private Integer insuranceCoverageRate;
        private Money insuranceAmount;
        private StringBuilder reviewOpinion;
        private ReviewStatus reviewStatus;
        private List<String> failureMessages;
        private ZonedDateTime createdAt;

        private Builder() {
        }

        public Builder reviewId(ReviewId val) {
            reviewId = val;
            return this;
        }

        public Builder appId(AppId val) {
            appId = val;
            return this;
        }

        public Builder appAmount(Money val) {
            appAmount = val;
            return this;
        }

        public Builder importerCountryCode(CountryCode val) {
            importerCountryCode = val;
            return this;
        }

        public Builder discountRate(Integer val) {
            discountRate = val;
            return this;
        }

        public Builder surchargeRate(Integer val) {
            surchargeRate = val;
            return this;
        }

        public Builder premiumRate(BigDecimal val) {
            premiumRate = val;
            return this;
        }

        public Builder insuranceCoverageRate(Integer val) {
            insuranceCoverageRate = val;
            return this;
        }

        public Builder insuranceAmount(Money val) {
            insuranceAmount = val;
            return this;
        }

        public Builder reviewOpinion(StringBuilder val) {
            reviewOpinion = val;
            return this;
        }

        public Builder reviewStatus(ReviewStatus val) {
            reviewStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
    // ***** builder 영역 END *****
}
