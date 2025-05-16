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

public enum RelationshipEnum implements EnumListener {

    SELECT(1,"Select Relationship"),
    HOUSEHOLD_HEAD(2,"Household head"),
    SPOUSE(3,"Spouse"),
    SON_DAUGHTER(4,"Son/daughter"),
    SON_DAUGHTER_IN_LAW(5,"Son/daughter in law"),
    GRANDCHILD(6,"Grandchild"),
    PARENT(7,"Parent"),
    PARENT_IN_LAW(8,"Parent-in-law"),
    SIBLING_IN_LAW(9,"Sibling-in-law"),
    SIBLING(10,"Sibling"),
    OTHER(11,"Other"),
    DOMESTIC_WORKER(12,"Domestic worker"),
    NO_RELATION(13,"No relation");

    private int id;
    private String value;

    RelationshipEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String getDisplayValue() {
        return this.value;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }


    public static RelationshipEnum getRelationById(int id){
        RelationshipEnum[] relationList =  RelationshipEnum.values();
        for(RelationshipEnum nowType:relationList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }

    public static RelationshipEnum find(String value) {
        for (RelationshipEnum type : RelationshipEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
    public static String[] getArray() {
        RelationshipEnum[] values = RelationshipEnum.values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].getValue();
        }
        return array;
    }

}
