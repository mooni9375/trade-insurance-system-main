package com.tradeInsurance.dataaccess.app.mapper;

import com.tradeInsurance.commonlibrary.domain.valueobject.CountryCode;
import com.tradeInsurance.dataaccess.app.entity.AppEntity;
import com.tradeInsurance.dataaccess.app.entity.AppStreetAddressEntity;
import com.tradeInsurance.commonlibrary.domain.valueobject.AppId;
import com.tradeInsurance.commonlibrary.domain.valueobject.CustomerId;
import com.tradeInsurance.commonlibrary.domain.valueobject.Money;
import com.tradeInsurance.domain.entity.App;
import com.tradeInsurance.domain.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

import static com.tradeInsurance.domain.entity.App.FAILURE_MESSAGE_DELIMITER;

@Component
public class AppDataAccessMapper {

    public AppEntity appToAppEntity(App app) {

        AppEntity appEntity = AppEntity.builder()
                .id(app.getId().getValue())
                .customerId(app.getCustomerId().getValue())
                .exporterName(app.getExporterName())
                .exporterAddress(streetAddressToAppStreetAddressEntity(app.getExporterAddress()))
                .importerCountryCode(CountryCode.fromCode(app.getImporterCountryCode().getCode()))
                .importerName(app.getImporterName())
                .importerAddress(streetAddressToAppStreetAddressEntity(app.getImporterAddress()))
                .exportProduct(app.getExportProduct())
                .appAmount(app.getAppAmount().getAmount())
                .appStatus(app.getAppStatus())
                .failureMessages(app.getFailureMessages() != null ?
                        String.join(FAILURE_MESSAGE_DELIMITER, app.getFailureMessages()) : "")
                .build();

        // 양방향 연관관계 설정
        appEntity.getExporterAddress().setApp(appEntity);
        appEntity.getImporterAddress().setApp(appEntity);

        return appEntity;
    }

    public App appEntityToApp(AppEntity appEntity) {
        return App.builder()
                .appId(new AppId(appEntity.getId()))
                .customerId(new CustomerId(appEntity.getCustomerId()))
                .exporterName(appEntity.getExporterName())
                .exporterAddress(appStreetAddressEntityToStreetAddress(appEntity.getExporterAddress()))
                .importerCountryCode(appEntity.getImporterCountryCode())
                .importerName(appEntity.getImporterName())
                .importerAddress(appStreetAddressEntityToStreetAddress(appEntity.getImporterAddress()))
                .exportProduct(appEntity.getExportProduct())
                .appAmount(new Money(appEntity.getAppAmount()))
                .appStatus(appEntity.getAppStatus())
                .failureMessages(appEntity.getFailureMessages().isEmpty() ? new ArrayList<>() :
                        new ArrayList<>(Arrays.asList(appEntity.getFailureMessages()
                                .split(FAILURE_MESSAGE_DELIMITER))))
                .build();
    }


    private AppStreetAddressEntity streetAddressToAppStreetAddressEntity(StreetAddress streetAddress) {
        return AppStreetAddressEntity.builder()
                .id(streetAddress.getId())
                .street(streetAddress.getStreet())
                .postalCode(streetAddress.getPostalCode())
                .city(streetAddress.getCity())
                .build();
    }

    private StreetAddress appStreetAddressEntityToStreetAddress(AppStreetAddressEntity appStreetAddressEntity) {
        return new StreetAddress(appStreetAddressEntity.getId(),
                appStreetAddressEntity.getStreet(),
                appStreetAddressEntity.getPostalCode(),
                appStreetAddressEntity.getCity());
    }

}
