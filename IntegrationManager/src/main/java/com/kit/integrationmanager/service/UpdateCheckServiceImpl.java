package com.kit.integrationmanager.service;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kit.integrationmanager.APIClient;
import com.kit.integrationmanager.APIInterface;
import com.kit.integrationmanager.model.ServerInfo;
import com.kit.integrationmanager.model.VersionInfo;
import com.kit.integrationmanager.payload.reset.response.ResetPassResponse;
import com.kit.integrationmanager.payload.update.request.CheckUpdateRequest;
import com.kit.integrationmanager.payload.update.response.CheckUpdateResponse;

import java.util.HashMap;
import java.util.Observer;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Response;

public class UpdateCheckServiceImpl implements UpdateCheckService{

    private Context mContext = null;
    private Observer mObserver;
    private ServerInfo mServerInfo;
    private String TAG = "UpdateCheckService";

    public UpdateCheckServiceImpl(Context context, ServerInfo serverInfo) {
        this.mContext = mContext;
        this.mServerInfo = serverInfo;
    }
    @Override
    public VersionInfo getUpdateInformation(String currentVersion) throws Exception{

        APIInterface apiInterface = null;
        boolean isError = false;
        Throwable errorObject = null;
        Response<CheckUpdateResponse> response = null;
        CheckUpdateResponse checkUpdateResponse = null;
        VersionInfo versionInfo = new VersionInfo();


        boolean isUiThread = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                ? Looper.getMainLooper().isCurrentThread()
                : Thread.currentThread() == Looper.getMainLooper().getThread();

        try {

            if(isUiThread){
                throw new Exception("Please call this api from non UI thread");
            }else if(currentVersion==null|| currentVersion.isEmpty()){
                throw new Exception("Null or empty version code provided");
            }else {

                StringTokenizer tokenizer = new StringTokenizer(currentVersion, ".");

                if(tokenizer.countTokens()!=3){
                    throw new Exception("Invalid version code provided. Version must be of the form <major>.<minor.<patch>");
                }
                long major = Integer.parseInt(tokenizer.nextToken());
                long minor = Integer.parseInt(tokenizer.nextToken());
                long patch = Integer.parseInt(tokenizer.nextToken());

                apiInterface = APIClient.getInstance().setServerInfo(mServerInfo).getRetrofit().create(APIInterface.class);

                Call<CheckUpdateResponse> call = apiInterface.checkUpdate(major,minor,patch);
                response = call.execute();

                if (response.code() == 200) {
                    checkUpdateResponse = response.body();
                    if(checkUpdateResponse.isOperationResult()) {
                        if(checkUpdateResponse.isUpdateAvailable()) {
                            StringBuilder versionBuilder = new StringBuilder();
                            versionBuilder.
                                    append(checkUpdateResponse.getMajor()).
                                    append(".").
                                    append(checkUpdateResponse.getMinor()).
                                    append(".").
                                    append(checkUpdateResponse.getPatch());
                            versionInfo.setAppVersion(versionBuilder.toString());
                        }else {
                            versionInfo.setAppVersion(currentVersion);
                        }
                        versionInfo.setForceUpdate(checkUpdateResponse.isForcedUpdate());
                        versionInfo.setUpdateAvailable(checkUpdateResponse.isUpdateAvailable());
                        versionInfo.setApkUrl(checkUpdateResponse.getApkUrl());

                    }else{
                        versionInfo = null;
                        throw new Exception("Error response received from server. Detail - "+checkUpdateResponse.getErrorMsg());
                    }
                } else {
                    versionInfo = null;
                    throw new Exception("Error occurred while calling API. Detail - "+response.errorBody().string());
                }
            }
        }catch(Throwable t){
            isError=true;
            errorObject=t;
            versionInfo = null;
            throw new Exception(t.getMessage());
        }finally {
            if(isError){
                Log.e(TAG,"Error occured in CheckUpdate call : "+errorObject.getMessage());
                errorObject.printStackTrace();
                isError=false;
            }
            errorObject=null;
            response = null;
            checkUpdateResponse=null;
        }

        return versionInfo;
    }
}
