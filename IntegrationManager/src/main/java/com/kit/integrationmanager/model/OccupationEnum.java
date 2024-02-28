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
public enum OccupationEnum {
    
    FORMAL_JOB("Formal Job"),
    CASUAL_WORK("Casual Work"),
    SELF_EMPLOYED("Self Employed"),
    AGRICULTURAL_WORK("Agricultural Work"),
    HOMEMAKER("Homemaker"),
    UNEMPLOYED("Unemployed"),
    STUDENT("Student"),
    DISABLED("Disabled"),
    RETIRED("Retired"),
    OTHER("Other");

    private String value;

    OccupationEnum(String value) {
        this.value = value;
    }
}
