package com.kit.integrationmanager.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResponseHeader {
    @SerializedName("errorCode")
    @JsonProperty("errorCode")
    private int errorCode;
    @SerializedName("errorMsg")
    @JsonProperty("errorMsg")
    private String errorMsg;
    @JsonProperty("operationResult")
    @SerializedName("operationResult")
    private boolean operationResult;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isOperationResult() {
        return operationResult;
    }

    public void setOperationResult(boolean operationResult) {
        this.operationResult = operationResult;
    }
}

