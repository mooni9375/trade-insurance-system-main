package com.tradeInsurance.applicationservice.dto.create;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
//@AllArgsConstructor
public class CreateAppCommand {

    @JsonCreator
    public CreateAppCommand(
            @JsonProperty("customerId") UUID customerId,
            @JsonProperty("exporterName") String exporterName,
            @JsonProperty("exporterAddress") AppStreetAddress exporterAddress,
            @JsonProperty("importerCountryCode") CountryCode importerCountryCode,
            @JsonProperty("importerName") String importerName,
            @JsonProperty("importerAddress") AppStreetAddress importerAddress,
            @JsonProperty("exportProduct") String exportProduct,
            @JsonProperty("appAmount") BigDecimal appAmount
            ) {
        this.customerId = customerId;
        this.exporterName = exporterName;
        this.exporterAddress = exporterAddress;
        this.importerCountryCode = importerCountryCode;
        this.importerName = importerName;
        this.importerAddress = importerAddress;
        this.exportProduct = exportProduct;
        this.appAmount = appAmount;
    }

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
