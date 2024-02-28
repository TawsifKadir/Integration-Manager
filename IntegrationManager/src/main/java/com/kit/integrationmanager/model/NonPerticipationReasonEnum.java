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
public enum NonPerticipationReasonEnum {
    
    REASON_1("All eligible household members have other commitments that occupy their time"),
    REASON_2("I am uncertain about the ability of the household members to comply with the program's expectations or conditions"),
    REASON_3("The health or physical condition of the eligible household members prevents me from participating"),
    REASON_4("I am not convinced that the program will provide meaningful benefits"),
    REASON_5("The program activities don't align with my interests"),
    REASON_OTHER("Other");
    
    String reason;
    
    NonPerticipationReasonEnum(String reason){
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}

 */
import java.util.ArrayList;
import java.util.List;

public enum NonPerticipationReasonEnum {
    SELECT("Select Non Participation Reason"),
    REASON_1("All other eligible household members have other commitments that occupy their time"),
    REASON_2("I am uncertain about the ability of the other household members to comply with the program's expectations or conditions"),
    REASON_3("The health or physical condition of the other eligible household members prevents me from participating"),
    REASON_4("I am not convinced that the program will provide meaningful benefits"),
    REASON_5("The program activities don't align with my interests"),
    REASON_6("All eligible household members have already been nominated"),
    REASON_OTHER("Other");

    private final String value;

    NonPerticipationReasonEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String[] getArray() {
        NonPerticipationReasonEnum[] values = NonPerticipationReasonEnum.values();
        List<String> list = new ArrayList<>();
        for (NonPerticipationReasonEnum reason : values) {
            list.add(reason.getValue());
        }
        return list.toArray(new String[0]);
    }

    public static NonPerticipationReasonEnum find(String value) {
        for (NonPerticipationReasonEnum reason : NonPerticipationReasonEnum.values()) {
            if (reason.getValue().equals(value)) {
                return reason;
            }
        }
        return null;
    }
}

