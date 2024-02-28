package com.kit.integrationmanager.model;

import lombok.Data;

@Data
public class HouseholdMember {
    private String applicationId = null;
    private Integer totalMale = 0;
    private Integer totalFemale = 0;
    private Integer femaleChronicalIll = 0;
    private Integer femaleDisable = 0;
    private Integer femaleNormal = 0;
    private Integer maleChronicalIll = 0;
    private Integer maleDisable = 0;
    private Integer maleNormal = 0;
}
