package com.tradeInsurance.domain.ports.output.repository;

import com.tradeInsurance.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> findCustomerById(UUID customerId);
}
