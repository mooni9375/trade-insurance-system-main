package com.tradeinsurance.appapplicationservice.ports.input.service;

import com.tradeinsurance.appapplicationservice.dto.create.CreateAppCommand;
import com.tradeinsurance.appapplicationservice.dto.create.CreateAppResponse;
import jakarta.validation.Valid;

public interface AppApplicationService {

    CreateAppResponse createApp(@Valid CreateAppCommand createAppCommand);

}
