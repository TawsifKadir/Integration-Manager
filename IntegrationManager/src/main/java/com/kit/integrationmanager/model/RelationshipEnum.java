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
public enum RelationshipEnum {
    HOUSEHOLD_HEAD,
    SOPUSE,
    SON_DAUGHTER,
    SON_DAUGHTER_IN_LAW,
    GRANDCHILD,
    PARENT,
    PARENT_IN_LAW,
    SIBLING,
    OTHER,
    DOMESTIC_WORKER,
    NO_RELATION,
    UNKNOWN
}

 */

public enum RelationshipEnum {
    SELECT("Select Relationship"),
    HOUSEHOLD_HEAD("Household head"),
    SPOUSE("Spouse"),
    SON_DAUGHTER("Son/daughter"),
    SON_DAUGHTER_IN_LAW("Son/daughter in law"),
    GRANDCHILD("Grandchild"),
    PARENT("Parent"),
    PARENT_IN_LAW("Parent-in-law"),
    SIBLING_IN_LAW("Sibling-in-law"),
    SIBLING("Sibling"),
    OTHER("Other"),
    DOMESTIC_WORKER("Domestic worker"),
    NO_RELATION("No relation");

    private final String value;

    RelationshipEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String[] getArray() {
        RelationshipEnum[] values = RelationshipEnum.values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].getValue();
        }
        return array;
    }

    public static RelationshipEnum find(String value) {
        for (RelationshipEnum relationship : RelationshipEnum.values()) {
            if (relationship.getValue().equals(value)) {
                return relationship;
            }
        }
        return null;
    }
}
