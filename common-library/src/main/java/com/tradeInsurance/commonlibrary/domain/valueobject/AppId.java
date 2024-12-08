package com.tradeInsurance.commonlibrary.domain.valueobject;

import java.util.UUID;

/**
 * AppId
 *  여러 Aggregate Root 사이에서 공유되는 AppId
 *  따라서 common-domain 패키지에 위치
 */
public class AppId extends BaseId<UUID> {

    public AppId(UUID value) {
        super(value);
    }
}
