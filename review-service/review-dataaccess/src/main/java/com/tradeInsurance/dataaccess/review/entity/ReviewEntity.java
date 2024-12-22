package com.tradeInsurance.dataaccess.review.entity;

import com.tradeInsurance.commonlibrary.domain.valueobject.CountryCode;
import com.tradeInsurance.commonlibrary.domain.valueobject.Money;
import com.tradeInsurance.commonlibrary.domain.valueobject.ReviewStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "review")
@Entity
public class ReviewEntity {

    @Id
    private UUID id;

    private UUID appId;

    private BigDecimal appAmount;

    @Enumerated(EnumType.STRING)
    private CountryCode importerCountryCode;

    private Integer discountRate;

    private Integer surchargeRate;

    private BigDecimal premiumRate;

    private Integer insuranceCoverageRate;

    private BigDecimal insuranceAmount;

    private StringBuilder reviewOpinion;

    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;

    private String failureMessages;


    public ReviewEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReviewEntity that = (ReviewEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
