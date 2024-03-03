package com.kit.integrationmanager.payload.login.response;

import com.kit.integrationmanager.payload.ResponseHeader;

import lombok.Data;

@Data
public class LoginResponse extends ResponseHeader{
    private String expiredIn;
    private int id;
    private String status;
    private String token;
    private String userName;

}
