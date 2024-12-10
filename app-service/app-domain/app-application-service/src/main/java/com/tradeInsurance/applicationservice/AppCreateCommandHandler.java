package com.tradeInsurance.applicationservice;

import com.tradeInsurance.applicationservice.dto.create.CreateAppCommand;
import com.tradeInsurance.applicationservice.dto.create.CreateAppResponse;
import com.tradeInsurance.applicationservice.dto.message.AppReviewMessage;
import com.tradeInsurance.applicationservice.mapper.AppDataMapper;
import com.tradeInsurance.applicationservice.ports.output.message.publisher.review.ReviewRequestMessagePublisher;
import com.tradeInsurance.domaincore.event.AppCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppCreateCommandHandler {

    private final AppCreateHelper appCreateHelper;
    private final AppDataMapper appDataMapper;
    private final ReviewRequestMessagePublisher reviewRequestMessagePublisher;

    @Autowired
    public AppCreateCommandHandler(AppCreateHelper appCreateHelper,
                                   AppDataMapper appDataMapper,
                                   ReviewRequestMessagePublisher reviewRequestMessagePublisher) {
        this.appCreateHelper = appCreateHelper;
        this.appDataMapper = appDataMapper;
        this.reviewRequestMessagePublisher = reviewRequestMessagePublisher;
    }

    /**
     * Separate Transaction processing and Event publishing.
     *  Transaction processing : Processed by Helper
     *  Event publishing : Published by Handler
     */
    public CreateAppResponse createApp(CreateAppCommand createAppCommand) {

        // Transaction processing
        AppCreatedEvent appCreatedEvent = appCreateHelper.persistApp(createAppCommand);
        log.info("App is created with id: {}", appCreatedEvent.getApp().getId().getValue());

        // Event publishing
        // Mapping : AppCreatedEvent -> AppReviewMessage
        AppReviewMessage appReviewMessage = appDataMapper.appCreatedEventToAppReviewMessage(appCreatedEvent);
        reviewRequestMessagePublisher.publish(appReviewMessage);

        // Response return
        // Mapping : AppCreatedEvent -> CreateAppResponse
        return appDataMapper.appToCreateAppResponse(appCreatedEvent.getApp(), "App created successfully.");
    }

}
