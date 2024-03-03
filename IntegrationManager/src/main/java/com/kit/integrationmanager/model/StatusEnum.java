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
public enum StatusEnum {
    ACTIVE(1,"Active","Active"),
    INACTIVE(2,"Inactive","Inactive");

    private int id;
    private String value;
    private String Txt;
    StatusEnum(int id, String value, String txt) {
        this.id = id;
        this.value = value;
        Txt = txt;
    }

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

    public static StatusEnum getStatusById(int id){
        StatusEnum[] StatusList =  StatusEnum.values();
        for(StatusEnum nowType:StatusList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }

    public static StatusEnum find(String value) {
        for (StatusEnum type : StatusEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
