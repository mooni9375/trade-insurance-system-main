package com.tradeInsurance.domain.ports.input.service;

import com.tradeInsurance.domain.dto.CreateReviewCommand;
import com.tradeInsurance.domain.dto.CreateReviewResponse;
import jakarta.validation.Valid;

public interface ReviewApplicationService {

    CreateReviewResponse createReview(@Valid CreateReviewCommand createReviewCommand);
}
