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
public enum SelectionCriteriaEnum {
    LIPW,
    DIS
}
*/
public enum SelectionCriteriaEnum implements EnumListener {

    SELECT(1,"Select Selection Criteria","Select Selection Criteria"),
    LIPW(2,"LIPW","LIPW"),
    DIS(3,"DIS","DIS");

    private int id;
    private String value;
    private String Txt;

    SelectionCriteriaEnum(int id, String value, String txt) {
        this.id = id;
        this.value = value;
        Txt = txt;
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

    public String getTxt() {
        return Txt;
    }

    public void setTxt(String txt) {
        Txt = txt;
    }

    public static SelectionCriteriaEnum getSelectionCriteriaById(int id){
        SelectionCriteriaEnum[] selectionCriteriaList =  SelectionCriteriaEnum.values();
        for(SelectionCriteriaEnum nowType:selectionCriteriaList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }

    public static SelectionCriteriaEnum find(String value) {
        for (SelectionCriteriaEnum type : SelectionCriteriaEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String[] getArray() {
        SelectionCriteriaEnum[] values = SelectionCriteriaEnum.values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].getValue();
        }
        return array;
    }
}
