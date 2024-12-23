package com.tradeInsurance.dataaccess.app.repository;

import com.tradeInsurance.dataaccess.app.entity.AppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppJpaRepository extends JpaRepository<AppEntity, UUID> {

    Optional<AppEntity> findById(UUID appId);
}
