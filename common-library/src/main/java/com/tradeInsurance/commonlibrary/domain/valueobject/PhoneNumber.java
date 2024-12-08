package com.tradeInsurance.commonlibrary.domain.valueobject;

import java.util.Objects;

public class PhoneNumber {

    private final String phoneNumberFirstPart;
    private final String phoneNumberSecondPart;
    private final String phoneNumberThirdPart;

    public PhoneNumber(String phoneNumberFirstPart,
                       String phoneNumberSecondPart,
                       String phoneNumberThirdPart) {
        this.phoneNumberFirstPart = phoneNumberFirstPart;
        this.phoneNumberSecondPart = phoneNumberSecondPart;
        this.phoneNumberThirdPart = phoneNumberThirdPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(phoneNumberFirstPart, that.phoneNumberFirstPart) && Objects.equals(phoneNumberSecondPart, that.phoneNumberSecondPart) && Objects.equals(phoneNumberThirdPart, that.phoneNumberThirdPart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumberFirstPart, phoneNumberSecondPart, phoneNumberThirdPart);
    }
}
