package com.kit.integrationmanager.service;

import android.os.Build;
import android.os.Looper;
import android.util.Log;


import androidx.annotation.NonNull;

import com.kit.integrationmanager.APIClient;
import com.kit.integrationmanager.APIInterface;
import com.kit.integrationmanager.model.BeneficiaryEdit;
import com.kit.integrationmanager.model.Pair;
import com.kit.integrationmanager.model.ServerInfo;
import com.kit.integrationmanager.payload.edit.request.BeneficiaryEditRequest;
import com.kit.integrationmanager.payload.edit.request.BeneficiaryEditStatusEntry;
import com.kit.integrationmanager.payload.edit.request.BeneficiaryEditStatusRequest;
import com.kit.integrationmanager.payload.edit.response.BeneficiaryEditResponse;
import com.kit.integrationmanager.payload.edit.response.BeneficiaryEditStatusResponse;


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

    private Call<List<BeneficiaryEditStatusResponse>> mApiStatusCall;

    private String TAG = "BeneficiaryEditService";

    public BeneficiaryEditServiceImpl(ServerInfo mServerInfo){
        this.mServerInfo = mServerInfo;
        this.mApiCall=null;
        this.mApiCallBatch = null;
        this.mApiStatusCall = null;
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

                    List<List<BeneficiaryEdit>> partitions = partitionList(beneficiaryEditList, partitionSize);

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
    public Observable<List<BeneficiaryEditStatusResponse>> getBeneficiaryEditStatus(List<Pair> beneficiaryList, HashMap<String, String> headers, int partitionSize) throws Exception {
        Observable<List<BeneficiaryEditStatusResponse>> nowObservable = Observable.create(emitter -> {
            APIInterface apiInterface;
            Response<List<BeneficiaryEditStatusResponse>> response;

            // Check if the current thread is the UI thread
            boolean isUiThread = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    ? Looper.getMainLooper().isCurrentThread()
                    : Thread.currentThread() == Looper.getMainLooper().getThread();

            try {
                if (isUiThread) {
                    emitter.onError(new IllegalStateException("Please call this API from non UI thread."));
                } else if (beneficiaryList==null || beneficiaryList.size()<=0) {
                    emitter.onError(new IllegalArgumentException("Invalid beneficiary edit list"));
                } else {

                    apiInterface = APIClient.getInstance().setServerInfo(mServerInfo).getRetrofit().create(APIInterface.class);

                    List<List<Pair>> partitions = partitionList(beneficiaryList, partitionSize);

                    for (List<Pair> reqList : partitions) {

                        List<BeneficiaryEditStatusEntry> beneficiaryEditStatusEntryList = getBeneficiaryEditStatusRequest(reqList);

                        BeneficiaryEditStatusRequest beneficiaryEditStatusRequest = new BeneficiaryEditStatusRequest();
                        beneficiaryEditStatusRequest.setRequests(beneficiaryEditStatusEntryList);

                        this.mApiStatusCall = apiInterface.getBeneficiaryEditStatus(beneficiaryEditStatusRequest,headers);

                        response = this.mApiStatusCall.execute();

                        if (response.isSuccessful()) {
                            List<BeneficiaryEditStatusResponse> nowResponse = response.body();
                            Log.d(TAG,"Get Beneficiary Edit Status Successfull");
                            emitter.onNext(nowResponse);
                        } else {
                            emitter.onError(new Throwable("Error in Get Beneficiary Edit Status. Error code ="+response.code()+". Message ="+ response.errorBody().string()));
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

    @NonNull
    private List<BeneficiaryEditStatusEntry> getBeneficiaryEditStatusRequest(List<Pair> reqList) {

        List<BeneficiaryEditStatusEntry> beneficiaryEditStatusEntryList = new ArrayList<>();

        for(Pair nowPair: reqList){
            BeneficiaryEditStatusEntry beneficiaryEditStatusEntry = new BeneficiaryEditStatusEntry();
            beneficiaryEditStatusEntry.setRequestId((String)nowPair.getFirst());
            beneficiaryEditStatusEntry.setApplicationId((String)nowPair.getSecond());
            beneficiaryEditStatusEntryList.add(beneficiaryEditStatusEntry);
        }

        return beneficiaryEditStatusEntryList;
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

    public void cancelGetBeneficiaryEditStatus() {
        try{
            if(this.mApiStatusCall!=null && !this.mApiStatusCall.isCanceled()){
                this.mApiStatusCall.cancel();
                this.mApiStatusCall = null;
            }

        }catch(Throwable t){
            Log.e(TAG,"Could not cancel Beneficiary Edit. Error : "+t.getMessage());
        }
    }

    public <T> List<List<T>> partitionList(List<T> list, int size) {
        List<List<T>> partitions = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            partitions.add(list.subList(i, Math.min(i + size, list.size())));
        }
        return partitions;
    }


}
