package com.kit.integrationmanager.payload.update.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kit.integrationmanager.model.Beneficiary;

import java.util.List;

import lombok.Builder;

@Builder
public class UpdateFullBeneficiaryRequest {
    @JsonProperty("beneficiaries")
    private List<BeneficiaryUpdateRequestV2> beneficiaries;


    public List<BeneficiaryUpdateRequestV2> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(List<BeneficiaryUpdateRequestV2> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }
}
