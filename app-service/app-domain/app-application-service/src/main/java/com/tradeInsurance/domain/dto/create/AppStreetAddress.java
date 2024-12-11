package com.tradeInsurance.domain.dto.create;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tradeInsurance.commonlibrary.domain.valueobject.CountryCode;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
//@AllArgsConstructor
public class AppStreetAddress {

    @JsonCreator
    public AppStreetAddress(
            @JsonProperty("street") String street,
            @JsonProperty("city") String city,
            @JsonProperty("postalCode") String postalCode
    ) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    @NotNull
    @Max(value = 50)
    private String street;

    @NotNull
    @Max(value = 50)
    private String city;

    @NotNull
    @Max(value = 50)
    private String postalCode;
}
