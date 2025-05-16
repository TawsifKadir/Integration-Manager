package com.kit.integrationmanager.payload.edit.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeneficiaryEditStatusEntry {
    @JsonProperty("applicationId")
    @SerializedName("applicationId")
    private String applicationId;
    @JsonProperty("requestId")
    @SerializedName("requestId")
    private String requestId;
}
