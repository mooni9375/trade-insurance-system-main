package com.tradeInsurance.applicationservice.ports.output.repository;

import com.tradeInsurance.domaincore.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> findCustomerById(UUID customerId);
}
