package com.tradeInsurance.domain.ports.output.repository;

import com.tradeInsurance.domain.entity.App;

public interface AppRepository {

    App save(App app);

//    Optional<App> findById(AppId appId);

}
