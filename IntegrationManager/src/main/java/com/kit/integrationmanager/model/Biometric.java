package com.kit.integrationmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Biometric {
    @JsonProperty("applicationId")
    @SerializedName("applicationId")
    private String applicationId;
    @JsonProperty("biometricType")
    @SerializedName("biometricType")
    private BiometricType biometricType;
    @JsonProperty("biometricUserType")
    @SerializedName("biometricUserType")
    private BiometricUserType biometricUserType;
    @JsonProperty("biometricUrl")
    @SerializedName("biometricUrl")
    private String biometricUrl;
    @JsonProperty("biometricData")
    @SerializedName("biometricData")
    private byte[] biometricData;
    @JsonProperty("noFingerPrint")
    @SerializedName("noFingerPrint")
    private Boolean noFingerPrint;
    @JsonProperty("noFingerprintReason")
    @SerializedName("noFingerprintReason")
    private NoFingerprintReasonEnum noFingerprintReason;
    @JsonProperty("noFingerprintReasonText")
    @SerializedName("noFingerprintReasonText")
    private String noFingerprintReasonText;
}
