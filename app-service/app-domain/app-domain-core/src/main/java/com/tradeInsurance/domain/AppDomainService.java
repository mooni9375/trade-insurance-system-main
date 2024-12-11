package com.tradeInsurance.domain;

import com.tradeInsurance.domain.entity.App;
import com.tradeInsurance.domain.event.AppCreatedEvent;

public interface AppDomainService {

    AppCreatedEvent validateAndInitiateApp(App app);
}
