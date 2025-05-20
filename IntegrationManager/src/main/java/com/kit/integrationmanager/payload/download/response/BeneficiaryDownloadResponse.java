package com.kit.integrationmanager.payload.download.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.kit.integrationmanager.model.Beneficiary;
import com.kit.integrationmanager.payload.ResponseHeader;

import java.util.List;

public class BeneficiaryDownloadResponse extends ResponseHeader {

    @JsonProperty("beneficiaries")
    @SerializedName("beneficiaries")
    private List<Beneficiary> beneficiaries;

    @JsonProperty("total")
    @SerializedName("total")
    private int total;

    public List<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(List<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
