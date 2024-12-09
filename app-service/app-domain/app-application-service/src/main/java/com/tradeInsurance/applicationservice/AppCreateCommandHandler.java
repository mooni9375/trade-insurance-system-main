package com.tradeinsurance.appapplicationservice;

import com.tradeinsurance.appapplicationservice.dto.create.CreateAppCommand;
import com.tradeinsurance.appapplicationservice.dto.create.CreateAppResponse;
import com.tradeinsurance.appapplicationservice.dto.message.AppReviewMessage;
import com.tradeinsurance.appapplicationservice.mapper.AppDataMapper;
import com.tradeinsurance.appapplicationservice.ports.output.message.publisher.review.ReviewRequestMessagePublisher;
import com.tradeinsurance.appdomaincore.event.AppCreatedEvent;
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
        // AppCreatedEvent -> AppReviewMessage : 변환해서 넘겨줘야 함
        AppReviewMessage appReviewMessage = appDataMapper.appCreatedEventToAppReviewMessage(appCreatedEvent);
        reviewRequestMessagePublisher.publish(appReviewMessage);

        return appDataMapper.appToCreateAppResponse(appCreatedEvent.getApp(), "App created successfully.");
    }

}
