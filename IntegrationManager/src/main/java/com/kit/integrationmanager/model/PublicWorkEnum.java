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
public enum PublicWorkEnum {
    POOR_HOUSEHOLD("Poor household with no sufficient income to sustain the household"),
    BODIED_YOUTH_MEMBER("Household contain able bodied youth member (18–35)"),
    HEADED_YOUNG_MEMBER("Household headed by young men and women between the ages of 18 and 35"),
    HOUSEHOLD_THREE_OR_MORE_DEPENDENTS("Household has three or more dependents"),
    POOR_HOUSEHOLD_WITH_SEVERE_DISABILITIES("Poor household which have persons with severe disabilities");

    private final String value;

    PublicWorkEnum(String value) {
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
}
