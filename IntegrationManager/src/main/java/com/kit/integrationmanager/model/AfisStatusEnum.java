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
public enum AfisStatusEnum implements EnumListener {
    PENDING(1,"Pending","Pending"),
    ENROLLED(2,"Enrolled","Enrolled"),
    MATCHED(3,"Matched","Matched"),
    COMPLETED(4,"Completed","Completed"),
    REJECTED(5,"Rejected","Rejected");

    private int id;
    private String value;
    private String Txt;
    AfisStatusEnum(int id, String value, String txt) {
        this.id = id;
        this.value = value;
        Txt = txt;
    }

    @Override
    public String getDisplayValue() {
        return this.value;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTxt() {
        return Txt;
    }

    public void setTxt(String txt) {
        Txt = txt;
    }

    public static AfisStatusEnum getAfisStatusById(int id){
        AfisStatusEnum[] AfisStatusList =  AfisStatusEnum.values();
        for(AfisStatusEnum nowType:AfisStatusList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }

    public static AfisStatusEnum find(String value) {
        for (AfisStatusEnum type : AfisStatusEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String[] getArray() {
        AfisStatusEnum[] values = AfisStatusEnum.values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].getValue();
        }
        return array;
    }

}
