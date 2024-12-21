package com.tradeInsurance.container;

import com.tradeInsurance.domain.ReviewDomainService;
import com.tradeInsurance.domain.ReviewDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ReviewDomainService reviewDomainService() {
        return new ReviewDomainServiceImpl();
    }

}
