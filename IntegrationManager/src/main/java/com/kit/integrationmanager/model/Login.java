package com.kit.integrationmanager.model;

import lombok.Data;

@Data
public class Login {
    private int userId;
    private String userName;
    private String password;
    private String deviceID;
}
