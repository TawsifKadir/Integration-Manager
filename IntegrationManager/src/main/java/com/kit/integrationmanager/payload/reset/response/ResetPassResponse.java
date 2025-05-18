package com.kit.integrationmanager.payload.reset.response;

import com.kit.integrationmanager.payload.ResponseHeader;

import lombok.Data;

@Data
public class ResetPassResponse extends ResponseHeader{
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
