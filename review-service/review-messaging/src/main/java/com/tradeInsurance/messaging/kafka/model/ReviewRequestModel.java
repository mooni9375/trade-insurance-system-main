package com.tradeInsurance.messaging.kafka.model;

import lombok.Builder;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Builder
@ToString
public class ReviewRequestModel {

    private String id;
    private String appId;
    private BigDecimal appAmount;
    private String importerCountryCode;
    private ZonedDateTime createdAt;

    public ReviewRequestModel() {
    }

    public ReviewRequestModel(String id,
                              String appId,
                              BigDecimal appAmount,
                              String importerCountryCode,
                              ZonedDateTime createdAt) {
        this.id = id;
        this.appId = appId;
        this.appAmount = appAmount;
        this.importerCountryCode = importerCountryCode;
        this.createdAt = createdAt;
    }

    public BigDecimal getAppAmount() {
        return appAmount;
    }

    public String getAppId() {
        return appId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
    }

    public String getImporterCountryCode() {
        return importerCountryCode;
    }
}
