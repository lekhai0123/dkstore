package com.dkstore.models;

public enum Country {
    CN("China"),
    VN("Vietnam"),
    IN("India"),
    IT("Italy"),
    ID("Indonesia"),
    US("United States"),
    BR("Brazil"),
    MY("Malaysia"),
    ES("Spain"),
    TH("Thailand"),
    PH("Philippines"),
    PT("Portugal"),
    DE("Germany"),
    JP("Japan"),
    RU("Russia"),
    MX("Mexico"),
    FR("France"),
    SA("South Africa"),
    KR("South Korea"),
    PL("Poland"),
    EG("Egypt"),
    TR("Turkey"),
    SG("Singapore"),
    UA("Ukraine"),
    BE("Belgium"),
    NL("Netherlands"),
    CZ("Czech Republic"),
    HU("Hungary"),
    SK("Slovakia"),
    RO("Romania"),
    BG("Bulgaria"),
    GR("Greece"),
    AT("Austria"),
    SE("Sweden"),
    DK("Denmark"),
    FI("Finland"),
    NO("Norway"),
    CH("Switzerland"),
    LU("Luxembourg"),
    AR("Argentina"),
    CO("Colombia"),
    KE("Kenya"),
    UG("Uganda"),
    MA("Morocco"),
    CL("Chile"),
    AE("United Arab Emirates"),
    QA("Qatar"),
    KW("Kuwait"),
    OM("Oman"),
    LY("Libya");

    private final String name;

    Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
