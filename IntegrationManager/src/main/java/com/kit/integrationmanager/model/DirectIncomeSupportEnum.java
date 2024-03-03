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
    NO_ALTERNATE_INCOME_SUPPORT(1,"Child headed households with no alternate income support"),
    LACKING_ALTERNATE_INCOME_SUPPORT(2,"Elderly headed household lacking alternate income support and able bodied member"),
    PERSON_WITH_DISABILITY_LACKING_ALTERNATE_INCOME_SUPPORT(3,"Persons with disability headed household lacking alternate income support and able bodied member"),
    CHRONICALLY_ILL_ALTERNATE_INCOME_SUPPORT(4,"Chronically ill headed household lacking alternate income and able bodied member"),
    FEMALE_HEADED_ALTERNATE_INCOME_SUPPORT(5,"Female headed household lacking alternate income support and able-bodied member");

    private final String value;
    private final int id;

    public int getId() {
        return id;
    }

    DirectIncomeSupportEnum(int id, String value) {
        this.id = id;
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
    public static DirectIncomeSupportEnum getDirectSupportById(int id){
        DirectIncomeSupportEnum[] directSupportList =  DirectIncomeSupportEnum.values();
        for(DirectIncomeSupportEnum nowType:directSupportList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }

}
