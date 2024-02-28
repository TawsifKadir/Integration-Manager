package com.kit.integrationmanager.model;

import lombok.Data;

@Data
public class Biometric {
    private String applicationId;
    private BiometricType biometricType;
    private BiometricUserType biometricUserType;
    private String biometricUrl;
    private byte[] biometricData;
    private Boolean noFingerPrint;
    private NoFingerprintReasonEnum noFingerprintReason;
    private String noFingerprintReasonText;
}
