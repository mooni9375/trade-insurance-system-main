package com.tradeInsurance.domain.dto.create;

import com.tradeInsurance.commonlibrary.domain.valueobject.CountryCode;
import com.tradeInsurance.commonlibrary.domain.valueobject.ReviewStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewCommand {

    @NotNull
    private UUID appId;

    @NotNull
    private BigDecimal appAmount;

    @NotNull
    private CountryCode importerCountryCode;

    /*
    @NotNull
    private Integer discountRate;

    @NotNull
    private Integer surchargeRate;

    @NotNull
    private BigDecimal premiumRate;

    @NotNull
    private Integer insuranceCoverageRate;

    @NotNull
    private BigDecimal insuranceAmount;

    @NotNull
    private String reviewOpinion;
    */
//    private StringBuilder reviewOpinion;

    @NotNull
    private ReviewStatus reviewStatus;

//    @NotNull
//    private ZonedDateTime createdAt;

}
