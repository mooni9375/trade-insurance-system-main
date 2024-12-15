package com.tradeInsurance.domain;

import com.tradeInsurance.domain.dto.CreateReviewCommand;
import com.tradeInsurance.domain.dto.CreateReviewResponse;
import com.tradeInsurance.domain.ports.input.service.ReviewApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class ReviewApplicationServiceImpl implements ReviewApplicationService {

    private final ReviewCreateCommandHandler reviewCreateCommandHandler;

    @Autowired
    public ReviewApplicationServiceImpl(ReviewCreateCommandHandler reviewCreateCommandHandler) {
        this.reviewCreateCommandHandler = reviewCreateCommandHandler;
    }

    @Override
    public CreateReviewResponse createReview(CreateReviewCommand createReviewCommand) {
        return reviewCreateCommandHandler.createReview(createReviewCommand);
    }
}
