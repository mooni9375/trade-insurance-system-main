package com.tradeInsurance.applicationservice.ports.input.service;

import com.tradeInsurance.applicationservice.dto.create.CreateAppCommand;
import com.tradeInsurance.applicationservice.dto.create.CreateAppResponse;
import jakarta.validation.Valid;

public interface AppApplicationService {

    CreateAppResponse createApp(@Valid CreateAppCommand createAppCommand);

}
