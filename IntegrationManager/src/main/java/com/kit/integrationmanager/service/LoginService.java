package com.kit.integrationmanager.service;

import com.kit.integrationmanager.model.Login;
import com.kit.integrationmanager.payload.login.request.LoginRequest;
import com.kit.integrationmanager.payload.reset.request.ResetPassRequest;

import java.util.HashMap;


public interface LoginService {
    public void doOnlineLogin(LoginRequest login, HashMap<String,String> headers);
    public void doResetPassword(ResetPassRequest resetPassRequest, HashMap<String,String> headers);
}
