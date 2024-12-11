package com.tradeInsurance.domain.event;

import com.tradeInsurance.commonlibrary.domain.event.DomainEvent;
import com.tradeInsurance.domain.entity.App;

import java.time.ZonedDateTime;

public class AppEvent implements DomainEvent<App> {

    private final App app;
    private final ZonedDateTime createdAt;

    public AppEvent(App app, ZonedDateTime createdAt) {
        this.app = app;
        this.createdAt = createdAt;
    }

    public App getApp() {
        return app;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
