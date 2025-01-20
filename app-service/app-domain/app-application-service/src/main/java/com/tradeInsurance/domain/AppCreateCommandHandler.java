package com.tradeInsurance.domain;

import com.tradeInsurance.domain.dto.create.CreateAppCommand;
import com.tradeInsurance.domain.dto.create.CreateAppResponse;
import com.tradeInsurance.domain.dto.message.AppReviewMessage;
import com.tradeInsurance.domain.mapper.AppDataMapper;
import com.tradeInsurance.domain.event.AppCreatedEvent;
import com.tradeInsurance.domain.ports.output.message.publisher.review.ReviewRequestMessagePublisher;
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
                                   ReviewRequestMessagePublisher reviewRequestMessagePublisher
    ) {
        this.appCreateHelper = appCreateHelper;
        this.appDataMapper = appDataMapper;
        this.reviewRequestMessagePublisher = reviewRequestMessagePublisher;
    }

    /**
     * Separate Transaction processing and Event publishing.
     *  Transaction processing : Processed by Helper
     *  Event publishing : Published by Handler
     */
    /**
     * 트랜잭션 처리에 대한 고민
     *  현재 문제 : 단일 서비스 내 트랜잭션에만 의존 -> 데이터 일관성과 원자성 보장 필요.
     *
     *  청약 트랜잭션 처리 후 단계인 이벤트 발행, 메시지 송수신, 심사 서비스 단계에서 오류 발생시, 청약과 심사의 데이터 일관성이 보장되지 않음.
     *  따라서 추후 Outbox Pattern 혹은 SAGA Pattern 도입함으로써 연관된 두 마이크로 서비스가 같은 트랜잭션 컨텍스트 내에서 처리되도록 조치 필요.
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
