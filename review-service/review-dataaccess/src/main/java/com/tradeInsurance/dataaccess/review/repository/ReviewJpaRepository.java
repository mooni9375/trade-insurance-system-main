package com.tradeInsurance.dataaccess.review.repository;

import com.tradeInsurance.dataaccess.review.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewJpaRepository extends JpaRepository<ReviewEntity, UUID> {

    Optional<ReviewEntity> findById(UUID reviewId);
}
