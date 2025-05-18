package com.kit.integrationmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class HouseholdMember {

    @JsonProperty("applicationId")
    @SerializedName("applicationId")
    private String applicationId;

    @JsonProperty("maleTotal")
    @SerializedName("maleTotal")
    private Integer maleTotal;

    @JsonProperty("femaleTotal")
    @SerializedName("femaleTotal")
    private Integer femaleTotal;

    @JsonProperty("femaleChronicalIll")
    @SerializedName("femaleChronicalIll")
    private Integer femaleChronicalIll;

    @JsonProperty("femaleDisable")
    @SerializedName("femaleDisable")
    private Integer femaleDisable;

    @JsonProperty("femaleBoth")
    @SerializedName("femaleBoth")
    private Integer femaleBoth;

    @JsonProperty("maleChronicalIll")
    @SerializedName("maleChronicalIll")
    private Integer maleChronicalIll;

    @JsonProperty("maleDisable")
    @SerializedName("maleDisable")
    private Integer maleDisable;

    @JsonProperty("maleBoth")
    @SerializedName("maleBoth")
    private Integer maleBoth;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getMaleTotal() {
        return maleTotal;
    }

    public void setMaleTotal(Integer maleTotal) {
        this.maleTotal = maleTotal;
    }

    public Integer getFemaleTotal() {
        return femaleTotal;
    }

    public void setFemaleTotal(Integer femaleTotal) {
        this.femaleTotal = femaleTotal;
    }

    public Integer getFemaleChronicalIll() {
        return femaleChronicalIll;
    }

    public void setFemaleChronicalIll(Integer femaleChronicalIll) {
        this.femaleChronicalIll = femaleChronicalIll;
    }

    public Integer getFemaleDisable() {
        return femaleDisable;
    }

    public void setFemaleDisable(Integer femaleDisable) {
        this.femaleDisable = femaleDisable;
    }

    public Integer getFemaleBoth() {
        return femaleBoth;
    }

    public void setFemaleBoth(Integer femaleBoth) {
        this.femaleBoth = femaleBoth;
    }

    public Integer getMaleChronicalIll() {
        return maleChronicalIll;
    }

    public void setMaleChronicalIll(Integer maleChronicalIll) {
        this.maleChronicalIll = maleChronicalIll;
    }

    public Integer getMaleDisable() {
        return maleDisable;
    }

    public void setMaleDisable(Integer maleDisable) {
        this.maleDisable = maleDisable;
    }

    public Integer getMaleBoth() {
        return maleBoth;
    }

    public void setMaleBoth(Integer maleBoth) {
        this.maleBoth = maleBoth;
    }
}
