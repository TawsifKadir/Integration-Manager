package com.kit.integrationmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeneficiaryEdit {
    @JsonProperty("applicationId")
    @SerializedName("applicationId")
    private String applicationId;
    @JsonProperty("alternateUpdate")
    @SerializedName("alternateUpdate")
    private Boolean alternateUpdate;
    @JsonProperty("beneficiaryUpdate")
    @SerializedName("beneficiaryUpdate")
    private Boolean beneficiaryUpdate;
    @JsonProperty("biometrics")
    @SerializedName("biometrics")
    private List<Biometric> biometrics;
    @JsonProperty("alternatePayee1")
    @SerializedName("alternatePayee1")
    private AlternatePayee alternatePayee1;
    @JsonProperty("alternatePayee2")
    @SerializedName("alternatePayee2")
    private AlternatePayee alternatePayee2;
    @JsonProperty("updatedBy")
    @SerializedName("updatedBy")
    private Long updatedBy;
}
