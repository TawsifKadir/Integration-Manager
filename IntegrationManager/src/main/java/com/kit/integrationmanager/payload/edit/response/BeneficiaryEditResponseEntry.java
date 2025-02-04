package com.kit.integrationmanager.payload.edit.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeneficiaryEditResponseEntry {
    @JsonProperty("applicationId")
    @SerializedName("applicationId")
    private String applicationId;
    @JsonProperty("requestId")
    @SerializedName("requestId")
    private String requestId;
    @JsonProperty("errorMessage")
    @SerializedName("errorMessage")
    private String errorMessage;
    @JsonProperty("result")
    @SerializedName("result")
    private String result;
}
