package com.tradeinsurance.appapplicationservice.mapper;


import com.tradeinsurance.appapplicationservice.dto.create.AppStreetAddress;
import com.tradeinsurance.appapplicationservice.dto.create.CreateAppCommand;
import com.tradeinsurance.appapplicationservice.dto.create.CreateAppResponse;
import com.tradeinsurance.appapplicationservice.dto.message.AppReviewMessage;
import com.tradeinsurance.appdomaincore.entity.App;
import com.tradeinsurance.appdomaincore.event.AppCreatedEvent;
import com.tradeinsurance.appdomaincore.valueobject.StreetAddress;
import com.tradeinsurance.commonlibrary.domain.valueobject.CustomerId;
import com.tradeinsurance.commonlibrary.domain.valueobject.Money;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppDataMapper {

    public App createAppCommandToApp(CreateAppCommand createAppCommand) {
        return App.builder()
                .customerId(new CustomerId(createAppCommand.getCustomerId()))
                .exporterName(createAppCommand.getExporterName())
                .exporterAddress(appStreetAdderToStreetAddress(createAppCommand.getExporterAddress()))
                .importerCountryCode(createAppCommand.getImporterCountryCode())
                .importerName(createAppCommand.getImporterName())
                .importerAddress(appStreetAdderToStreetAddress(createAppCommand.getImporterAddress()))
                .appAmount(new Money(createAppCommand.getAppAmount()))
                .exportProduct(createAppCommand.getExportProduct())
                .build();
    }

    public CreateAppResponse appToCreateAppResponse(App app, String message) {
        return CreateAppResponse.builder()
                .appStatus(app.getAppStatus())
                .message(message)
                .build();
    }

    public AppReviewMessage appCreatedEventToAppReviewMessage(AppCreatedEvent appCreatedEvent) {
        return AppReviewMessage.builder()
                .appId(appCreatedEvent.getApp().getId().getValue().toString())
                .appAmount(appCreatedEvent.getApp().getAppAmount().getAmount())
                .importerCountryCode(appCreatedEvent.getApp().getImporterCountryCode().getCode())
                .createdAt(appCreatedEvent.getCreatedAt())
                .build();
    }

//    public TrackAppResponse appToTrackAppResponse(App app) {
//        return TrackAppResponse.builder()
//                .appTrackingId(app.getTrackingId().getValue())
//                .appStatus(app.getAppStatus())
//                .failureMessages(app.getFailureMessages())
//                .build();
//    }

    private StreetAddress appStreetAdderToStreetAddress(AppStreetAddress appStreetAddress) {
        return new StreetAddress(UUID.randomUUID(),
                appStreetAddress.getStreet(),
                appStreetAddress.getCity(),
                appStreetAddress.getPostalCode());
    }
}
