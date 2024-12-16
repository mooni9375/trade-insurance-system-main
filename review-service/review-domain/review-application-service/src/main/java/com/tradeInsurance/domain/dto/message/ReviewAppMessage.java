package com.tradeInsurance.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ReviewAppMessage {

    private String appId;
    private String reviewId;
    private ZonedDateTime createdAt;
}
