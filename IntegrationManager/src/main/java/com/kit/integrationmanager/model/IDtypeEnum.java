/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kit.integrationmanager.model;

import com.kit.integrationmanager.listener.EnumListener;

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
public enum IDtypeEnum implements EnumListener {
    SELECT(1,"Select ID Type"),
    PASSPORT(2,"Passport"),
    NATIONAL_ID(3,"National Id"),
    OTHERS(4,"Other");

    private final String value;
    private final int id;

    @Override
    public String getDisplayValue() {
        return this.value;
    }

    @Override
    public int getId() {
        return id;
    }

    IDtypeEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String[] getArray() {
        IDtypeEnum[] values = IDtypeEnum.values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].getValue();
        }
        return array;
    }

    public static IDtypeEnum find(String value) {
        for (IDtypeEnum iDtypeEnum : IDtypeEnum.values()) {
            if (iDtypeEnum.getValue().equals(value)) {
                return iDtypeEnum;
            }
        }
        return null;
    }

    public static IDtypeEnum getIdTypeById(int id){
        IDtypeEnum[] idTypeList =  IDtypeEnum.values();
        for(IDtypeEnum nowType:idTypeList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }
}
