package com.tradeInsurance.domain.dto.create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppStreetAddress {

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
