package com.kit.integrationmanager.service;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;

import com.kit.integrationmanager.APIClient;
import com.kit.integrationmanager.APIInterface;
import com.kit.integrationmanager.model.ServerInfo;
import com.kit.integrationmanager.payload.login.callback.LoginCallBack;
import com.kit.integrationmanager.payload.login.request.LoginRequest;
import com.kit.integrationmanager.payload.login.response.LoginResponse;
import com.kit.integrationmanager.payload.reset.request.ResetPassRequest;
import com.kit.integrationmanager.payload.reset.response.ResetPassResponse;


import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginServiceImpl extends Observable implements LoginService{

    private Context mContext = null;
    private Observer mObserver;
    private ServerInfo mServerInfo;
    private String TAG = "LoginService";

    public LoginServiceImpl(Context context){
        this.mContext = context;
        this.mObserver = null;
        this.mServerInfo = null;
    }

    public LoginServiceImpl(Context context,ServerInfo serverInfo) {
        this.mContext = mContext;
        this.mObserver = null;
        this.mServerInfo = serverInfo;
    }

    public LoginServiceImpl(Context context,Observer observer,ServerInfo serverInfo) {
        this.mContext = mContext;
        this.mObserver = observer;
        this.addObserver(observer);
        this.mServerInfo = serverInfo;
    }

    @Override
    public void doOnlineLogin(String username, String password, HashMap<String, String> headers,
                              LoginCallBack callback) {
        if (!isValidLoginRequest(username, password)) {
            callback.onError(400, "Invalid login request");
            return;
        }
        try{
            APIInterface apiInterface = APIClient.getInstance()
                    .setServerInfo(mServerInfo)
                    .getRetrofit()
                    .create(APIInterface.class);

            LoginRequest request = LoginRequest.builder()
                    .userName(username)
                    .password(password)
                    .build();

            apiInterface.login(request, headers).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        LoginResponse loginResponse = response.body();
                        if (loginResponse != null && loginResponse.getErrorCode() == 0) {
                            callback.onSuccess(loginResponse);
                        } else {
                            String errorMsg = loginResponse != null ?
                                    loginResponse.getErrorMsg() : "Empty response body";
                            int errorCode = loginResponse != null ?
                                    loginResponse.getErrorCode() : -1;
                            callback.onError(errorCode, errorMsg);
                        }
                    } else {
                        try {
                            callback.onError(response.code(),
                                    response.errorBody() != null ?
                                            response.errorBody().string() : "Login failed");
                        } catch (Exception e) {
                            callback.onError(-1, "Error parsing response");
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    callback.onError(-1, t.getMessage());
                }
            });
        }catch (Exception e){
            callback.onError(-1, e.getMessage());
        }
    }

    @Override
    public synchronized ResetPassResponse doResetPassword(ResetPassRequest resetPasswordRequest, HashMap<String, String> headers) {

        APIInterface apiInterface = null;
        boolean isError = false;
        Throwable errorObject = null;
        Response<ResetPassResponse> response = null;
        ResetPassResponse rpResponse = null;
        boolean isUiThread = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                ? Looper.getMainLooper().isCurrentThread()
                : Thread.currentThread() == Looper.getMainLooper().getThread();

        try {
            if(isUiThread){
                rpResponse = preparResetPassResponse(9,"Please call this API from non UI thread.",false);
            }else if(!isValidResetPassRequest(resetPasswordRequest)){
                rpResponse = preparResetPassResponse(9,"Invalid reset password request",false);
            }else {
                apiInterface = APIClient.getInstance().setServerInfo(mServerInfo).getRetrofit().create(APIInterface.class);
                Call<ResetPassResponse> call = apiInterface.resetPassword(resetPasswordRequest, headers);
                response = call.execute();
                if (response.code() == 200) {
                    rpResponse = response.body();
                } else {
                    rpResponse = preparResetPassResponse(response.code(), response.errorBody().string(), false);
                }
            }
        }catch(Throwable t){
            isError=true;
            errorObject=t;
            rpResponse = preparResetPassResponse(9,errorObject.getMessage(),false);
        }finally {
            if(isError){
                Log.e(TAG,"Error occured in login call : "+errorObject.getMessage());
                errorObject.printStackTrace();
            }
            errorObject=null;
            response = null;
        }

        return rpResponse;
    }

    public Observer getObserver() {
        return mObserver;
    }

    public void setObserver(Observer mObserver) {
        this.mObserver = mObserver;
        this.addObserver(mObserver);
    }

    public ServerInfo getServerInfo() {
        return mServerInfo;
    }

    public void setServerInfo(ServerInfo serverInfo) {
        this.mServerInfo = serverInfo;
    }

    private LoginResponse prepareLoginResponse(int errorCode, String errorMsg, boolean operationResult){
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setErrorCode(errorCode);
        loginResponse.setErrorMsg(errorMsg);
        loginResponse.setOperationResult(operationResult);
        return loginResponse;
    }
    private ResetPassResponse preparResetPassResponse(int errorCode, String errorMsg, boolean operationResult){
        ResetPassResponse resetPassResponse = new ResetPassResponse();

        resetPassResponse.setErrorCode(errorCode);
        resetPassResponse.setErrorMsg(errorMsg);
        resetPassResponse.setOperationResult(operationResult);

        return resetPassResponse;
    }

    private boolean isValidLoginRequest(String username, String password){
        if(username == null || password == null) return false;
        if(username==null || username.isEmpty()) return false;
        if(password==null || password.isEmpty()) return false;
        return true;
    }

    private boolean isValidResetPassRequest(ResetPassRequest resetPassRequest){
        if(resetPassRequest==null) return false;
        if(resetPassRequest.getNewPassword()==null || resetPassRequest.getNewPassword().isEmpty()) return false;
        return true;
    }
}
