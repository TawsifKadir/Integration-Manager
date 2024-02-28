/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kit.integrationmanager.model;

/**
 *
 * @author anwar
 */
/*
public enum MaritalStatusEnum {
    SINGLE,
    MARRIED,
    WIDOW,
    SEPARATED,
    DIVORCE
}
 */
public enum MaritalStatusEnum {
    SELECT("Select Marital Status"),
    SINGLE("Single"),
    MARRIED("Married"),
    WIDOW("Widow"),
    SEPARATED("Separated"),
    DIVORCE("Divorce");

    private final String value;

    MaritalStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String[] getArray() {
        MaritalStatusEnum[] values = MaritalStatusEnum.values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].getValue();
        }
        return array;
    }

    public static MaritalStatusEnum find(String value) {
        for (MaritalStatusEnum maritalStatus : MaritalStatusEnum.values()) {
            if (maritalStatus.getValue().equals(value)) {
                return maritalStatus;
            }
        }
        return null;
    }
}
