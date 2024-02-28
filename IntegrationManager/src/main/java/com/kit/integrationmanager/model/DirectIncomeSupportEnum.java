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
public enum GenderEnum {
    MALE,
    FEMALE
}
 */
public enum DirectIncomeSupportEnum {
    NO_ALTERNATE_INCOME_SUPPORT("Child headed households with no alternate income support"),
    LACKING_ALTERNATE_INCOME_SUPPORT("Elderly headed household lacking alternate income support and able bodied member"),
    PERSON_WITH_DISABILITY_LACKING_ALTERNATE_INCOME_SUPPORT("Persons with disability headed household lacking alternate income support and able bodied member"),
    CHRONICALLY_ILL_ALTERNATE_INCOME_SUPPORT("Chronically ill headed household lacking alternate income and able bodied member"),
    FEMALE_HEADED_ALTERNATE_INCOME_SUPPORT("Female headed household lacking alternate income support and able-bodied member");

    private final String value;

    DirectIncomeSupportEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String[] getArray() {
        DirectIncomeSupportEnum[] values = DirectIncomeSupportEnum.values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].getValue();
        }
        return array;
    }

    public static DirectIncomeSupportEnum find(String value) {
        for (DirectIncomeSupportEnum enumtype : DirectIncomeSupportEnum.values()) {
            if (enumtype.getValue().equals(value)) {
                return enumtype;
            }
        }
        return null;
    }
}
