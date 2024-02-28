package com.kit.integrationmanager.model;

public enum DocumentTypeEnum {
    PASSPORT("Passport"),
    NATIONAL_ID("National Id"),
    OTHER("Other"),
    NONE("None");

    String value;

    DocumentTypeEnum(String value) {
        this.value = value;
    }

}
