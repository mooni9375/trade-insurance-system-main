package com.tradeInsurance.domain;

import com.tradeInsurance.domain.dto.CreateReviewCommand;
import com.tradeInsurance.domain.event.ReviewCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReviewCreateHelper {

    public ReviewCreatedEvent persistReview(CreateReviewCommand createReviewCommand) {

        return null;
    }
}
