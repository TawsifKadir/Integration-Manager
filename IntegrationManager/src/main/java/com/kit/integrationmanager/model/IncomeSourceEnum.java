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
public enum IncomeSourceEnum {
    NONE,
    SELLING_OF_FARM,
    SELLING_OF_NON_FARM,
    CAUSAL_LABOUR,
    FORMAL_EMPLOYMENT,
    REMITTANCES,
    GIFT,
    FROM_GOVT,
    FROM_NGO,
    PENSION,
    OTHER
}
*/
public enum IncomeSourceEnum {
    SELECT("Select Income Source"),
    NONE("None"),
    SELLING_OF_FARM("Selling of farm"),
    SELLING_OF_NON_FARM("Selling of non farm"),
    CAUSAL_LABOUR("Causal labour"),
    FORMAL_EMPLOYMENT("Formal employment"),
    REMITTANCES("Remittances"),
    GIFT("Gift"),
    FROM_GOVT("From govt"),
    FROM_NGO("From ngo"),
    PENSION("Pension"),
    OTHER("Other");

    private final String value;

    IncomeSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String[] getArray() {
        IncomeSourceEnum[] values = IncomeSourceEnum.values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].getValue();
        }
        return array;
    }

    public static IncomeSourceEnum find(String value) {
        for (IncomeSourceEnum incomeSource : IncomeSourceEnum.values()) {
            if (incomeSource.getValue().equals(value)) {
                return incomeSource;
            }
        }
        return null;
    }
}
