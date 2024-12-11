package com.tradeInsurance.domain.exception;

import com.tradeInsurance.commonlibrary.domain.exception.DomainException;

public class AppNotFoundException extends DomainException {

    public AppNotFoundException(String message) {
        super(message);
    }

    public AppNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
