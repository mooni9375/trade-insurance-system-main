package com.tradeInsurance.domain.dto.create;

import com.tradeInsurance.commonlibrary.domain.valueobject.CountryCode;
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
public class CreateAppCommand {

    @NotNull
    private UUID customerId;

    @NotNull
    private String exporterName;

    @NotNull
    private AppStreetAddress exporterAddress;

    @NotNull
    private CountryCode importerCountryCode;

    @NotNull
    private String importerName;

    @NotNull
    private AppStreetAddress importerAddress;

    @NotNull
    private String exportProduct;

    @NotNull
    private BigDecimal appAmount;

}
