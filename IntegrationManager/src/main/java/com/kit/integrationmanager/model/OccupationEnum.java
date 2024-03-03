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
public enum OccupationEnum {
    
    FORMAL_JOB(1,"Formal Job"),
    CASUAL_WORK(2,"Casual Work"),
    SELF_EMPLOYED(3,"Self Employed"),
    AGRICULTURAL_WORK(4,"Agricultural Work"),
    HOMEMAKER(5,"Homemaker"),
    UNEMPLOYED(6,"Unemployed"),
    STUDENT(7,"Student"),
    DISABLED(8,"Disabled"),
    RETIRED(9,"Retired"),
    OTHER(10,"Other");

    private final String value;
    private int id;

    public int getId() {
        return id;
    }

    OccupationEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String[] getArray() {
        OccupationEnum[] values = OccupationEnum.values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].getValue();
        }
        return array;
    }

    public static OccupationEnum find(String value) {
        for (OccupationEnum occupation : OccupationEnum.values()) {
            if (occupation.getValue().equals(value)) {
                return occupation;
            }
        }
        return null;
    }

    public static OccupationEnum getOccupationById(int id){
        OccupationEnum[] occupationList =  OccupationEnum.values();
        for(OccupationEnum nowType:occupationList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }

}
