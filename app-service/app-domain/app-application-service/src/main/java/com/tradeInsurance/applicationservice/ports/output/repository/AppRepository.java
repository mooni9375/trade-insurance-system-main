package com.tradeInsurance.applicationservice.ports.output.repository;

import com.tradeInsurance.domaincore.entity.App;

public interface AppRepository {

    App save(App app);

//    Optional<App> findById(AppId appId);

}
