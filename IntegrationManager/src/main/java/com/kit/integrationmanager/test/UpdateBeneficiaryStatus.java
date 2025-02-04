package com.kit.integrationmanager.test;

import android.content.Context;
import android.util.Log;

import android.view.View;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kit.integrationmanager.R;
import com.kit.integrationmanager.model.BeneficiaryEdit;
import com.kit.integrationmanager.model.Pair;
import com.kit.integrationmanager.model.ServerInfo;
import com.kit.integrationmanager.payload.edit.request.BeneficiaryEditRequest;
import com.kit.integrationmanager.payload.edit.response.BeneficiaryEditResponse;
import com.kit.integrationmanager.payload.edit.response.BeneficiaryEditStatusResponse;
import com.kit.integrationmanager.service.BeneficiaryEditService;
import com.kit.integrationmanager.service.BeneficiaryEditServiceImpl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UpdateBeneficiaryStatus implements View.OnClickListener{
    private static final String TAG = "Beneficiary Update";

    private Context mContext;

    private ServerInfo mServerInfo;


    private HashMap<String, String> mHeaders;

    private CompositeDisposable mDisposables;

    public UpdateBeneficiaryStatus(Context context , ServerInfo serverInfo , HashMap<String, String> headers){
        this.mContext = context;
        this.mServerInfo = serverInfo;
        this.mHeaders = headers;

        mDisposables = new CompositeDisposable();
    }

    @Override
    public void onClick(View v) {

        List<Pair> beneficiaryUpdateStatusList = new ArrayList<>();

        beneficiaryUpdateStatusList.add(new Pair("7514456b-44e6-4b04-ba46-edeb255f72ce","2acf6197-521d-4543-80ef-a67af3f4647f"));
        beneficiaryUpdateStatusList.add(new Pair("7514456b-44e6-4b04-ba46-edeb255f72cf","5acf6197-521d-4543-80ef-a67af3f4647f"));



        if(beneficiaryUpdateStatusList==null){
            Log.d(TAG,"Found null beneficiary ID list. Fix it and try again.");
        }else if(beneficiaryUpdateStatusList.isEmpty()){
            Log.d(TAG,"Empty beneficiary ID list. Is the sample file correct? Please check and try again.");
        }else {

            Observer<List<BeneficiaryEditStatusResponse>> nowObserver = new Observer<List<BeneficiaryEditStatusResponse>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mDisposables.add(d);
                }

                @Override
                public void onNext(@NonNull List<BeneficiaryEditStatusResponse> beneficiaryEditStatusResponse) {
                    Log.d(TAG,"Received response from server");
                    if(beneficiaryEditStatusResponse==null){
                        Log.d(TAG,"Received null response from server");
                    }else if(beneficiaryEditStatusResponse.isEmpty()){
                        Log.d(TAG,"Received empty response from server");
                    }else{
                        for(BeneficiaryEditStatusResponse nowResponse:beneficiaryEditStatusResponse){
                            Log.d(TAG,"Request ID = "+nowResponse.getRequestId());
                            Log.d(TAG,"Application ID = "+nowResponse.getApplicationId());
                            Log.d(TAG,"Error Message = "+nowResponse.getErrorMessage());
                            Log.d(TAG,"Result = "+nowResponse.getResult());
                            Log.d(TAG,"------------------------------------------");
                        }

                    }
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
            try {
                Observable<List<BeneficiaryEditStatusResponse>> beneficiaryEditResponse = beneficiaryEditService.getBeneficiaryEditStatus(beneficiaryUpdateStatusList,mHeaders,5);
                beneficiaryEditResponse = beneficiaryEditResponse.
                        subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread());
                beneficiaryEditResponse.subscribe(nowObserver);
            }catch(Throwable t){
                Log.e(TAG,"Error occured while calling editBeneficiaryAsync. "+t.getMessage());
                t.printStackTrace();
            }

        }
    }


}
