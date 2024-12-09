package com.tradeinsurance.appapplicationservice.dto.create;

import com.tradeinsurance.commonlibrary.domain.valueobject.CountryCode;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateAppCommand {

    @NotNull
    private final UUID customerId;

    @NotNull
    private final String exporterName;

    @NotNull
    private final AppStreetAddress exporterAddress;

    @NotNull
    private final CountryCode importerCountryCode;

    @NotNull
    private final String importerName;

    @NotNull
    private final AppStreetAddress importerAddress;

    @NotNull
    private final String exportProduct;

    @NotNull
    private final BigDecimal appAmount;

}
