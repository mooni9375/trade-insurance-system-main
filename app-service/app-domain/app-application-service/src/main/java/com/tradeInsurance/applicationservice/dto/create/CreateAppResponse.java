package com.tradeinsurance.appapplicationservice.dto.create;

import com.tradeinsurance.commonlibrary.domain.valueobject.AppStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateAppResponse {

    @NotNull
    private final UUID appTrackingId;

    @NotNull
    private final AppStatus appStatus;

    @NotNull
    private final String message;
}
