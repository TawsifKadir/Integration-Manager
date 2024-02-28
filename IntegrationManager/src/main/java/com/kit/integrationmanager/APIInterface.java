package com.kit.integrationmanager;


import com.kit.integrationmanager.model.Beneficiary;
import com.kit.integrationmanager.payload.BatchRegistrationRequest;
import com.kit.integrationmanager.payload.BatchRegistrationResponse;
import com.kit.integrationmanager.payload.device.request.RegisterDeviceRequest;
import com.kit.integrationmanager.payload.device.response.RegisterDeviceResponse;
import com.kit.integrationmanager.payload.login.request.LoginRequest;
import com.kit.integrationmanager.payload.login.response.LoginResponse;
import com.kit.integrationmanager.payload.reset.request.ResetPassRequest;
import com.kit.integrationmanager.payload.reset.response.ResetPassResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface APIInterface {
    @POST("/afis/api/beneficiary/register")
    Call<Void> register(@Body Beneficiary beneficiary, @HeaderMap Map<String, String> headers);

    @POST("/afis/api/beneficiary/register/batch")
    Call<BatchRegistrationResponse> registerBatch(@Body BatchRegistrationRequest beneficiaryRequest, @HeaderMap Map<String, String> headers);

    @POST("/afis/api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest, @HeaderMap Map<String, String> headers);

    @POST("/afis/api/user/resetPassword")
    Call<ResetPassResponse> resetPassword(@Body ResetPassRequest resetPassRequest, @HeaderMap Map<String, String> headers);

    @POST("/afis/api/device/register")
    Call<RegisterDeviceResponse> registerDevice(@Body RegisterDeviceRequest registerDeviceRequest, @HeaderMap Map<String, String> headers);
}
