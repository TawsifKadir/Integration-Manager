package com.kit.integrationmanager.test;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kit.integrationmanager.R;
import com.kit.integrationmanager.event.DownloadProgressEvent;
import com.kit.integrationmanager.model.BeneficiaryEdit;
import com.kit.integrationmanager.model.ServerInfo;
import com.kit.integrationmanager.payload.edit.request.BeneficiaryEditRequest;
import com.kit.integrationmanager.payload.edit.response.BeneficiaryEditResponse;
import com.kit.integrationmanager.payload.edit.response.BeneficiaryEditResponseEntry;
import com.kit.integrationmanager.payload.reconcile.request.PayrollReconcileBatchRequest;
import com.kit.integrationmanager.payload.reconcile.response.PayrollReconcileBatchResponse;
import com.kit.integrationmanager.service.BeneficiaryEditService;
import com.kit.integrationmanager.service.BeneficiaryEditServiceImpl;
import com.kit.integrationmanager.service.PayrollReconService;
import com.kit.integrationmanager.service.PayrollReconServiceImpl;

import org.reactivestreams.Subscriber;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UpdateBeneficiary implements View.OnClickListener {
    private static final String TAG = "Beneficiary Update";

    private Context mContext;

    private ServerInfo mServerInfo;
    private boolean isAsync;

    private HashMap<String, String> mHeaders;

    private CompositeDisposable mDisposables;

    public UpdateBeneficiary(Context context , ServerInfo serverInfo , HashMap<String, String> headers, boolean async){
        this.mContext = context;
        this.mServerInfo = serverInfo;
        this.mHeaders = headers;
        this.isAsync = async;
        mDisposables = new CompositeDisposable();
    }

    @Override
    public void onClick(View v) {

        List<BeneficiaryEdit> beneficiaryUpdateList = readBeneficiaryUpdateJson();
        if(beneficiaryUpdateList==null){
            Log.d(TAG,"Found null beneficiary update list. Fix it and try again.");
        }else if(beneficiaryUpdateList.isEmpty()){
            Log.d(TAG,"Empty beneficiary update list. Is the sample file correct? Please check and try again.");
        }else {

            Observer<BeneficiaryEditResponse> nowObserver = new Observer<BeneficiaryEditResponse>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mDisposables.add(d);
                }

                @Override
                public void onNext(@NonNull BeneficiaryEditResponse beneficiaryEditResponse) {
                    Log.d(TAG,"Received response from server");
                    Log.d(TAG,"Success Count : "+beneficiaryEditResponse.getSuccessCount());

                    for(BeneficiaryEditResponseEntry beneficiaryEditResponseEntry:beneficiaryEditResponse.getBeneficiaryEditResponseList()){
                            Log.d(TAG,"Result = "+beneficiaryEditResponseEntry.getResult());
                            Log.d(TAG,"Error Message = "+beneficiaryEditResponseEntry.getErrorMessage());
                            Log.d(TAG,"Request ID = "+beneficiaryEditResponseEntry.getRequestId());
                            Log.d(TAG,"Application ID = "+beneficiaryEditResponseEntry.getApplicationId());
                            Log.d(TAG,"-------------------------------");

                    }
                    Log.d(TAG,beneficiaryEditResponse.getBeneficiaryEditResponseList().toString());
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.e(TAG,"Error occurred while sending data to server");
                    Log.e(TAG,e.getMessage());
                    e.printStackTrace();
                }

                @Override
                public void onComplete() {
                    Log.d(TAG,"Task Completed");
                }
            };

            BeneficiaryEditService beneficiaryEditService = new BeneficiaryEditServiceImpl(mServerInfo);

            if(isAsync){
                try {
                    Observable<BeneficiaryEditResponse> beneficiaryEditResponse = beneficiaryEditService.editBeneficiaryAsync(beneficiaryUpdateList, mHeaders, 5);
                    beneficiaryEditResponse = beneficiaryEditResponse.
                            subscribeOn(Schedulers.io()).
                            observeOn(AndroidSchedulers.mainThread());
                    beneficiaryEditResponse.subscribe(nowObserver);
                }catch(Throwable t){
                    Log.e(TAG,"Error occured while calling editBeneficiaryAsync. "+t.getMessage());
                    t.printStackTrace();
                }
            }else {
                Observable<BeneficiaryEditResponse> nowObservable = Observable.create(new ObservableOnSubscribe<BeneficiaryEditResponse>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<BeneficiaryEditResponse> emitter) throws Throwable {

                        BeneficiaryEditResponse beneficiaryEditResponse = null;

                        try {
                            beneficiaryEditResponse = beneficiaryEditService.editBeneficiary(beneficiaryUpdateList, mHeaders);
                            emitter.onNext(beneficiaryEditResponse);
                        } catch (Throwable t) {
                            emitter.onError(t);
                        }
                        emitter.onComplete();
                        Log.d(TAG, "Update Beneficiary Processor Leaving....");
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

                nowObservable.subscribe(nowObserver);
            }
        }
    }

    private List<BeneficiaryEdit> readBeneficiaryUpdateJson() {
        BeneficiaryEditRequest beneficiaryEditRequest = null;
        try {

            InputStream inputStream = mContext.getResources().openRawResource(R.raw.beneficiary_update_req);
            ObjectMapper objectMapper = new ObjectMapper();
            beneficiaryEditRequest = objectMapper.readValue(inputStream, BeneficiaryEditRequest.class);

        } catch (Throwable t) {
            Log.e(TAG,"Beneficiary Data Read Error");
            Log.e(TAG,t.getMessage());
            t.printStackTrace();
        }

        if(beneficiaryEditRequest!=null) return beneficiaryEditRequest.getBeneficiaryEditRequest();
        return null;
    }
}