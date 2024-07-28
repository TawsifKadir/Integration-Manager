package com.kit.integrationmanager.payload.download.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayrollLockRequest {
    @JsonProperty("state")
    @SerializedName("state")
    private int state;
    @JsonProperty("county")
    @SerializedName("county")
    private int county;
    @JsonProperty("payam")
    @SerializedName("payam")
    private int payam;
    @JsonProperty("boma")
    @SerializedName("boma")
    private long boma;
    @JsonProperty("wagePaymentReqId")
    @SerializedName("wagePaymentReqId")
    private int wagePaymentReqId;
    @JsonProperty("paymentCycle")
    @SerializedName("paymentCycle")
    private String paymentCycle;
    @JsonProperty("supportType")
    @SerializedName("supportType")
    private String supportType;
}
