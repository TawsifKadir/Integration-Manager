package com.kit.integrationmanager.service;

import android.content.Context;
import android.util.Log;

import com.kit.integrationmanager.APIClient;
import com.kit.integrationmanager.APIInterface;
import com.kit.integrationmanager.model.ServerInfo;
import com.kit.integrationmanager.payload.ResponseHeader;
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
    public LoginServiceImpl(Context context,Observer observer,ServerInfo serverInfo) {
        this.mContext = mContext;
        this.mObserver = observer;
        this.addObserver(observer);
        this.mServerInfo = serverInfo;
    }

    @Override
    public void doOnlineLogin(LoginRequest loginRequest, HashMap<String, String> headers) {

        APIInterface apiInterface = null;
        try {
            apiInterface = APIClient.getInstance().setServerInfo(mServerInfo).getRetrofit().create(APIInterface.class);
        }catch(Exception exc){
            Log.e(TAG,"Error while creating API Interface. Possibly , Server information is not correct.");
            exc.printStackTrace();

            mObserver.update(LoginServiceImpl.this,prepareLoginResponse(1,
                    "Error while creating API Interface. Possibly , Server information is not correct.",
                    false));

            return;
        }
        Call<LoginResponse> call = apiInterface.login(loginRequest,headers);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.code()==200){
                    try{
                        mObserver.update(LoginServiceImpl.this,response.body());
                    }catch(Exception exc){
                        Log.e(TAG,"Response Error : "+ exc.getLocalizedMessage());
                        exc.printStackTrace();
                        mObserver.update(LoginServiceImpl.this,
                                prepareLoginResponse(2,
                                         exc.getLocalizedMessage(),
                                        false)
                        );
                    }
                }else{
                    mObserver.update(LoginServiceImpl.this,
                            prepareLoginResponse(response.code(),
                                    response.message(),
                                    false)
                    );
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                mObserver.update(LoginServiceImpl.this,
                        prepareLoginResponse(4,
                                 throwable.getLocalizedMessage(),
                                false)
                );
            }
        });
    }

    @Override
    public void doResetPassword(ResetPassRequest resetPasswordRequest, HashMap<String, String> headers) {
        APIInterface apiInterface = null;
        try {
            apiInterface = APIClient.getInstance().setServerInfo(mServerInfo).getRetrofit().create(APIInterface.class);
        }catch(Exception exc){
            Log.e(TAG,"Error while creating API Interface. Possibly , Server information is not correct.");
            exc.printStackTrace();

            mObserver.update(LoginServiceImpl.this,preparResetPassResponse(1,
                    "Error while creating API Interface. Possibly , Server information is not correct.",
                    false));

            return;
        }
        Call<ResetPassResponse> call = apiInterface.resetPassword(resetPasswordRequest,headers);
        call.enqueue(new Callback<ResetPassResponse>() {
            @Override
            public void onResponse(Call<ResetPassResponse> call, Response<ResetPassResponse> response) {
                if(response.code()==200){
                    try{
                        mObserver.update(LoginServiceImpl.this,response.body());
                    }catch(Exception exc){
                        Log.e(TAG,"Response Error : "+ exc.getLocalizedMessage());
                        exc.printStackTrace();
                        mObserver.update(LoginServiceImpl.this,
                                preparResetPassResponse(2,
                                        exc.getLocalizedMessage(),
                                        false)
                        );
                    }
                }else{
                    mObserver.update(LoginServiceImpl.this,
                            preparResetPassResponse(response.code(),
                                    response.message(),
                                    false)
                    );
                }
            }

            @Override
            public void onFailure(Call<ResetPassResponse> call, Throwable throwable) {
                mObserver.update(LoginServiceImpl.this,
                        preparResetPassResponse(4,
                                 throwable.getLocalizedMessage(),
                                false)
                );
            }
        });
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
}
