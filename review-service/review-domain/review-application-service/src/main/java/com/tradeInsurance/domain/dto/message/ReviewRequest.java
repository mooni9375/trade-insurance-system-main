package com.tradeInsurance.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ReviewRequest {

    private String id;
    private String appId;
    private BigDecimal appAmount;
    private String importerCountryCode;
    private ZonedDateTime createdAt;
}
