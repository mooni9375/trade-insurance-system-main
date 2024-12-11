package com.tradeInsurance.container;

import com.tradeInsurance.domain.AppDomainService;
import com.tradeInsurance.domain.AppDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public AppDomainService appDomainService() {
        return new AppDomainServiceImpl();
    }

}
