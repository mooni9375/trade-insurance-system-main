package com.tradeInsurance.commonlibrary.domain.event.publisher;

import com.tradeInsurance.commonlibrary.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent>{

    void publish(T domainEvent);

}

