package com.kit.integrationmanager.payload.login.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kit.integrationmanager.payload.ResponseHeader;

import java.util.List;

import lombok.Data;

@Data
public class LoginResponse extends ResponseHeader{

    @JsonProperty("id")
    private int id;
    @JsonProperty("expiredIn")
    private String expiredIn;
    @JsonProperty("status")
    private String status;
    @JsonProperty("token")
    private String token;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("operations")
    private List<String> operations;

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
