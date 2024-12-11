package com.tradeInsurance.domain.entity;

import com.tradeInsurance.commonlibrary.domain.entity.AggregateRoot;
import com.tradeInsurance.commonlibrary.domain.valueobject.*;
import com.tradeInsurance.domain.exception.AppDomainException;
import com.tradeInsurance.domain.valueobject.StreetAddress;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class App extends AggregateRoot<AppId> {

    private final CustomerId customerId;

    private final String exporterName;
    private final StreetAddress exporterAddress;

    private final CountryCode importerCountryCode;
    private final String importerName;
    private final StreetAddress importerAddress;

    private final String exportProduct;
    private final Money appAmount;

    // 생성 후 비즈니스 로직에서 셋팅하기 위해 not final
//    private TrackingId trackingId;
    private AppStatus appStatus;

    private List<String> failureMessages;
    public static final String FAILURE_MESSAGE_DELIMITER = ",";


    // 청약 생성
    public void initializeApp() {
        setId(new AppId(UUID.randomUUID()));
        appStatus = AppStatus.SUBMITTED;
    }

    // ***** App Status 변경 영역 START *****
    private void requestReview() {
        if (appStatus != AppStatus.SUBMITTED) {
            throw new AppDomainException("App is not in correct state for review request.");
        }
        appStatus = AppStatus.UNDER_REVIEW;
        // 이후 Handler 로직에서 AppSubmittedEvent 발행되어야 함
    }

    private void issuancePolicy() {
        if (appStatus != AppStatus.UNDER_REVIEW) {
            throw new AppDomainException("App is not in correct state for policy issuance.");
        }
        appStatus = AppStatus.POLICY_ISSUED;
    }

    private void returnApp(List<String> failureMessages) {
        if (appStatus != AppStatus.UNDER_REVIEW) {
            throw new AppDomainException("App is not in correct state for return.");
        }
        appStatus = AppStatus.RETURNED;
        updateFailureMessages(failureMessages);
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if (this.failureMessages != null && failureMessages !=  null) {
            this.failureMessages.addAll(failureMessages.stream().filter(message -> !message.isEmpty()).toList());
        }
        if (this.failureMessages == null) {
            this.failureMessages = failureMessages;
        }
    }
    // ***** App Status 변경 영역 END *****


    // ***** 청약 검증 영역 START *****
    public void validateApp() {
        validateInitialApp();
        validateAppAmount();
        validateMinimumAppAmount();
        validateAppAmountUnit();
    }

    private void validateInitialApp() {
        if (appStatus != null || getId() != null) {
            throw new AppDomainException("App is not in correct state for initialization.");
        }
    }

    private void validateAppAmount() {
        if (appAmount == null || !appAmount.isGraterThanZero()) {
            throw new AppDomainException("App amount must be greater than zero.");
        }
    }

    public void validateMinimumAppAmount() {
        if (appAmount != null && appAmount.getAmount().compareTo(new BigDecimal(5000)) < 0) {
            throw new AppDomainException("App amount must be Greater than $ 5,000");
        }
    }

    public void validateAppAmountUnit() {
        BigDecimal remainder = appAmount.getAmount().remainder(BigDecimal.valueOf(1000));
        if (remainder.compareTo(BigDecimal.ZERO) != 0) {
            throw new AppDomainException("App amount is not multiple of $ 1,000");
        }
    }
    // ***** 청약 검증 영역 END *****


    // ***** getter 영역 START *****
    public CustomerId getCustomerId() {
        return customerId;
    }

    public String getExporterName() {
        return exporterName;
    }

    public StreetAddress getExporterAddress() {
        return exporterAddress;
    }

    public CountryCode getImporterCountryCode() {
        return importerCountryCode;
    }

    public String getImporterName() {
        return importerName;
    }

    public StreetAddress getImporterAddress() {
        return importerAddress;
    }

    public String getExportProduct() {
        return exportProduct;
    }

    public Money getAppAmount() {
        return appAmount;
    }

//    public TrackingId getTrackingId() {
//        return trackingId;
//    }

    public AppStatus getAppStatus() {
        return appStatus;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }
    // ***** getter 영역 END *****


    // ***** builder 영역 START *****
    private App(Builder builder) {
        super.setId(builder.appId);
        customerId = builder.customerId;
        exporterName = builder.exporterName;
        exporterAddress = builder.exporterAddress;
        importerCountryCode = builder.importerCountryCode;
        importerName = builder.importerName;
        importerAddress = builder.importerAddress;
        exportProduct = builder.exportProduct;
        appAmount = builder.appAmount;
//        trackingId = builder.trackingId;
        appStatus = builder.appStatus;
        failureMessages = builder.failureMessages;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private AppId appId;
        private CustomerId customerId;
        private String exporterName;
        private StreetAddress exporterAddress;
        private CountryCode importerCountryCode;
        private String importerName;
        private StreetAddress importerAddress;
        private String exportProduct;
        private Money appAmount;
//        private TrackingId trackingId;
        private AppStatus appStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public Builder appId(AppId val) {
            appId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder exporterName(String val) {
            exporterName = val;
            return this;
        }

        public Builder exporterAddress(StreetAddress val) {
            exporterAddress = val;
            return this;
        }

        public Builder importerCountryCode(CountryCode val) {
            importerCountryCode = val;
            return this;
        }

        public Builder importerName(String val) {
            importerName = val;
            return this;
        }

        public Builder importerAddress(StreetAddress val) {
            importerAddress = val;
            return this;
        }

        public Builder exportProduct(String val) {
            exportProduct = val;
            return this;
        }

        public Builder appAmount(Money val) {
            appAmount = val;
            return this;
        }

//        public Builder trackingId(TrackingId val) {
//            trackingId = val;
//            return this;
//        }

        public Builder appStatus(AppStatus val) {
            appStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public App build() {
            return new App(this);
        }
    }
    // ***** builder 영역 END *****




}
