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

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public BiometricType getBiometricType() {
        return biometricType;
    }

    public void setBiometricType(BiometricType biometricType) {
        this.biometricType = biometricType;
    }

    public BiometricUserType getBiometricUserType() {
        return biometricUserType;
    }

    public void setBiometricUserType(BiometricUserType biometricUserType) {
        this.biometricUserType = biometricUserType;
    }

    public String getBiometricUrl() {
        return biometricUrl;
    }

    public void setBiometricUrl(String biometricUrl) {
        this.biometricUrl = biometricUrl;
    }

    public byte[] getBiometricData() {
        return biometricData;
    }

    public void setBiometricData(byte[] biometricData) {
        this.biometricData = biometricData;
    }

    public Boolean getNoFingerPrint() {
        return noFingerPrint;
    }

    public void setNoFingerPrint(Boolean noFingerPrint) {
        this.noFingerPrint = noFingerPrint;
    }

    public NoFingerprintReasonEnum getNoFingerprintReason() {
        return noFingerprintReason;
    }

    public void setNoFingerprintReason(NoFingerprintReasonEnum noFingerprintReason) {
        this.noFingerprintReason = noFingerprintReason;
    }

    public String getNoFingerprintReasonText() {
        return noFingerprintReasonText;
    }

    public void setNoFingerprintReasonText(String noFingerprintReasonText) {
        this.noFingerprintReasonText = noFingerprintReasonText;
    }
}
