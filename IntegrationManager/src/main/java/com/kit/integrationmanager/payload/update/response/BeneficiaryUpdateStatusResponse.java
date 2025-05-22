package com.kit.integrationmanager.payload.update.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.kit.integrationmanager.payload.OperationStatus;

import lombok.Builder;

@Builder
public class BeneficiaryUpdateStatusResponse {
    @JsonProperty("applicationId")
    private String applicationId;
    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("errorMessage")
    private String errorMessage;
    @JsonProperty("result")
    private OperationStatus result;

    public BeneficiaryUpdateStatusResponse(String applicationId, String requestId, String errorMessage, OperationStatus result) {
        this.applicationId = applicationId;
        this.requestId = requestId;
        this.errorMessage = errorMessage;
        this.result = result;
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public OperationStatus getResult() {
        return result;
    }

    public void setResult(OperationStatus result) {
        this.result = result;
    }
}
