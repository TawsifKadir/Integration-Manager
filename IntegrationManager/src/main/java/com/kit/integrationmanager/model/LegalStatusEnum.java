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
public enum LegalStatusEnum {
    HOST,
    RETURNEE,
    REFUGEE,
    IDP
}
*/
public enum LegalStatusEnum {
    SELECT("Select Legal Status"),
    HOST("Host"),
    RETURNEE("Returnee"),
    REFUGEE("Refugee"),
    IDP("Idp");

    private final String value;

    LegalStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String[] getArray() {
        LegalStatusEnum[] values = LegalStatusEnum.values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].getValue();
        }
        return array;
    }

    public static LegalStatusEnum find(String value) {
        for (LegalStatusEnum legalStatus : LegalStatusEnum.values()) {
            if (legalStatus.getValue().equals(value)) {
                return legalStatus;
            }
        }
        return null;
    }
}
