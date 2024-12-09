package com.tradeinsurance.appdomaincore;

import com.tradeinsurance.appdomaincore.entity.App;
import com.tradeinsurance.appdomaincore.event.AppCreatedEvent;

public interface AppDomainService {

    AppCreatedEvent validateAndInitiateApp(App app);
}
