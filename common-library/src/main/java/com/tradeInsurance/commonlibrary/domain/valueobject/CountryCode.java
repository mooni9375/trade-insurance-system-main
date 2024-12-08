package com.tradeInsurance.commonlibrary.domain.valueobject;

public enum CountryCode {
    // 주요 15개 국가
    KOREA("KR", "South Korea", false),
    USA("US", "United States", false),
    CANADA("CA", "Canada", false),
    JAPAN("JP", "Japan", false),
    CHINA("CN", "China", false),
    GERMANY("DE", "Germany", false),
    FRANCE("FR", "France", false),
    UNITED_KINGDOM("GB", "Unitevd Kingdom", false),
    ITALY("IT", "Italy", false),
    AUSTRALIA("AU", "Australia", false),
    BRAZIL("BR", "Brazil", false),
    INDIA("IN", "India", false),
    RUSSIA("RU", "Russia", true),
    MEXICO("MX", "Mexico", false),
    SPAIN("ES", "Spain", false);

    private final String code;
    private final String countryName;
    private final boolean highRiskCountry;

    CountryCode(String code, String countryName, boolean highRiskCountry) {
        this.code = code;
        this.countryName = countryName;
        this.highRiskCountry = highRiskCountry;
    }

    public String getCode() {
        return code;
    }

    public String getCountryName() {
        return countryName;
    }

    public boolean isHighRiskCountry() {
        return highRiskCountry;
    }

    public static CountryCode fromCode(String code) {
        for (CountryCode countryCode : values()) {
            if (countryCode.code.equalsIgnoreCase(code)) {
                return countryCode;
            }
        }
        throw new IllegalArgumentException("Invalid country code: " + code);
    }
}

