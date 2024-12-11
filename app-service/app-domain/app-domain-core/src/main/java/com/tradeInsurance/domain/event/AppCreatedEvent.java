package com.tradeInsurance.domain.event;

import com.tradeInsurance.domain.entity.App;

import java.time.ZonedDateTime;

public class AppCreatedEvent extends AppEvent {

    public AppCreatedEvent(App app, ZonedDateTime createdAt) {
        super(app, createdAt);
    }
}
