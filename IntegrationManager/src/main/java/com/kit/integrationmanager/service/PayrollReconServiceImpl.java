package com.kit.integrationmanager.service;

import android.os.Build;
import android.os.Looper;
import android.util.Log;

import com.kit.integrationmanager.APIClient;
import com.kit.integrationmanager.APIInterface;

import com.kit.integrationmanager.model.ServerInfo;

import com.kit.integrationmanager.payload.reconcile.request.PayrollReconcileBatchRequest;
import com.kit.integrationmanager.payload.reconcile.request.PayrollReconcileRequest;
import com.kit.integrationmanager.payload.reconcile.response.PayrollReconcileBatchResponse;
import com.kit.integrationmanager.payload.reconcile.response.PayrollReconcileResponse;
import java.util.HashMap;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Response;

public class PayrollReconServiceImpl implements PayrollReconService{

    private ServerInfo mServerInfo;
    Call<PayrollReconcileResponse> mApiCall;
    Call<PayrollReconcileBatchResponse> mApiCallBatch;
    private String TAG = "PayrollReconciliationService";

    public PayrollReconServiceImpl(ServerInfo mServerInfo) {
        this.mServerInfo = mServerInfo;
        this.mApiCall=null;
    }

    @Override
    public Observable<PayrollReconcileResponse> reconcilePayroll(PayrollReconcileRequest payrollReconRequest, HashMap<String, String> headers) {

        Observable<PayrollReconcileResponse> nowObservable = Observable.create(emitter -> {
            APIInterface apiInterface;
            Response<PayrollReconcileResponse> response;

            // Check if the current thread is the UI thread
            boolean isUiThread = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    ? Looper.getMainLooper().isCurrentThread()
                    : Thread.currentThread() == Looper.getMainLooper().getThread();

            try {
                if (isUiThread) {
                    emitter.onError(new IllegalStateException("Please call this API from non UI thread."));
                } else if (!isValidPayrollRequest(payrollReconRequest)) {
                    emitter.onError(new IllegalArgumentException("Invalid payroll reconciliation request"));
                } else {

                    apiInterface = APIClient.getInstance().setServerInfo(mServerInfo).getRetrofit().create(APIInterface.class);

                    this.mApiCall = apiInterface.reconcilePayroll(payrollReconRequest,headers);

                    response = this.mApiCall.execute();

                    if (response.isSuccessful()) {
                        PayrollReconcileResponse nowResponse = response.body();
                        if(!nowResponse.getError()) {
                            Log.d(TAG,"Reconciliation Successfull");
                            emitter.onNext(response.body());
                            emitter.onComplete();


                        }else{
                            emitter.onError(new Throwable(nowResponse.getErrorMsg()));
                        }
                        emitter.onComplete();
                    } else {
                        emitter.onError(new Throwable("Error in payroll reconciliation. Error code ="+response.code()+". Message ="+ response.errorBody().string()));
                        emitter.onComplete();
                    }
                }
            }catch(Throwable t){
                emitter.onError(t);
                emitter.onComplete();
            }
        });

        return nowObservable;
    }

    @Override
    public Observable<PayrollReconcileBatchResponse> reconcilePayrollBatch(PayrollReconcileBatchRequest payrollReconBatchRequest, HashMap<String, String> headers) {

        Observable<PayrollReconcileBatchResponse> nowObservable = Observable.create(emitter -> {
            APIInterface apiInterface;
            Response<PayrollReconcileBatchResponse> response;

            // Check if the current thread is the UI thread
            boolean isUiThread = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    ? Looper.getMainLooper().isCurrentThread()
                    : Thread.currentThread() == Looper.getMainLooper().getThread();

            try {
                if (isUiThread) {
                    emitter.onError(new IllegalStateException("Please call this API from non UI thread."));
                } else if (!isValidPayrollRequestBatch(payrollReconBatchRequest)) {
                    emitter.onError(new IllegalArgumentException("Invalid batch payroll reconciliation request"));
                } else {

                    apiInterface = APIClient.getInstance().setServerInfo(mServerInfo).getRetrofit().create(APIInterface.class);

                    this.mApiCallBatch = apiInterface.reconcilePayrollBatch(payrollReconBatchRequest,headers);

                    response = this.mApiCallBatch.execute();

                    if (response.isSuccessful()) {
                        PayrollReconcileBatchResponse nowResponse = response.body();

                        if(nowResponse.getBulkResponse()!=null && !nowResponse.getBulkResponse().isEmpty() ) {
                            Log.d(TAG,"Batch payroll reconciliation successfull");
                            emitter.onNext(response.body());
                            emitter.onComplete();
                        }else{
                            emitter.onError(new Throwable("Empty batch payroll reconciliation response"));
                        }
                        emitter.onComplete();
                    } else {

                        emitter.onError(new Throwable("Error in payroll reconciliation. Error code ="+response.code()+". Message ="+ response.errorBody().string()));
                        emitter.onComplete();
                    }
                }
            }catch(Throwable t){
                emitter.onError(t);
                emitter.onComplete();
            }
        });

        return nowObservable;

    }

    private boolean isValidPayrollRequest(PayrollReconcileRequest payrollReconRequest){
        if(payrollReconRequest==null) return false;
        if(payrollReconRequest.getData()==null) return false;
        return true;
    }

    private boolean isValidPayrollRequestBatch(PayrollReconcileBatchRequest payrollReconBatchRequest){
        if(payrollReconBatchRequest==null) return false;
        if(payrollReconBatchRequest.getDataList()==null) return false;
        if(payrollReconBatchRequest.getDataList().isEmpty()) return false;
        return true;
    }

    @Override
    public void cancelPayrollReconciliation() {
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
            Log.e(TAG,"Could not cancel Payroll Reconciliation. Error : "+t.getMessage());
        }
    }
}
