package com.tradeinsurance.appdomaincore;

import com.tradeinsurance.appdomaincore.entity.App;
import com.tradeinsurance.appdomaincore.event.AppCreatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class AppDomainServiceImpl implements AppDomainService {

    private static final String UTC = "UTC";

    @Override
    public AppCreatedEvent validateAndInitiateApp(App app) {

        app.validateApp();
        app.initializeApp();

        log.info("app with id: {} is initialized", app.getId().getValue());

        return new AppCreatedEvent(app, ZonedDateTime.now(ZoneId.of(UTC)));

    }
}
