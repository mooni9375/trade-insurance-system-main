package com.tradeInsurance.domain;

import com.tradeInsurance.domain.dto.create.CreateAppCommand;
import com.tradeInsurance.domain.mapper.AppDataMapper;
import com.tradeInsurance.domain.ports.output.repository.AppRepository;
import com.tradeInsurance.domain.entity.App;
import com.tradeInsurance.domain.event.AppCreatedEvent;
import com.tradeInsurance.domain.exception.AppDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class AppCreateHelper {

    private final AppDomainService appDomainService;
    private final AppRepository appRepository;
//    private final CustomerRepository customerRepository;
    private final AppDataMapper appDataMapper;

    @Autowired
    public AppCreateHelper(AppDataMapper appDataMapper,
                           AppDomainService appDomainService,
//                           CustomerRepository customerRepository,
                           AppRepository appRepository) {
        this.appDataMapper = appDataMapper;
        this.appDomainService = appDomainService;
//        this.customerRepository = customerRepository;
        this.appRepository = appRepository;
    }

    @Transactional
    public AppCreatedEvent persistApp(CreateAppCommand createAppCommand) {

        // Check if the customer exists.
//        checkCustomerExists(createAppCommand.getCustomerId());

        // Mapping.
        App app = appDataMapper.createAppCommandToApp(createAppCommand);

        // Validate and Initiate App and get the Event.
        AppCreatedEvent appCreatedEvent = appDomainService.validateAndInitiateApp(app);

        // Save App
        saveApp(app);
        log.info("App is created with id: {}", app.getId());

        // Return the Event for Publishing it.
        return appCreatedEvent;
    }

//    private void checkCustomerExists(UUID customerId) {
//        Optional<Customer> customer = customerRepository.findCustomerById(customerId);
//
//        if (customer.isEmpty()) {
//            log.warn("Could not find customer with id: {}", customerId);
//            throw new AppDomainException("Could not find customer with id: " + customerId);
//        }
//    }

    private App saveApp(App app) {
        App appResult = appRepository.save(app);

        if (appResult == null) {
            log.error("Could not save app!");
            throw new AppDomainException("Could not save app!");
        }

        log.info("App is saved with id: {}", app.getId());

        return appResult;
    }
}
