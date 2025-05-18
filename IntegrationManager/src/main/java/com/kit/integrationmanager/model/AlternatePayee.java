package com.kit.integrationmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AlternatePayee {
    @JsonProperty("payeeFirstName")
    @SerializedName("payeeFirstName")
    private String payeeFirstName;
    @JsonProperty("payeeMiddleName")
    @SerializedName("payeeMiddleName")
    private String payeeMiddleName;
    @JsonProperty("payeeLastName")
    @SerializedName("payeeLastName")
    private String payeeLastName;
    @JsonProperty("payeeNickName")
    @SerializedName("payeeNickName")
    private String payeeNickName;
    @JsonProperty("payeeAge")
    @SerializedName("payeeAge")
    private Integer payeeAge;
    @JsonProperty("payeeGender")
    @SerializedName("payeeGender")
    private GenderEnum payeeGender;
    @JsonProperty("documentType")
    @SerializedName("documentType")
    private DocumentTypeEnum documentType;
    @JsonProperty("documentTypeOther")
    @SerializedName("documentTypeOther")
    private String documentTypeOther;
    @JsonProperty("relationshipWithHouseholdHead")
    @SerializedName("relationshipWithHouseholdHead")
    private RelationshipEnum relationshipWithHouseholdHead;
    @JsonProperty("relationshipOther")
    @SerializedName("relationshipOther")
    private String relationshipOther;
    @JsonProperty("nationalId")
    @SerializedName("nationalId")
    private String nationalId;
    @JsonProperty("payeePhoneNo")
    @SerializedName("payeePhoneNo")
    private String payeePhoneNo;
    @JsonProperty("biometrics")
    @SerializedName("biometrics")
    private List<Biometric> biometrics;

    public String getPayeeFirstName() {
        return payeeFirstName;
    }

    public void setPayeeFirstName(String payeeFirstName) {
        this.payeeFirstName = payeeFirstName;
    }

    public String getPayeeMiddleName() {
        return payeeMiddleName;
    }

    public void setPayeeMiddleName(String payeeMiddleName) {
        this.payeeMiddleName = payeeMiddleName;
    }

    public String getPayeeLastName() {
        return payeeLastName;
    }

    public void setPayeeLastName(String payeeLastName) {
        this.payeeLastName = payeeLastName;
    }

    public String getPayeeNickName() {
        return payeeNickName;
    }

    public void setPayeeNickName(String payeeNickName) {
        this.payeeNickName = payeeNickName;
    }

    public Integer getPayeeAge() {
        return payeeAge;
    }

    public void setPayeeAge(Integer payeeAge) {
        this.payeeAge = payeeAge;
    }

    public GenderEnum getPayeeGender() {
        return payeeGender;
    }

    public void setPayeeGender(GenderEnum payeeGender) {
        this.payeeGender = payeeGender;
    }

    public DocumentTypeEnum getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentTypeEnum documentType) {
        this.documentType = documentType;
    }

    public String getDocumentTypeOther() {
        return documentTypeOther;
    }

    public void setDocumentTypeOther(String documentTypeOther) {
        this.documentTypeOther = documentTypeOther;
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

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPayeePhoneNo() {
        return payeePhoneNo;
    }

    public void setPayeePhoneNo(String payeePhoneNo) {
        this.payeePhoneNo = payeePhoneNo;
    }

    public List<Biometric> getBiometrics() {
        return biometrics;
    }

    public void setBiometrics(List<Biometric> biometrics) {
        this.biometrics = biometrics;
    }
}
