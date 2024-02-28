package com.kit.integrationmanager.service;

import android.content.Context;
import android.util.Log;

import com.kit.integrationmanager.APIClient;
import com.kit.integrationmanager.APIInterface;

import com.kit.integrationmanager.model.ServerInfo;

import com.kit.integrationmanager.payload.device.request.RegisterDeviceRequest;
import com.kit.integrationmanager.payload.device.response.RegisterDeviceResponse;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;


import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceRegistrationServiceImpl extends Observable implements DeviceRegistrationService{
    private  Observer mObserver;
    private APIInterface apiInterface;

    @Getter
    @Setter
    private ServerInfo mServerInfo;

    private String TAG = "DeviceRegistrationServiceImpl";
    private Context mContext;

    public DeviceRegistrationServiceImpl(Context mContext, Observer mObserver, ServerInfo mServerInfo) {
        this.mObserver = mObserver;
        this.mServerInfo = mServerInfo;
        this.mContext = mContext;
    }

    @Override
    public void registerDevice(RegisterDeviceRequest registerDeviceRequest,HashMap<String,String > headers) {

        apiInterface = null;
        try {
            if(!DeviceManager.getInstance(mContext).isOnline()){
                Log.d(TAG,"Error: The device is not connetced.");

                mObserver.update(DeviceRegistrationServiceImpl.this,prepareRegisterDeviceResponse(
                        1,
                        "The device is not connetced.",
                        false
                ));
                return;
            }
            apiInterface = APIClient.getInstance().setServerInfo(mServerInfo).getRetrofit().create(APIInterface.class);
        }catch(Exception exc){
            exc.printStackTrace();
            mObserver.update(DeviceRegistrationServiceImpl.this,prepareRegisterDeviceResponse(
                    1,
                    "Error while creating API Interface. Possibly , Server information is not correct.",
                    false
            ));
            return;
        }

        Call<RegisterDeviceResponse> call = apiInterface.registerDevice(registerDeviceRequest, headers);
        call.enqueue(new Callback<RegisterDeviceResponse>() {

            @Override
            public void onResponse(Call<RegisterDeviceResponse> call, Response<RegisterDeviceResponse> response) {
                if(response.code()==200) {
                    try {
                        mObserver.update(DeviceRegistrationServiceImpl.this,response.body());
                    } catch (Exception exc) {
                        Log.e(TAG, "Response Error : " + exc.getLocalizedMessage());
                        exc.printStackTrace();
                        mObserver.update(DeviceRegistrationServiceImpl.this,response.body());
                    }
                }else{
                    String msg = "Error Occurred";
                    try{msg=response.errorBody().string();}catch(Exception exc){}
                    mObserver.update(DeviceRegistrationServiceImpl.this,prepareRegisterDeviceResponse(
                            response.code(),
                            msg,
                            false
                    ));
                }
            }

            @Override
            public void onFailure(Call<RegisterDeviceResponse> call, Throwable throwable) {
                mObserver.update(DeviceRegistrationServiceImpl.this,prepareRegisterDeviceResponse(
                        1,
                        throwable.getMessage(),
                        false
                ));
            }
        });
    }

    private RegisterDeviceResponse prepareRegisterDeviceResponse(int errorCode,String errorMsg,boolean operationResult){
        RegisterDeviceResponse registerDeviceResponse = new RegisterDeviceResponse();
        registerDeviceResponse.setOperationResult(operationResult);
        registerDeviceResponse.setErrorCode(errorCode);
        registerDeviceResponse.setErrorMsg(errorMsg);
        return registerDeviceResponse;

    }
}
