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
    SELECT("Select Nominee Occupation"),
    FORMAL_JOB("Employed in a formal job"),
    CASUAL_WORK_OR_DAILY_WAGE_LABOR("Casual work or daily wage labor"),
    SELF_EMPLOYED_BUSINESS_TRADE("Self-employed in a business or trade"),
    AGRICULTURAL_WORK_OR_FARMING("Agricultural work or farming"),
    HOMEMAKER("Homemaker (manages household activities)"),
    UNEMPLOYED_AND_ACTIVELY_SEEKING_WORK("Unemployed and actively seeking work"),
    STUDENT("Student or in education"),
    DISABLE("Disabled or chronically ill"),
    RETIRED("Retired or not working"),
    OTHERS("Other (specify)");

    private final String value;

    NomineeOccupationEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
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
