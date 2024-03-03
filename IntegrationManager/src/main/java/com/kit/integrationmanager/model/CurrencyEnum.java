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
    SELECT(1,"Select Currency"),
    SUDANESE_POUND(2,"Sudanese pound"),
    USD(3,"Usd"),
    POUND(4,"Pound"),
    EURO(5,"Euro");

    private final String value;
    private final int id;

    public int getId() {
        return id;
    }

    CurrencyEnum(int id, String value) {
        this.id = id;
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
    public static CurrencyEnum getCurrencyById(int id){
        CurrencyEnum[] currencyList =  CurrencyEnum.values();
        for(CurrencyEnum nowType:currencyList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }

}
