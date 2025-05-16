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
}
