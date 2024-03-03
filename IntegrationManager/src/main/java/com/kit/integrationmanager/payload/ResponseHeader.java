package com.kit.integrationmanager.payload;

import lombok.Data;

@Data
public class ResponseHeader {
    private int errorCode;
    private String errorMsg;
    private boolean operationResult;
}
