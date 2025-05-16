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
public enum MaritalStatusEnum {
    SINGLE,
    MARRIED,
    WIDOW,
    SEPARATED,
    DIVORCE
}
 */
public enum MaritalStatusEnum implements EnumListener {

    SELECT(1,"Select Marital Status"),
    SINGLE(2,"Single"),
    MARRIED(3,"Married"),
    WIDOW(4,"Widow"),
    SEPARATED(5,"Separated"),
    DIVORCE(6,"Divorce");

    private final String value;

    @Override
    public String getDisplayValue() {
        return this.value;
    }

    @Override
    public int getId() {
        return id;
    }

    private final int id;

    MaritalStatusEnum(int id,String value) {
        this.id = id;
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

    public static MaritalStatusEnum getMartialStatusById(int id){
        MaritalStatusEnum[] martialStatusList =  MaritalStatusEnum.values();
        for(MaritalStatusEnum nowType:martialStatusList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }

}
