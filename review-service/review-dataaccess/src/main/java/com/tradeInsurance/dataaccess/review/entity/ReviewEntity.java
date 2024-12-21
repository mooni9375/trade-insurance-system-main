package com.tradeInsurance.dataaccess.review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "review")
@Entity
public class ReviewEntity {

    @Id
    private UUID id;

    public ReviewEntity() {
    }
}
