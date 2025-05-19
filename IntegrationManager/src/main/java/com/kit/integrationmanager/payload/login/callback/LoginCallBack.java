package com.kit.integrationmanager.payload.login.callback;

import com.kit.integrationmanager.payload.login.response.LoginResponse;

public interface LoginCallBack {
    void onSuccess(LoginResponse response);
    void onError(int errorCode, String errorMessage);
}
