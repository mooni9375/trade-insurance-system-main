package com.tradeInsurance.domain.ports.input.service;

import com.tradeInsurance.domain.dto.create.CreateAppCommand;
import com.tradeInsurance.domain.dto.create.CreateAppResponse;
import jakarta.validation.Valid;

public interface AppApplicationService {

    CreateAppResponse createApp(@Valid CreateAppCommand createAppCommand);

}
