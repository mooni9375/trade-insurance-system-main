package com.tradeinsurance.appapplicationservice.ports.output.repository;

import com.tradeinsurance.appdomaincore.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> findCustomerById(UUID customerId);
}
