package com.kit.integrationmanager.model;

import lombok.Data;

@Data
public class HouseholdMember {
    private String applicationId = null;
    private Integer maleTotal = 0;
    private Integer femaleTotal = 0;
    private Integer femaleChronicalIll = 0;
    private Integer femaleDisable = 0;
    private Integer femaleBoth = 0;
    private Integer maleChronicalIll = 0;
    private Integer maleDisable = 0;
    private Integer maleBoth = 0;
}
