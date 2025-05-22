package com.kit.integrationmanager.payload.update;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public class BeneficiaryUpdateRequestBody {

    @JsonProperty("applicationId")
    private String applicationId;
    @JsonProperty("requestId")
    private String requestId;

    public BeneficiaryUpdateRequestBody(String applicationId, String requestId) {
        this.applicationId = applicationId;
        this.requestId = requestId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
