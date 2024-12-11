package com.tradeInsurance.dataaccess.app.adapter;

import com.tradeInsurance.dataaccess.app.mapper.AppDataAccessMapper;
import com.tradeInsurance.dataaccess.app.repository.AppJpaRepository;
import com.tradeInsurance.domain.entity.App;
import com.tradeInsurance.domain.ports.output.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppRepositoryImpl implements AppRepository {

    private final AppJpaRepository appJpaRepository;
    private final AppDataAccessMapper appDataAccessMapper;

    @Autowired
    public AppRepositoryImpl(AppJpaRepository appJpaRepository,
                             AppDataAccessMapper appDataAccessMapper) {
        this.appJpaRepository = appJpaRepository;
        this.appDataAccessMapper = appDataAccessMapper;
    }

    @Override
    public App save(App app) {
        return appDataAccessMapper.appEntityToApp(appJpaRepository
                .save(appDataAccessMapper.appToAppEntity(app)));
    }
}
