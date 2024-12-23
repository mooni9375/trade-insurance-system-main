package com.tradeInsurance.domain;

import com.tradeInsurance.domain.dto.create.CreateAppCommand;
import com.tradeInsurance.domain.dto.create.CreateAppResponse;
import com.tradeInsurance.domain.ports.input.service.AppApplicationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class AppApplicationServiceImpl implements AppApplicationService {

    private final AppCreateCommandHandler appCreateCommandHandler;

    @Autowired
    public AppApplicationServiceImpl(AppCreateCommandHandler appCreateCommandHandler) {
        this.appCreateCommandHandler = appCreateCommandHandler;
    }

    @Override
    public CreateAppResponse createApp(@Valid CreateAppCommand createAppCommand) {
        return appCreateCommandHandler.createApp(createAppCommand);
    }
}
