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
public enum SelectionCriteriaEnum {
    LIPW,
    DIS
}
*/
public enum SelectionCriteriaEnum {
    SELECT("Select Selection Criteria"),
    LIPW("LIPW"),
    DIS("DIS");

    private final String value;

    SelectionCriteriaEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String[] getArray() {
        SelectionCriteriaEnum[] values = SelectionCriteriaEnum.values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].getValue();
        }
        return array;
    }

    public static SelectionCriteriaEnum find(String value) {
        for (SelectionCriteriaEnum criteria : SelectionCriteriaEnum.values()) {
            if (criteria.getValue().equals(value)) {
                return criteria;
            }
        }
        return null;
    }
}
