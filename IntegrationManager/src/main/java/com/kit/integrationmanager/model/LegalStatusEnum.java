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
public enum LegalStatusEnum {
    HOST,
    RETURNEE,
    REFUGEE,
    IDP
}
*/
public enum LegalStatusEnum implements EnumListener {

    SELECT(1,"Select Legal Status"),
    HOST(2,"Host"),
    RETURNEE(3,"Returnee"),
    REFUGEE(4,"Refugee"),
    IDP(5,"Idp");

    private final String value;
    private final int id;

    @Override
    public String getDisplayValue() {
        return this.value;
    }

    @Override
    public int getId() {
        return id;
    }

    LegalStatusEnum(int id, String value) {
        this.id = id;
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

    public static LegalStatusEnum getLegalStatusById(int id){
        LegalStatusEnum[] legalStatusList =  LegalStatusEnum.values();
        for(LegalStatusEnum nowType:legalStatusList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }

}
