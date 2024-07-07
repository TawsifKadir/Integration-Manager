package com.kit.integrationmanager.payload.download.response;

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
public class PayrollLockResponse {
    @JsonProperty("responseCode")
    @SerializedName("responseCode")
    private int responseCode;
    @JsonProperty("responseMessage")
    @SerializedName("responseMessage")
    private String responseMessage;
}
