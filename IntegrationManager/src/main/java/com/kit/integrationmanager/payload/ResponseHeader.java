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
}

