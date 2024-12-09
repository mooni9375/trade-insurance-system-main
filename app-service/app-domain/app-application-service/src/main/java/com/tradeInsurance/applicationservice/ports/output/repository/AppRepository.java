package com.tradeinsurance.appapplicationservice.ports.output.repository;

import com.tradeinsurance.appdomaincore.entity.App;
import com.tradeinsurance.commonlibrary.domain.valueobject.AppId;

import java.util.Optional;

public interface AppRepository {

    App save(App app);

//    Optional<App> findById(AppId appId);

}
