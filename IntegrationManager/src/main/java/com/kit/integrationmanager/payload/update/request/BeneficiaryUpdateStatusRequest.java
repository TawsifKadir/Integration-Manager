package com.kit.integrationmanager.payload.update.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kit.integrationmanager.payload.update.BeneficiaryUpdateRequestBody;

import java.util.List;

import lombok.Builder;

@Builder
public class BeneficiaryUpdateStatusRequest {
    @JsonProperty("requests")
    private List<BeneficiaryUpdateRequestBody> requests;


    public BeneficiaryUpdateStatusRequest(List<BeneficiaryUpdateRequestBody> requestBodies){
        this.requests = requestBodies;
    }

    public List<BeneficiaryUpdateRequestBody> getRequests() {
        return requests;
    }

    public void setRequests(List<BeneficiaryUpdateRequestBody> requests) {
        this.requests = requests;
    }
}
