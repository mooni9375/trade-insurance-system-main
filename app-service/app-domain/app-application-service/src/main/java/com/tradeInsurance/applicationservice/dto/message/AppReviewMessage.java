package com.tradeinsurance.appapplicationservice.dto.message;

import com.tradeinsurance.commonlibrary.domain.valueobject.AppStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class AppReviewMessage {

    private String appId;
    private BigDecimal appAmount;
    private String importerCountryCode;
    private ZonedDateTime createdAt;
}
