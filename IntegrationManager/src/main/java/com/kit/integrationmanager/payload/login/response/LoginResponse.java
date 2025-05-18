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


    public String getExpiredIn() {
        return expiredIn;
    }

    public void setExpiredIn(String expiredIn) {
        this.expiredIn = expiredIn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
