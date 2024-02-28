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
public enum SelectionReasonEnum {
    LIPW_REASON_1("Poor household with no sufficient income to sustain the household"),
    LIPW_REASON_2("Household contain able bodied youth member (18-35)"),
    LIPW_REASON_3("Household headed by young men and women between the ages of 18 and 35"),
    LIPW_REASON_4("Many members who are dependents (HH with dependants greater than 3)"),
    LIPW_REASON_5("Poor household which have persons with severe disabilities"),
    DIS_REASON_1("Child headed households with no alternate income support"),
    DIS_REASON_2("Elderly headed household lacking alternate income support and able bodied member"),
    DIS_REASON_3("Persons with disability headed household lacking alternate income support and able bodied member"),
    DIS_REASON_4("Chronically ill headed household lacking alternate income and able bodied member"),
    DIS_REASON_5("Female headed household lacking alternate income support and able-bodied member");
    
    String value;
    
    SelectionReasonEnum(String value){
        this.value = value;
    }
    
    public String getValue(){
        return this.value;
    }
}

 */

import java.util.ArrayList;
import java.util.List;

public enum SelectionReasonEnum {
    SELECT("Select Selection Reason"),
    LIPW_REASON_1("Poor household with no sufficient income to sustain the household"),
    LIPW_REASON_2("Household contain able bodied youth member (18-35)"),
    LIPW_REASON_3("Household headed by young men and women between the ages of 18 and 35"),
    LIPW_REASON_4("Many members who are dependents (HH with dependants greater than 3)"),
    LIPW_REASON_5("Poor household which have persons with severe disabilities"),
    DIS_REASON_1("Child headed households with no alternate income support"),
    DIS_REASON_2("Elderly headed household lacking alternate income support and able bodied member"),
    DIS_REASON_3("Persons with disability headed household lacking alternate income support and able bodied member"),
    DIS_REASON_4("Chronically ill headed household lacking alternate income and able bodied member"),
    DIS_REASON_5("Female headed household lacking alternate income support and able-bodied member");

    private final String value;

    SelectionReasonEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String[] getArray() {
        SelectionReasonEnum[] values = SelectionReasonEnum.values();
        List<String> list = new ArrayList<>();
        for (SelectionReasonEnum reason : values) {
            list.add(reason.getValue());
        }
        return list.toArray(new String[0]);
    }

    public static String[] getArrayLipw() {
        SelectionReasonEnum[] values = SelectionReasonEnum.values();
        List<String> list = new ArrayList<>();
        for (SelectionReasonEnum reason : values) {
            if (reason.name().toLowerCase().contains("lipw") || reason == SELECT) {
                list.add(reason.getValue());
            }
        }
        return list.toArray(new String[0]);
    }

    public static String[] getArrayDis() {
        SelectionReasonEnum[] values = SelectionReasonEnum.values();
        List<String> list = new ArrayList<>();
        for (SelectionReasonEnum reason : values) {
            if (reason.name().toLowerCase().contains("dis") || reason == SELECT) {
                list.add(reason.getValue());
            }
        }
        return list.toArray(new String[0]);
    }

    public static SelectionReasonEnum find(String value) {
        for (SelectionReasonEnum reason : SelectionReasonEnum.values()) {
            if (reason.getValue().equals(value)) {
                return reason;
            }
        }
        return null;
    }
}
