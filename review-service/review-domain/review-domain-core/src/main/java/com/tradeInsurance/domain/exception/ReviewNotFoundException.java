package com.tradeInsurance.domain.exception;

import com.tradeInsurance.commonlibrary.domain.exception.DomainException;

public class ReviewNotFoundException extends DomainException {

    public ReviewNotFoundException(String message) {
        super(message);
    }

    public ReviewNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
