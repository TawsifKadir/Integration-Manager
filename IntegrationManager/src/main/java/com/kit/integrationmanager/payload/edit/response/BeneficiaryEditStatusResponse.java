package com.kit.integrationmanager.payload.edit.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.kit.integrationmanager.payload.ResponseHeader;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeneficiaryEditStatusResponse{
    @JsonProperty("requestId")
    @SerializedName("requestId")
    private String requestId;
    @JsonProperty("applicationId")
    @SerializedName("applicationId")
    private String applicationId;
    @JsonProperty("errorMessage")
    @SerializedName("errorMessage")
    private String errorMessage;
    @JsonProperty("result")
    @SerializedName("result")
    private String result;
}
