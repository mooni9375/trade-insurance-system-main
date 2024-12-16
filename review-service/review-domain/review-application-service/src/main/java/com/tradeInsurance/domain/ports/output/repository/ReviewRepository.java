package com.tradeInsurance.domain.ports.output.repository;

import com.tradeInsurance.domain.entity.Review;

public interface ReviewRepository {

    Review save(Review review);
}
