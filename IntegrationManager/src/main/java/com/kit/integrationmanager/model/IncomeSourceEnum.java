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
    SELECT(1,"Select Income Source"),
    NONE(2,"None"),
    SELLING_OF_FARM(3,"Selling of farm"),
    SELLING_OF_NON_FARM(4,"Selling of non farm"),
    CAUSAL_LABOUR(5,"Causal labour"),
    FORMAL_EMPLOYMENT(6,"Formal employment"),
    REMITTANCES(7,"Remittances"),
    GIFT(8,"Gift"),
    FROM_GOVT(9,"From govt"),
    FROM_NGO(10,"From ngo"),
    PENSION(11,"Pension"),
    OTHER(12,"Other");

    private final String value;
    private final int id;

    public int getId() {
        return id;
    }

    IncomeSourceEnum(int id, String value) {
        this.id = id;
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

    public static IncomeSourceEnum getIncomeSourceById(int id){
        IncomeSourceEnum[] incomeSourceList =  IncomeSourceEnum.values();
        for(IncomeSourceEnum nowType:incomeSourceList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }

}
