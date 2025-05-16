package com.kit.integrationmanager.service;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.util.Log;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kit.CaseUtils;
import com.kit.integrationmanager.APIClient;
import com.kit.integrationmanager.APIInterface;
import com.kit.integrationmanager.event.DownloadProgressEvent;
import com.kit.integrationmanager.event.bus.SimpleEventBus;
import com.kit.integrationmanager.interceptor.DownloadProgressInterceptor;
import com.kit.integrationmanager.model.Payroll;
import com.kit.integrationmanager.model.ServerInfo;
import com.kit.integrationmanager.payload.download.request.PayrollLockRequest;
import com.kit.integrationmanager.payload.download.request.PayrollRequest;
import com.kit.integrationmanager.payload.download.response.PayrollLockResponse;
import com.kit.integrationmanager.payload.download.response.PayrollResponse;
import com.kit.integrationmanager.payload.login.request.LoginRequest;
import com.kit.integrationmanager.payload.login.response.LoginResponse;
import com.kit.integrationmanager.payload.reset.response.ResetPassResponse;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Response;

public class DownloadServiceImpl implements DownloadService{
    private ServerInfo mServerInfo;
    private Disposable  mProgressSubscription;
    private Call<PayrollResponse> mApiCall;
    private String TAG = "PayrollDownloadService";

    private static final String AES_TRANSFORMATION = "AES/CBC/PKCS7Padding";
    private static final String AES_ALGORITHM = "AES";

    private static final byte[] KEY = hexStringToByteArray("dd8de506bd4a90418afe9372f01b979b6f0df2e8aae22ec90b09a0bc197dc80c");
    private static final byte[] IV = hexStringToByteArray("4a6f8e2f8a0c5d3e4b6c8d9e0f1a2b3c");


    public DownloadServiceImpl(ServerInfo mServerInfo) {
        this.mServerInfo = mServerInfo;
        this.mProgressSubscription = null;
        this.mApiCall=null;
    }

    @Override
    public Observable<PayrollResponse> loadPayrol(PayrollRequest payrollRequest, HashMap<String,String> headers, final Subscriber<DownloadProgressEvent> observeProgress){

        SimpleEventBus.getInstance()
                    .filteredObservable(DownloadProgressEvent.class)
            .subscribe(new Observer<DownloadProgressEvent>(){

                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mProgressSubscription = d;
                }
                @Override
                public void onNext(@NonNull DownloadProgressEvent downloadProgressEvent) {
                    observeProgress.onNext(downloadProgressEvent); // notify listener
                }
                @Override
                public void onError(@NonNull Throwable e) {
                    observeProgress.onError(e);
                }
                @Override
                public void onComplete() {
                    observeProgress.onComplete();
                }
            });

        Observable<PayrollResponse> nowObservable = Observable.create(emitter -> {
            APIInterface apiInterface;
            Response<PayrollResponse> response;

            // Check if the current thread is the UI thread
            boolean isUiThread = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    ? Looper.getMainLooper().isCurrentThread()
                    : Thread.currentThread() == Looper.getMainLooper().getThread();

            try {
                if (isUiThread) {
                    emitter.onError(new IllegalStateException("Please call this API from non UI thread."));
                } else if (!isValidPayrollRequest(payrollRequest)) {
                    emitter.onError(new IllegalArgumentException("Invalid payroll request"));
                } else {
                    headers.put(DownloadProgressInterceptor.DOWNLOAD_IDENTIFIER_HEADER, "true");

                    apiInterface = APIClient.getInstance().setServerInfo(mServerInfo).getRetrofit().create(APIInterface.class);

                    this.mApiCall = apiInterface.loadPayroll(payrollRequest, headers);

                    response = this.mApiCall.execute();

                    if (response.isSuccessful()) {
                        PayrollResponse nowResponse = response.body();
                        if(nowResponse.isOperationResult()) {
                            Log.d(TAG,"Payroll Data "+nowResponse.getPayrolls());

                            if(nowResponse.getTotal()>0){
                                emitter.onNext(response.body());
                            }else{
                                emitter.onError(new Throwable("No record recieved from server"));
                            }

                        }else{
                            emitter.onError(new Throwable(nowResponse.getErrorMsg()));
                        }
                        emitter.onComplete();
                    } else {
                        PayrollResponse errorResponse = preparePayrollResponse(response.code(), response.errorBody().string(), false);
                        emitter.onNext(errorResponse);
                        emitter.onComplete();
                    }
                }
            }catch(Throwable t){
                emitter.onError(t);
                if(this.mProgressSubscription!=null && !this.mProgressSubscription.isDisposed()){
                    this.mProgressSubscription.dispose();
                }
            }
        });

        return nowObservable;
    }

    @Override
    public Observable<PayrollResponse> loadPayrolFromFile(Context context,Uri payrolFile) {
        Observable<PayrollResponse> nowObservable = Observable.create(emitter -> {


            // Check if the current thread is the UI thread
            boolean isUiThread = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    ? Looper.getMainLooper().isCurrentThread()
                    : Thread.currentThread() == Looper.getMainLooper().getThread();

            try {
                if (isUiThread) {
                    emitter.onError(new IllegalStateException("Please call this API from non UI thread."));
                } else if (payrolFile==null) {
                    emitter.onError(new IllegalArgumentException("Invalid payroll file"));
                } else {
                    byte[] encData = readBinaryFile(context,payrolFile);
                    byte[] decData = decrypt(encData,KEY,IV);
                    encData = null;
                    if (decData != null){
                        Log.d(TAG, "Data first form: " + new String(decData));
                        Log.d(TAG, "Data second form: " + new String(decData, StandardCharsets.UTF_8));
                    }
                    ObjectMapper payrolCreator = new ObjectMapper();
                    payrolCreator.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,true);
                    PayrollResponse payrollResponse = payrolCreator.readValue(decData,PayrollResponse.class);
                    emitter.onNext(payrollResponse);

                    emitter.onComplete();

                }
            }catch(Throwable t){
                emitter.onError(t);
            }
        });

        return nowObservable;
    }

    @Override
    public Observable<PayrollLockResponse> lockPayrol(PayrollLockRequest payrollLockRequest, HashMap<String, String> headers) {

        Observable<PayrollLockResponse> nowObservable = Observable.create(emitter -> {
            APIInterface apiInterface;
            Response<PayrollLockResponse> response;

            // Check if the current thread is the UI thread
            boolean isUiThread = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    ? Looper.getMainLooper().isCurrentThread()
                    : Thread.currentThread() == Looper.getMainLooper().getThread();

            try {
                if (isUiThread) {
                    emitter.onError(new IllegalStateException("Please call this API from non UI thread."));
                } else if (!isValidPayrollLockRequest(payrollLockRequest)) {
                    emitter.onError(new IllegalArgumentException("Invalid payroll lock request"));
                } else {

                    apiInterface = APIClient.getInstance().setServerInfo(mServerInfo).getRetrofit().create(APIInterface.class);

                    Call<PayrollLockResponse> nowCall = apiInterface.lockPayroll(payrollLockRequest, headers);

                    response = nowCall.execute();

                    if (response.isSuccessful()) {
                        emitter.onNext(response.body());
                        emitter.onComplete();
                    } else {
                        PayrollLockResponse errorResponse = preparePayrollLockResponse(response.code(), response.errorBody().string());
                        emitter.onNext(errorResponse);
                        emitter.onComplete();
                    }
                }
            }catch(Throwable t){
                emitter.onError(t);
                if(this.mProgressSubscription!=null && !this.mProgressSubscription.isDisposed()){
                    this.mProgressSubscription.dispose();
                }
            }
        });

        return nowObservable;
    }

    private PayrollResponse preparePayrollResponse(int errorCode, String errorMsg, boolean operationResult){
        PayrollResponse payrollResponse = new PayrollResponse();

        payrollResponse.setErrorCode(errorCode);
        payrollResponse.setErrorMsg(errorMsg);
        payrollResponse.setOperationResult(operationResult);

        return payrollResponse;
    }

    private PayrollLockResponse preparePayrollLockResponse(int errorCode, String errorMsg){
        PayrollLockResponse payrollLockResponse = new PayrollLockResponse();

        payrollLockResponse.setResponseCode(errorCode);
        payrollLockResponse.setResponseMessage(errorMsg);

        return payrollLockResponse;
    }

    private boolean isValidPayrollRequest(PayrollRequest payrollRequest){
        if(payrollRequest==null) return false;
        if(payrollRequest.getState()==null || payrollRequest.getState().isEmpty()) return false;
        if(payrollRequest.getCounty()==null || payrollRequest.getCounty().isEmpty()) return false;
        if(payrollRequest.getPayam()==null || payrollRequest.getPayam().isEmpty()) return false;
        if(payrollRequest.getBoma()==null || payrollRequest.getBoma().isEmpty()) return false;
        return true;
    }

    private boolean isValidPayrollLockRequest(PayrollLockRequest payrollLockRequest){
        if(payrollLockRequest==null) return false;
//        if(payrollLockRequest.getWagePaymentReqId()==0) return false;
        if(payrollLockRequest.getState()==0) return false;
        if(payrollLockRequest.getCounty()==0) return false;
        if(payrollLockRequest.getPayam()==0) return false;
        if(payrollLockRequest.getBoma()==0) return false;
        if(payrollLockRequest.getSupportType()==null || payrollLockRequest.getSupportType().isEmpty()) return false;

        return true;
    }

    public void cancelPayrollLoading(){
        try{
            if(this.mApiCall!=null && !this.mApiCall.isCanceled()){
                this.mApiCall.cancel();
                this.mApiCall = null;
            }
        }catch(Throwable t){
            Log.e(TAG,"Could not cancel Payroll loading. Error : "+t.getMessage());
        }
    }

    private byte[] readBinaryFile(Context context,Uri uri) throws Throwable {
        ContentResolver contentResolver = context.getContentResolver();
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try {
            inputStream = contentResolver.openInputStream(uri);
            if (inputStream == null) {
                throw new IOException("Unable to open input stream for URI: " + uri.toString());
            }

            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            return byteArrayOutputStream.toByteArray();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        }
    }

    private String readTextFile(Context context, Uri uri) throws IOException {
        ContentResolver contentResolver = context.getContentResolver();
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            inputStream = contentResolver.openInputStream(uri);
            if (inputStream == null) {
                throw new IOException("Unable to open input stream for URI: " + uri.toString());
            }

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            return stringBuilder.toString().trim();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private byte[] decrypt(byte[] data, byte[] key, byte[] iv) throws Throwable {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        return cipher.doFinal(data);
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
