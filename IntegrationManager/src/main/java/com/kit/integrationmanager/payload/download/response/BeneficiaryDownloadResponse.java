package com.kit.integrationmanager.payload.download.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.kit.integrationmanager.model.Beneficiary;
import lombok.Data;

import java.util.List;

@Data
public class BeneficiaryDownloadResponse {

    @JsonProperty("operationResult")
    @SerializedName("operationResult")
    private boolean operationResult;

    @JsonProperty("errorMsg")
    @SerializedName("errorMsg")
    private String errorMsg;

    @JsonProperty("errorCode")
    @SerializedName("errorCode")
    private String errorCode;

    @JsonProperty("beneficiaries")
    @SerializedName("beneficiaries")
    private List<Beneficiary> beneficiaries;

    @JsonProperty("total")
    @SerializedName("total")
    private int total;

    public boolean isOperationResult() {
        return operationResult;
    }

    public void setOperationResult(boolean operationResult) {
        this.operationResult = operationResult;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

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
