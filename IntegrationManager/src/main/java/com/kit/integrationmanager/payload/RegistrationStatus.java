package com.kit.integrationmanager.payload;

public enum RegistrationStatus {
    SUCCESS(1,0,""),
    FAILED(2,0,"");

    private int id;
    private int errorCode;
    private String errorMsg;

    private RegistrationStatus(int id,int errorCode,String errorMsg){
        this.id = id;
        this.errorCode = 0;
        this.errorMsg=null;
    }

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
}
