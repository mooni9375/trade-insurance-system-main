package com.tradeInsurance.domaincore;

import com.tradeInsurance.domaincore.entity.App;
import com.tradeInsurance.domaincore.event.AppCreatedEvent;

public interface AppDomainService {

    AppCreatedEvent validateAndInitiateApp(App app);
}
