package com.tradeInsurance.applicationservice.mapper;


import com.tradeInsurance.applicationservice.dto.create.AppStreetAddress;
import com.tradeInsurance.applicationservice.dto.create.CreateAppCommand;
import com.tradeInsurance.applicationservice.dto.create.CreateAppResponse;
import com.tradeInsurance.applicationservice.dto.message.AppReviewMessage;
import com.tradeInsurance.domaincore.entity.App;
import com.tradeInsurance.domaincore.event.AppCreatedEvent;
import com.tradeInsurance.domaincore.valueobject.StreetAddress;
import com.tradeInsurance.commonlibrary.domain.valueobject.CustomerId;
import com.tradeInsurance.commonlibrary.domain.valueobject.Money;
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
