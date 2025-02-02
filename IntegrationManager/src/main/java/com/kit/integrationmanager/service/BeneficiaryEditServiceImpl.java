package com.kit.integrationmanager.service;

import android.os.Build;
import android.os.Looper;
import android.util.Log;

import com.kit.integrationmanager.APIClient;
import com.kit.integrationmanager.APIInterface;
import com.kit.integrationmanager.model.BeneficiaryEdit;
import com.kit.integrationmanager.model.ServerInfo;
import com.kit.integrationmanager.payload.edit.request.BeneficiaryEditRequest;
import com.kit.integrationmanager.payload.edit.response.BeneficiaryEditResponse;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Response;

public class BeneficiaryEditServiceImpl implements BeneficiaryEditService{

    private ServerInfo mServerInfo;
    private Call<BeneficiaryEditResponse> mApiCall;
    private Call<BeneficiaryEditResponse> mApiCallBatch;

    private String TAG = "BeneficiaryEditService";

    public BeneficiaryEditServiceImpl(ServerInfo mServerInfo){
        this.mServerInfo = mServerInfo;
        this.mApiCall=null;
        this.mApiCallBatch = null;
    }

    @Override
    public BeneficiaryEditResponse editBeneficiary(List<BeneficiaryEdit> beneficiaryEditList, HashMap<String, String> headers) throws Exception {

        APIInterface apiInterface;
        Response<BeneficiaryEditResponse> response = null;
        BeneficiaryEditResponse responseRet = null;

        boolean isUiThread = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                ? Looper.getMainLooper().isCurrentThread()
                : Thread.currentThread() == Looper.getMainLooper().getThread();


            if (isUiThread) {
                throw new IllegalStateException("Please call this API from non UI thread.");
            } else if (beneficiaryEditList==null || beneficiaryEditList.size()<=0) {
                throw new IllegalArgumentException("Invalid beneficiary edit list");
            } else {

                apiInterface = APIClient.getInstance().setServerInfo(mServerInfo).getRetrofit().create(APIInterface.class);

                BeneficiaryEditRequest nowRequest = new BeneficiaryEditRequest();
                nowRequest.setBeneficiaryEditRequest(beneficiaryEditList);

                this.mApiCall = apiInterface.beneficiaryEditBatch(nowRequest,headers);

                response = this.mApiCall.execute();

                if (response.isSuccessful()) {
                    responseRet =  response.body();
                } else {
                    throw new Exception(response.errorBody().string());
                }
            }

            return responseRet;
    }

    @Override
    public Observable<BeneficiaryEditResponse> editBeneficiaryAsync(List<BeneficiaryEdit> beneficiaryEditList, HashMap<String, String> headers , int partitionSize) throws Exception {

        Observable<BeneficiaryEditResponse> nowObservable = Observable.create(emitter -> {
            APIInterface apiInterface;
            Response<BeneficiaryEditResponse> response;

            // Check if the current thread is the UI thread
            boolean isUiThread = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    ? Looper.getMainLooper().isCurrentThread()
                    : Thread.currentThread() == Looper.getMainLooper().getThread();

            try {
                if (isUiThread) {
                    emitter.onError(new IllegalStateException("Please call this API from non UI thread."));
                } else if (beneficiaryEditList==null || beneficiaryEditList.size()<=0) {
                    emitter.onError(new IllegalArgumentException("Invalid beneficiary edit list"));
                } else {

                    apiInterface = APIClient.getInstance().setServerInfo(mServerInfo).getRetrofit().create(APIInterface.class);
                    BeneficiaryEditRequest beneficiaryEditRequest = new BeneficiaryEditRequest();

                    List<List<BeneficiaryEdit>> partitions = partitionBeneficiaryEditList(beneficiaryEditList, partitionSize);

                    for (List<BeneficiaryEdit> reqList : partitions) {

                        beneficiaryEditRequest.setBeneficiaryEditRequest(reqList);
                        this.mApiCall = apiInterface.beneficiaryEditBatch(beneficiaryEditRequest,headers);

                        response = this.mApiCall.execute();

                        if (response.isSuccessful()) {
                            BeneficiaryEditResponse nowResponse = response.body();
                            Log.d(TAG,"Beneficiary Edit Successfull");
                            emitter.onNext(response.body());
                        } else {
                            emitter.onError(new Throwable("Error in Beneficiary Edit. Error code ="+response.code()+". Message ="+ response.errorBody().string()));
                        }
                    }
                }
                emitter.onComplete();
            }catch(Throwable t){
                emitter.onError(t);
                emitter.onComplete();
            }
        });

        return nowObservable;
    }

    @Override
    public void cancelBeneficiaryEdit() {
        try{
            if(this.mApiCall!=null && !this.mApiCall.isCanceled()){
                this.mApiCall.cancel();
                this.mApiCall = null;
            }

            if(this.mApiCallBatch!=null && !this.mApiCallBatch.isCanceled()){
                this.mApiCallBatch.cancel();
                this.mApiCallBatch = null;
            }

        }catch(Throwable t){
            Log.e(TAG,"Could not cancel Beneficiary Edit. Error : "+t.getMessage());
        }
    }

    public <T> List<List<T>> partitionBeneficiaryEditList(List<T> list, int size) {
        List<List<T>> partitions = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            partitions.add(list.subList(i, Math.min(i + size, list.size())));
        }
        return partitions;
    }
}
