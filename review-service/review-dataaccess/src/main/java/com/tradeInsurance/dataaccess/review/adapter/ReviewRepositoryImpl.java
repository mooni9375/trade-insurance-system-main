package com.tradeInsurance.dataaccess.review.adapter;

import com.tradeInsurance.dataaccess.review.mapper.ReviewDataAccessMapper;
import com.tradeInsurance.dataaccess.review.repository.ReviewJpaRepository;
import com.tradeInsurance.domain.entity.Review;
import com.tradeInsurance.domain.ports.output.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewRepositoryImpl implements ReviewRepository {

    private final ReviewJpaRepository reviewJpaRepository;
    private final ReviewDataAccessMapper reviewDataAccessMapper;

    @Autowired
    public ReviewRepositoryImpl(ReviewDataAccessMapper reviewDataAccessMapper,
                                ReviewJpaRepository reviewJpaRepository) {
        this.reviewDataAccessMapper = reviewDataAccessMapper;
        this.reviewJpaRepository = reviewJpaRepository;
    }

    @Override
    public Review save(Review review) {
        return null;
    }
}
