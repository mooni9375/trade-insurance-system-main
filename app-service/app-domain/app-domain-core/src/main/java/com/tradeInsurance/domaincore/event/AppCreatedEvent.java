package com.tradeInsurance.domaincore.event;

import com.tradeInsurance.domaincore.entity.App;

import java.time.ZonedDateTime;

public class AppCreatedEvent extends AppEvent {

    public AppCreatedEvent(App app, ZonedDateTime createdAt) {
        super(app, createdAt);
    }
}
