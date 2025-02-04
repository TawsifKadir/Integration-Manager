package com.kit.integrationmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
import com.kit.integrationmanager.test.UpdateBeneficiary;
import com.kit.integrationmanager.test.UpdateBeneficiaryStatus;


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



    private String TAG = "OnlineIntegratioManagerTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Button editBeneficiaryBtn = (Button)findViewById(R.id.editBeneficiaryBtn);
        Button getBeneficiaryEditStatus = (Button)findViewById(R.id.getBeneficiaryStatusBtn);

        UniqueID = DeviceManager.getInstance(MainActivity.this).getDeviceUniqueID();
        Log.d(TAG,"Device unique ID is : "+UniqueID);
        Log.d(TAG,"Is Device online : "+DeviceManager.getInstance(this).isOnline());

        mDisposables = new CompositeDisposable();

        mServerInfo = new ServerInfo();
        mServerInfo.setPort(8090);
        mServerInfo.setProtocol("http");
        mServerInfo.setHost_name("dev.karoothitbd.com");

        mHeaders = new HashMap<>();
        mHeaders.put("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzaG92b24iLCJpYXQiOjE3MDg4ODY5OTYsImV4cCI6MTg2NjU2Njk5Nn0.L-75R-EYM1GbrAqj-KdRpWLjxfxCMdVsAboepITEnI2I6AtTUtRhTgQaevzb5GOLWPnGaAUzggcC6SsArnMj-g");
        mHeaders.put("DeviceId", "29feb6211b04-a56d-7d6a-e79e-018b2f19");

        UpdateBeneficiary updateBeneficiaryProcessor = new UpdateBeneficiary(MainActivity.this,mServerInfo,mHeaders,true);

        editBeneficiaryBtn.setOnClickListener(updateBeneficiaryProcessor);

        UpdateBeneficiaryStatus updateBeneficiaryStatusProcessor = new UpdateBeneficiaryStatus(MainActivity.this,mServerInfo,mHeaders);

        getBeneficiaryEditStatus.setOnClickListener(updateBeneficiaryStatusProcessor);

        mapper = new ObjectMapper();

    }


    public void update(Observable o, Object arg) {

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