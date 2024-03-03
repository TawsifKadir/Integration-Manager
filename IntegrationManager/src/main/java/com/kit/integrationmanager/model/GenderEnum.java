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
public enum GenderEnum {
    SELECT(1,"Select Gender"),
    MALE(2,"Male"),
    FEMALE(3,"Female");

    private final String value;

    public int getId() {
        return id;
    }

    private final int id;

    GenderEnum(int id,String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String[] getArray() {
        GenderEnum[] values = GenderEnum.values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].getValue();
        }
        return array;
    }

    public static GenderEnum find(String value) {
        for (GenderEnum gender : GenderEnum.values()) {
            if (gender.getValue().equals(value)) {
                return gender;
            }
        }
        return null;
    }

    public static GenderEnum getGenderById(int id){
        GenderEnum[] genderList =  GenderEnum.values();
        for(GenderEnum nowType:genderList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }

}
