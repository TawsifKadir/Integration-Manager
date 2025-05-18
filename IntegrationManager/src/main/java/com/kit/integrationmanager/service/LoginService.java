package com.kit.integrationmanager.service;

import com.kit.integrationmanager.model.Login;
import com.kit.integrationmanager.payload.login.request.LoginRequest;
import com.kit.integrationmanager.payload.login.response.LoginResponse;
import com.kit.integrationmanager.payload.reset.request.ResetPassRequest;
import com.kit.integrationmanager.payload.reset.response.ResetPassResponse;

import java.util.HashMap;


public interface LoginService {
    LoginResponse doOnlineLogin(String username, String password, HashMap<String, String> headers);
    ResetPassResponse doResetPassword(ResetPassRequest resetPassRequest, HashMap<String, String> headers);
}
