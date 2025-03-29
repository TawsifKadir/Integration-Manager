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
public enum GenderEnum {
    MALE,
    FEMALE
}
 */
public enum PublicWorkEnum implements EnumListener {
    POOR_HOUSEHOLD(1,"Poor household with no sufficient income to sustain the household"),
    BODIED_YOUTH_MEMBER(2,"Household contain able bodied youth member (18–35)"),
    HEADED_YOUNG_MEMBER(3,"Household headed by young men and women between the ages of 18 and 35"),
    HOUSEHOLD_THREE_OR_MORE_DEPENDENTS(4,"Household has three or more dependents"),
    POOR_HOUSEHOLD_WITH_SEVERE_DISABILITIES(5,"Poor household which have persons with severe disabilities");

    private final String value;

    @Override
    public String getDisplayValue() {
        return this.value;
    }

    @Override
    public int getId() {
        return id;
    }

    private final int id;

    PublicWorkEnum(int id,String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String[] getArray() {
        PublicWorkEnum[] values = PublicWorkEnum.values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].getValue();
        }
        return array;
    }

    public static PublicWorkEnum find(String value) {
        for (PublicWorkEnum enumtype : PublicWorkEnum.values()) {
            if (enumtype.getValue().equals(value)) {
                return enumtype;
            }
        }
        return null;
    }
    public static PublicWorkEnum getPublicWorkById(int id){
        PublicWorkEnum[] publicWorkList =  PublicWorkEnum.values();
        for(PublicWorkEnum nowType:publicWorkList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }

}
