package com.kit.integrationmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class Nominee {

    @JsonProperty("applicationId")
    @SerializedName("applicationId")
    private String applicationId;

    @JsonProperty("nomineeFirstName")
    @SerializedName("nomineeFirstName")
    private String nomineeFirstName;

    @JsonProperty("nomineeLastName")
    @SerializedName("nomineeLastName")
    private String nomineeLastName;

    @JsonProperty("nomineeMiddleName")
    @SerializedName("nomineeMiddleName")
    private String nomineeMiddleName;

    @JsonProperty("nomineeNickName")
    @SerializedName("nomineeNickName")
    private String nomineeNickName;

    @JsonProperty("nomineeAge")
    @SerializedName("nomineeAge")
    private Integer nomineeAge;

    @JsonProperty("nomineeGender")
    @SerializedName("nomineeGender")
    private GenderEnum nomineeGender;

    @JsonProperty("nomineeOccupation")
    @SerializedName("nomineeOccupation")
    private OccupationEnum nomineeOccupation;

    @JsonProperty("otherOccupation")
    @SerializedName("otherOccupation")
    private String otherOccupation;

    @JsonProperty("relationshipWithHouseholdHead")
    @SerializedName("relationshipWithHouseholdHead")
    private RelationshipEnum relationshipWithHouseholdHead;

    @JsonProperty("relationshipOther")
    @SerializedName("relationshipOther")
    private String relationshipOther;

    @JsonProperty("isReadWrite")
    @SerializedName("isReadWrite")
    private Boolean isReadWrite;

    @JsonProperty("biometrics")
    @SerializedName("biometrics")
    private List<Biometric> biometrics;


    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getNomineeFirstName() {
        return nomineeFirstName;
    }

    public void setNomineeFirstName(String nomineeFirstName) {
        this.nomineeFirstName = nomineeFirstName;
    }

    public String getNomineeLastName() {
        return nomineeLastName;
    }

    public void setNomineeLastName(String nomineeLastName) {
        this.nomineeLastName = nomineeLastName;
    }

    public String getNomineeMiddleName() {
        return nomineeMiddleName;
    }

    public void setNomineeMiddleName(String nomineeMiddleName) {
        this.nomineeMiddleName = nomineeMiddleName;
    }

    public String getNomineeNickName() {
        return nomineeNickName;
    }

    public void setNomineeNickName(String nomineeNickName) {
        this.nomineeNickName = nomineeNickName;
    }

    public Integer getNomineeAge() {
        return nomineeAge;
    }

    public void setNomineeAge(Integer nomineeAge) {
        this.nomineeAge = nomineeAge;
    }

    public GenderEnum getNomineeGender() {
        return nomineeGender;
    }

    public void setNomineeGender(GenderEnum nomineeGender) {
        this.nomineeGender = nomineeGender;
    }

    public OccupationEnum getNomineeOccupation() {
        return nomineeOccupation;
    }

    public void setNomineeOccupation(OccupationEnum nomineeOccupation) {
        this.nomineeOccupation = nomineeOccupation;
    }

    public String getOtherOccupation() {
        return otherOccupation;
    }

    public void setOtherOccupation(String otherOccupation) {
        this.otherOccupation = otherOccupation;
    }

    public RelationshipEnum getRelationshipWithHouseholdHead() {
        return relationshipWithHouseholdHead;
    }

    public void setRelationshipWithHouseholdHead(RelationshipEnum relationshipWithHouseholdHead) {
        this.relationshipWithHouseholdHead = relationshipWithHouseholdHead;
    }

    public String getRelationshipOther() {
        return relationshipOther;
    }

    public void setRelationshipOther(String relationshipOther) {
        this.relationshipOther = relationshipOther;
    }

    public Boolean getReadWrite() {
        return isReadWrite;
    }

    public void setReadWrite(Boolean readWrite) {
        isReadWrite = readWrite;
    }

    public List<Biometric> getBiometrics() {
        return biometrics;
    }

    public void setBiometrics(List<Biometric> biometrics) {
        this.biometrics = biometrics;
    }
}
