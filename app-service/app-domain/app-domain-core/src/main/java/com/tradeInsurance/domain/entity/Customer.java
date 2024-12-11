package com.tradeInsurance.domain.entity;

import com.tradeInsurance.commonlibrary.domain.entity.AggregateRoot;
import com.tradeInsurance.commonlibrary.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

    public Customer() {
    }

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }
}
