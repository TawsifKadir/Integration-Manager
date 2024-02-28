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
public enum CurrencyEnum {
    SUDANESE_POUND,
    USD,
    POUND,
    EURO
}
*/
public enum CurrencyEnum {
    SELECT("Select Currency"),
    SUDANESE_POUND("Sudanese pound"),
    USD("Usd"),
    POUND("Pound"),
    EURO("Euro");

    private final String value;

    CurrencyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String[] getArray() {
        CurrencyEnum[] values = CurrencyEnum.values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].getValue();
        }
        return array;
    }

    public static CurrencyEnum find(String value) {
        for (CurrencyEnum currency : CurrencyEnum.values()) {
            if (currency.getValue().equals(value)) {
                return currency;
            }
        }
        return null;
    }
}
