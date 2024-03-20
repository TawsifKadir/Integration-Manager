package com.kit.integrationmanager.service;


import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kit.integrationmanager.APIClient;
import com.kit.integrationmanager.APIInterface;

import com.kit.integrationmanager.model.Device;
import com.kit.integrationmanager.model.ServerInfo;
import com.kit.integrationmanager.model.Beneficiary;
import com.kit.integrationmanager.payload.RegistrationResult;
import com.kit.integrationmanager.payload.RegistrationStatus;
import com.kit.integrationmanager.payload.BatchRegistrationRequest;
import com.kit.integrationmanager.payload.BatchRegistrationResponse;

import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class OnlineIntegrationManager extends Observable implements IntegrationManager{

    private final Observer mObserver;
    private APIInterface apiInterface;

    @Getter
    @Setter
    private ServerInfo mServerInfo;

    private Context mContext;

    private Device mDeviceInfo;

    private String TAG = "OnlineIntegrationManager";
    public OnlineIntegrationManager(Context context,Observer observer,ServerInfo serverInfo){
        mContext = context;
        mServerInfo = serverInfo;
        mDeviceInfo = null;
        mObserver = observer;
    }

    public void syncRecords(List<Beneficiary> beneficiaries,HashMap<String,String > headers) throws Exception{
        BatchRegistrationRequest apiRequest = BatchRegistrationRequest.builder().beneficiaries(beneficiaries).build();
        apiInterface = null;
        try {
            if(!DeviceManager.getInstance(mContext).isOnline()){
                Log.d(TAG,"Error: The device is not connetced.");
                mObserver.update(OnlineIntegrationManager.this,prepareRegistrationResult(
                        RegistrationStatus.FAILED,
                        1,
                        "The device is not connetced.",
                        null
                ));
                return;
            }

            if(beneficiaries==null || beneficiaries.isEmpty()){
                Log.d(TAG,"Error: empty beneficiary list provided.");
                mObserver.update(OnlineIntegrationManager.this,prepareRegistrationResult(
                        RegistrationStatus.FAILED,
                        10,
                        "Empty beneficiary list provided.",
                        null
                ));
            }

            apiInterface = APIClient.getInstance().setServerInfo(mServerInfo).getRetrofit().create(APIInterface.class);
        }catch(Exception exc){
            exc.printStackTrace();
            mObserver.update(OnlineIntegrationManager.this,prepareRegistrationResult(
                    RegistrationStatus.FAILED,
                    1,
                    "Error while creating API Interface. Possibly , Server information is not correct.",
                    null
            ));
            return;
        }

        Call<BatchRegistrationResponse> call = apiInterface.registerBatch(apiRequest, headers);
        call.enqueue(new Callback<BatchRegistrationResponse>() {

            @Override
            public void onResponse(Call<BatchRegistrationResponse> call, Response<BatchRegistrationResponse> response) {
                if(response.code()==200) {
                    try {
                        mObserver.update(OnlineIntegrationManager.this, prepareRegistrationResult(
                                RegistrationStatus.SUCCESS,
                                0,
                                "",
                                response.body().getApplicationIds()
                        ));
                    } catch (Throwable exc) {
                        Log.e(TAG, "Response Error : " + exc.getLocalizedMessage());
                        exc.printStackTrace();
                        mObserver.update(OnlineIntegrationManager.this,prepareRegistrationResult(
                                RegistrationStatus.FAILED,
                                1,
                                exc.getLocalizedMessage(),
                                null
                        ));
                    }
                }else{
                    String msg = "Error Occurred";
                    try{msg=response.errorBody().string();}catch(Exception exc){}
                    mObserver.update(OnlineIntegrationManager.this,prepareRegistrationResult(
                            RegistrationStatus.FAILED,
                            response.code(),
                            msg,
                            null
                    ));
                }
            }

            @Override
            public void onFailure(Call<BatchRegistrationResponse> call, Throwable throwable) {
                RegistrationResult syncResult = new RegistrationResult(RegistrationStatus.FAILED,null);
                mObserver.update(OnlineIntegrationManager.this,prepareRegistrationResult(
                        RegistrationStatus.FAILED,
                        1,
                        throwable.getMessage(),
                        null
                ));
            }
        });
    }

    public void syncRecord(Beneficiary beneficiary, HashMap<String,String> headers) throws Exception{

        Beneficiary mBeneficiary = beneficiary;
        apiInterface = null;
        try {
            if(!DeviceManager.getInstance(mContext).isOnline()){
                Log.d(TAG,"Error: The device is not connetced.");
                mObserver.update(OnlineIntegrationManager.this,prepareRegistrationResult(
                        RegistrationStatus.FAILED,
                        1,
                        "The device is not connetced.",
                        null
                ));
                return;
            }
            if(beneficiary==null){
                Log.d(TAG,"Error: The device is not connetced.");
                mObserver.update(OnlineIntegrationManager.this,prepareRegistrationResult(
                        RegistrationStatus.FAILED,
                        11,
                        "Beneficiary record null.",
                        null
                ));
            }
            apiInterface = APIClient.getInstance().setServerInfo(mServerInfo).getRetrofit().create(APIInterface.class);
        }catch(Exception exc){
            Log.e(TAG,"Error while creating API Interface. Possibly , Server information is not correct.");
            exc.printStackTrace();
            mObserver.update(OnlineIntegrationManager.this,prepareRegistrationResult(
                    RegistrationStatus.FAILED,
                    1,
                    "Error while creating API Interface. Possibly , Server information is not correct.",
                    null
                ));
            return;
        }
        Call<Void> call = apiInterface.register(mBeneficiary,headers);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code()==200){
                    try{
                        List bID = new ArrayList<String>();
                        bID.add(mBeneficiary.getApplicationId());
                        mObserver.update(OnlineIntegrationManager.this,prepareRegistrationResult(
                                RegistrationStatus.SUCCESS,
                                0,
                                "",
                                bID
                        ));
                    }catch(Exception exc){
                        Log.e(TAG,"Response Error : "+ exc.getLocalizedMessage());
                        exc.printStackTrace();
                        mObserver.update(OnlineIntegrationManager.this,prepareRegistrationResult(
                                RegistrationStatus.FAILED,
                                1,
                                exc.getLocalizedMessage(),
                                null
                        ));
                    }
                }else{
                    String msg = "Error Occurred";
                    try{msg=response.errorBody().string();}catch(Exception exc){}
                    mObserver.update(OnlineIntegrationManager.this,prepareRegistrationResult(
                            RegistrationStatus.FAILED,
                            response.code(),
                            msg,
                            null
                    ));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {

                mObserver.update(OnlineIntegrationManager.this,prepareRegistrationResult(
                        RegistrationStatus.FAILED,
                        1,
                        throwable.getMessage(),
                        null
                ));
            }
        });
    }
    RegistrationResult prepareRegistrationResult(RegistrationStatus registrationStatus,int errorCode, String errorMsg,List<String> result){
        registrationStatus.setErrorCode(errorCode);
        registrationStatus.setErrorMsg(errorMsg);
        RegistrationResult syncResult = new RegistrationResult(registrationStatus,result);
        return syncResult;
    }
    @Override
    protected void finalize() throws Throwable {

        super.finalize();
    }
}
