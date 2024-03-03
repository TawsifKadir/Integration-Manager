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
public enum NomineeOccupationEnum {
    SELECT(1,"Select Nominee Occupation"),
    FORMAL_JOB(2,"Employed in a formal job"),
    CASUAL_WORK_OR_DAILY_WAGE_LABOR(3,"Casual work or daily wage labor"),
    SELF_EMPLOYED_BUSINESS_TRADE(4,"Self-employed in a business or trade"),
    AGRICULTURAL_WORK_OR_FARMING(5,"Agricultural work or farming"),
    HOMEMAKER(6,"Homemaker (manages household activities)"),
    UNEMPLOYED_AND_ACTIVELY_SEEKING_WORK(7,"Unemployed and actively seeking work"),
    STUDENT(8,"Student or in education"),
    DISABLE(9,"Disabled or chronically ill"),
    RETIRED(10,"Retired or not working"),
    OTHERS(11,"Other (specify)");

    private final String value;
    private final int id;

    public int getId() {
        return id;
    }

    NomineeOccupationEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static NomineeOccupationEnum getNomineeOccupationById(int id){
        NomineeOccupationEnum[] nomineeOccupationList =  NomineeOccupationEnum.values();
        for(NomineeOccupationEnum nowType:nomineeOccupationList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }

    public static String[] getArray() {
        NomineeOccupationEnum[] values = NomineeOccupationEnum.values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].getValue();
        }
        return array;
    }

    public static NomineeOccupationEnum find(String value) {
        for (NomineeOccupationEnum enumtype : NomineeOccupationEnum.values()) {
            if (enumtype.getValue().equals(value)) {
                return enumtype;
            }
        }
        return null;
    }
}
