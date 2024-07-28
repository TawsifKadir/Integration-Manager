package com.kit.integrationmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kit.databasemanager.dao.PayrollDataDao;
import com.kit.databasemanager.dao.PayrollTransactionDao;

import com.kit.databasemanager.database.PaymentDatabase;
import com.kit.databasemanager.model.PayrollBiometricEO;
import com.kit.databasemanager.model.PayrollDataEO;
import com.kit.databasemanager.model.PayrollEO;
import com.kit.integrationmanager.event.DownloadProgressEvent;
import com.kit.integrationmanager.model.AlternatePayee;
import com.kit.integrationmanager.model.Login;
import com.kit.integrationmanager.model.Payroll;
import com.kit.integrationmanager.model.PayrollAlternate;
import com.kit.integrationmanager.model.PayrollReconcile;
import com.kit.integrationmanager.model.ServerInfo;

import com.kit.integrationmanager.payload.download.request.PayrollRequest;
import com.kit.integrationmanager.payload.download.response.PayrollResponse;

import com.kit.integrationmanager.payload.reconcile.request.PayrollReconcileBatchRequest;
import com.kit.integrationmanager.payload.reconcile.request.PayrollReconcileRequest;
import com.kit.integrationmanager.payload.reconcile.response.PayrollReconcileBatchResponse;
import com.kit.integrationmanager.payload.reconcile.response.PayrollReconcileResponse;
import com.kit.integrationmanager.service.DeviceManager;
import com.kit.integrationmanager.service.DownloadService;
import com.kit.integrationmanager.service.DownloadServiceImpl;
import com.kit.integrationmanager.service.PayrollReconService;
import com.kit.integrationmanager.service.PayrollReconServiceImpl;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ObjectMapper mapper = null;
    private Login mLogin = null;
    private String mAuthToken = null;

    private String UniqueID = null;

    private Subscriber<DownloadProgressEvent> progressSubscriber;

    private CompositeDisposable mDisposables;
    private ServerInfo mServerInfo;

    private HashMap<String, String> mHeaders;

    private  int totalDataCount = 0;
    private  int storedDataCount = 0;

    private String TAG = "OnlineIntegratioManagerTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button registerBtn = (Button)findViewById(R.id.registerBtn);
        Button loginBtn = (Button)findViewById(R.id.loginBtn);
        Button resetBtn = (Button)findViewById(R.id.resetPassBtn);
        Button registerDeviceBtn = (Button)findViewById(R.id.registerDeviceBtn);

        UniqueID = DeviceManager.getInstance(MainActivity.this).getDeviceUniqueID();
        Log.d(TAG,"Device unique ID is : "+UniqueID);
        Log.d(TAG,"Is Device online : "+DeviceManager.getInstance(this).isOnline());

        mDisposables = new CompositeDisposable();

        mServerInfo = new ServerInfo();
        mServerInfo.setPort(8090);
        mServerInfo.setProtocol("http");
        mServerInfo.setHost_name("dev.karoothitbd.com");

        mHeaders = new HashMap<>();
        mHeaders.put("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYWtpYiIsImlhdCI6MTcxNDk4MTEwOCwiZXhwIjoxODcyNjYxMTA4fQ.7FgalnXhBOcC7q-nYfONbxmc7My4iUOeWpN2eK3b7vc_br43NCEFiKWIrXs1cJTsU_Ds_ZZLHRB28c-jdgiBRw");
        mHeaders.put("DeviceId", "29feb6211b04-a56d-7d6a-e79e-018b2f19");



        mapper = new ObjectMapper();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    progressSubscriber = new Subscriber<DownloadProgressEvent>() {
                        @Override
                        public void onComplete() {
                            Log.d(TAG, "Progress listener on complete called");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "Progress listener on error called.");
                            Log.e(TAG, "Error msg >> " + e.getMessage());
                            e.printStackTrace();
                        }

                        @Override
                        public void onSubscribe(Subscription s) {
                            Log.d(TAG, "Progress listener on subscribe called");

                        }

                        @Override
                        public void onNext(DownloadProgressEvent progressEvent) {
                            Log.d(TAG, "Progress listener on next called.");
                            Log.d(TAG, "Progress : " + progressEvent.getProgress());
                        }
                    };


                    DownloadService downloadService = new DownloadServiceImpl(mServerInfo);
                    PayrollRequest payrollRequest = PayrollRequest.builder().state(String.valueOf(81)).county(String.valueOf(8103)).payam(String.valueOf(810303)).boma(String.valueOf(810303002)).build();


                    Observable<PayrollResponse> nowObservable = downloadService.loadPayrol(payrollRequest,mHeaders, progressSubscriber);

                    nowObservable.subscribeOn(Schedulers.io()).
                            observeOn(AndroidSchedulers.mainThread()).
                            subscribe(new Observer<PayrollResponse>() {
                                PayrollResponse nowPayrollResponse = null;
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    Log.d(TAG, "Observer has been subscribed.");
                                    mDisposables.add(d);
                                }

                                @Override
                                public void onNext(@NonNull PayrollResponse payrollResponse) {
                                    Log.d(TAG, "Got the data.");
                                    Log.d(TAG, "Operation Result : " + payrollResponse.isOperationResult());
                                    Log.d(TAG, "Error Code : " + payrollResponse.getErrorCode());
                                    Log.d(TAG, "Error Message : " + payrollResponse.getErrorMsg());
                                    Log.d(TAG, "Total Records : " + payrollResponse.getTotal());
                                    nowPayrollResponse = payrollResponse;
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Log.d(TAG, "Error occured : " + e.getMessage());
                                    e.printStackTrace();
                                }

                                @Override
                                public void onComplete() {
                                    Log.d(TAG, "Task Completed");
                                    storeInDatabase(nowPayrollResponse);
                                }
                            });

                }catch (Exception ex){
                    Log.e(TAG,"Error while sending data : "+ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        registerDeviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readPayrollReconcileJson(MainActivity.this).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<PayrollReconcileBatchRequest>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                mDisposables.add(d);
                            }

                            @Override
                            public void onNext(@NonNull PayrollReconcileBatchRequest payrollReconcileBatchRequest) {


                                PayrollReconService payrollReconService = new PayrollReconServiceImpl(mServerInfo);
                                Observable<PayrollReconcileBatchResponse> nowObservable = payrollReconService.reconcilePayrollBatch(payrollReconcileBatchRequest,mHeaders);
                                nowObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Observer<PayrollReconcileBatchResponse>() {
                                            @Override
                                            public void onSubscribe(@NonNull Disposable d) {
                                                mDisposables.add(d);
                                            }

                                            @Override
                                            public void onNext(@NonNull PayrollReconcileBatchResponse payrollReconcileBatchResponse) {
                                                Log.d(TAG,"Received batch payroll reconciliation reponse");
                                                Log.d(TAG,"Response size = "+payrollReconcileBatchResponse.getBulkResponse().size());
                                            }

                                            @Override
                                            public void onError(@NonNull Throwable e) {
                                                Log.e(TAG,"Error in bath payroll reconciliation = "+e.getMessage());
                                                e.printStackTrace();
                                            }

                                            @Override
                                            public void onComplete() {
                                                Log.d(TAG,"Batch payroll reconciliation completed");
                                            }
                                        });
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.e(TAG,e.getMessage());
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG,"Payroll data loaded.");
                            }
                        });
            }
        });
    }


    public void update(Observable o, Object arg) {

    }

    public void storeInDatabase(final PayrollResponse payrollResponse){

        if(payrollResponse==null){
            Log.d(TAG,"Empty payroll response. Nothing to do");
        }


        PaymentDatabase.getInstance(MainActivity.this).payrollCleanupDao().deleteAllPayroll();

        Observable<Integer> nowObservable = new Observable<Integer>() {
            @Override
            protected void subscribeActual(@NonNull Observer<? super Integer> emitter) {

                Payroll payroll = payrollResponse.getPayrolls().get(0);
                totalDataCount = payroll.getPayrollData().size();

                PaymentDatabase paymentDatabase = PaymentDatabase.getInstance(MainActivity.this);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                        PayrollEO payrollEO = new PayrollEO();
                        payrollEO.mapFromBO(payroll);

                        paymentDatabase.payrollDao().insert(payrollEO);

                        payroll.getPayrollData().stream().forEach(payrollData -> {
                            try {

                                    PayrollDataEO payrollDataEO = null;
                                    PayrollBiometricEO payrollBenBiometricEO = null;
                                    PayrollBiometricEO payrollFirstAlternateBiometricEO = null;
                                    PayrollBiometricEO payrollSecondAlternateBiometricEO = null;


                                    if (payrollData != null && payrollData.getHouseHoldDetail() != null) {
                                        payrollDataEO = new PayrollDataEO();
                                        payrollDataEO.mapFromBO(payrollData,payrollData.getHouseHoldDetail(),payrollData.getAlternates());
                                    }

                                    if(payrollData.getHouseHoldDetail().getBiometric()!=null){
                                        payrollBenBiometricEO = new PayrollBiometricEO();
                                        payrollBenBiometricEO.mapFromBO(payrollData.getHouseHoldDetail().getBiometric());
                                    }

                                    List<PayrollAlternate> altList = payrollData.getAlternates();

                                    if(altList!=null){
                                        if(altList.size()>0 && (altList.get(0).getBiometric()!=null) ){
                                            payrollFirstAlternateBiometricEO = new PayrollBiometricEO();
                                            payrollFirstAlternateBiometricEO.mapFromBO(altList.get(0).getBiometric());
                                        }

                                        if(altList.size()>1 && (altList.get(1).getBiometric()!=null)){
                                            payrollSecondAlternateBiometricEO = new PayrollBiometricEO();
                                            payrollSecondAlternateBiometricEO.mapFromBO(altList.get(1).getBiometric());
                                        }
                                    }

                                    PayrollTransactionDao payrollTransactionDao = paymentDatabase.payrollTransactionDao();
                                    payrollTransactionDao.insertPayrollRecord(payrollEO,
                                            payrollDataEO,
                                            payrollBenBiometricEO,
                                            payrollFirstAlternateBiometricEO,
                                            payrollSecondAlternateBiometricEO
                                            );

                                     storedDataCount++;
                                     emitter.onNext(storedDataCount);

                            }catch(Throwable t){
                               emitter.onError(t);
                            }

                        });
                    }

                emitter.onComplete();
            }


        };
        nowObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
            
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(@NonNull Integer count) {
               Log.d(TAG,"Stored "+count+" of "+totalDataCount);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG,"Error occured : "+e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Data insert completed");
                populateAFIS();
            }
        });
    }

    public void populateAFIS(){
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Throwable {
                try {
                    PayrollDataDao payrollDataDao = PaymentDatabase.getInstance(MainActivity.this).payrollDataDao();
                    Log.d(TAG,"Total payroll data count = "+payrollDataDao.findTotalPayrollData());

                    List<PayrollDataEO> payrollDataDaoList = payrollDataDao.findAllWithLimitOffset(500, 0);
                    Log.d(TAG,"Payroll Data List size = "+payrollDataDaoList.size());

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        payrollDataDaoList.forEach(data -> {
                            Log.d(TAG, "Beneficiary name : " + data.benFirstName + " " + data.benMiddleName + " " + data.benLastName);
                        });
                    }

                    emitter.onComplete();
                }catch(Throwable t){
                    emitter.onError(t);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"DONE");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"ERROR OCCURED : "+e.getMessage());
                e.printStackTrace();
            }
        });

    }

    private Observable<PayrollReconcileBatchRequest> readPayrollReconcileJson(Context context) {

        Observable<PayrollReconcileBatchRequest> nowObservable = new Observable<PayrollReconcileBatchRequest>() {
            @Override
            protected void subscribeActual(@NonNull Observer<? super PayrollReconcileBatchRequest> observer) {
                PayrollReconcileBatchRequest payrollReconcileBatchRequest = null;

                try {

                    InputStream inputStream = context.getResources().openRawResource(R.raw.payroll_reconcile_request);
                    ObjectMapper objectMapper = new ObjectMapper();
                    payrollReconcileBatchRequest = objectMapper.readValue(inputStream, PayrollReconcileBatchRequest.class);

                } catch (Throwable t) {
                    observer.onError(t);
                    observer.onComplete();
                }

                observer.onNext(payrollReconcileBatchRequest);
                observer.onComplete();
            }
        };


        return nowObservable;
    }

}