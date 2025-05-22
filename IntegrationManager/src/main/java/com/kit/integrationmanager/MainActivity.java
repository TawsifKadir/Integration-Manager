package com.kit.integrationmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kit.integrationmanager.model.Beneficiary;
import com.kit.integrationmanager.model.BulkResponse;
import com.kit.integrationmanager.model.ServerInfo;
import com.kit.integrationmanager.payload.OperationStatus;
import com.kit.integrationmanager.payload.download.request.BeneficiaryDownloadRequest;
import com.kit.integrationmanager.payload.download.response.BeneficiaryDownloadResponse;
import com.kit.integrationmanager.payload.login.callback.LoginCallBack;
import com.kit.integrationmanager.payload.login.response.LoginResponse;
import com.kit.integrationmanager.payload.update.BeneficiaryUpdateRequestBody;
import com.kit.integrationmanager.payload.update.request.BeneficiaryUpdateRequestV2;
import com.kit.integrationmanager.payload.update.request.BeneficiaryUpdateStatusRequest;
import com.kit.integrationmanager.payload.update.request.UpdateFullBeneficiaryRequest;
import com.kit.integrationmanager.payload.update.response.BeneficiaryUpdateStatusResponse;
import com.kit.integrationmanager.service.BeneficiaryDownloadService;
import com.kit.integrationmanager.service.BeneficiaryDownloadServiceImpl;
import com.kit.integrationmanager.service.BeneficiaryUpdateService;
import com.kit.integrationmanager.service.BeneficiaryUpdateServiceImpl;
import com.kit.integrationmanager.service.LoginServiceImpl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);
    private HashMap<String, String> mHeaders;
    private ServerInfo mServerInfo;
    private String authToken;
    List<BeneficiaryUpdateRequestBody> requestBodies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        requestBodies = new ArrayList<>();

        // Initialize UI components
        Button loginButton = findViewById(R.id.loginBtn);
        Button resetPasswordButton = findViewById(R.id.resetPasswordBtn);
        Button getBeneficiariesButton = findViewById(R.id.getBeneficiariesBtn);

        // Setup server configuration
        mServerInfo = new ServerInfo();
        mServerInfo.setPort(8090);
        mServerInfo.setProtocol("http");
        mServerInfo.setHost_name("dev.karoothitbd.com");

        // Setup headers
        mHeaders = new HashMap<>();
        mHeaders.put("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYWtpYiIsImlhdCI6MTcxNDk4MTEwOCwiZXhwIjoxODcyNjYxMTA4fQ.7FgalnXhBOcC7q-nYfONbxmc7My4iUOeWpN2eK3b7vc_br43NCEFiKWIrXs1cJTsU_Ds_ZZLHRB28c-jdgiBRw");
        mHeaders.put("DeviceId", "29feb6211b04-a56d-7d6a-e79e-018b2f19");

        // Set up button click listeners
        loginButton.setOnClickListener(v -> testLogin());
        resetPasswordButton.setOnClickListener(v -> testGetBeneficiaryUpdateStatus());
        getBeneficiariesButton.setOnClickListener(v -> testUpdateBeneficiary());
    }

    private void testLogin() {
        showToast("Attempting login...");
        executorService.execute(() -> {
            LoginServiceImpl loginService = new LoginServiceImpl(this, mServerInfo);
            String username = "admin";
            String password = "Abc@123";
            loginService.doOnlineLogin(username, password, mHeaders, new LoginCallBack() {
                @Override
                public void onSuccess(LoginResponse response) {
                    Log.d(TAG, "onSuccess() called with: response = [" + response + "]");
                }

                @Override
                public void onError(int errorCode, String errorMessage) {

                }
            });
        });
    }

    private void testResetPassword() {
        showToast("Attempting password reset...");
        executorService.execute(() -> {
//            LoginServiceImpl loginService = new LoginServiceImpl(this, this, mServerInfo);
//            String username = "shovon1";
//            String newPassword = "NewPassword123";
//            loginService.resetPassword(username, newPassword, mHeaders);
        });
    }

    private void testGetBeneficiaries() {
        showToast("Fetching beneficiaries...");

        BeneficiaryDownloadService beneficiaryService = new BeneficiaryDownloadServiceImpl(mServerInfo);

        BeneficiaryDownloadRequest request = BeneficiaryDownloadRequest.builder()
                .bomaId(920101001)
                .page(0)
                .size(-1)
                .build();

        beneficiaryService.downloadBeneficiaryByBoma(request, mHeaders)
                .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io()) // Run on IO thread
                .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread()) // Observe on UI thread
                .subscribe(new io.reactivex.rxjava3.core.Observer<BeneficiaryDownloadResponse>() {
                    @Override
                    public void onSubscribe(io.reactivex.rxjava3.disposables.Disposable d) {
                        // Optionally store the disposable if you want to dispose later
                    }

                    @Override
                    public void onNext(BeneficiaryDownloadResponse response) {
                        if (response.isOperationResult()) {
                            showToast("Got " + response.getTotal() + " beneficiaries");
                            Log.d(TAG, "Beneficiary data: " + response.getBeneficiaries());
                        } else {
                            showToast("Failed: " + response.getErrorMsg());
                            Log.e(TAG, "API Error: " + response.getErrorMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast("Error: " + e.getMessage());
                        Log.e(TAG, "Error fetching beneficiaries", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Beneficiary download complete");
                    }
                });
    }


    @SuppressLint("CheckResult")
    private void testUpdateBeneficiary(){

        UpdateFullBeneficiaryRequest request = readBeneficiariesFromRaw(MainActivity.this);

        BeneficiaryUpdateService updateService = new BeneficiaryUpdateServiceImpl(mServerInfo);
        updateService.updateFullBeneficiary(request, mHeaders)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            // Handle successful response
                            int successCount = response.getSuccessCount();
                            List<BulkResponse> bulkResponse = response.getBulkResponse();
                            for (BulkResponse response1 : bulkResponse){
                                BeneficiaryUpdateRequestBody requestBody = new BeneficiaryUpdateRequestBody(response1.getApplicationId(), response1.getRequestId());
                                requestBodies.add(requestBody);
                            }
                            // Process the response
                        },
                        throwable -> {
                            // Handle error
                            throwable.printStackTrace();
                        }
                );
    }

    @SuppressLint("CheckResult")
    private void testGetBeneficiaryUpdateStatus(){
        BeneficiaryUpdateService updateService = new BeneficiaryUpdateServiceImpl(mServerInfo);

        BeneficiaryUpdateStatusRequest request = new BeneficiaryUpdateStatusRequest(requestBodies);

        for (BeneficiaryUpdateRequestBody request1 : request.getRequests()){
            Log.d(TAG, "testGetBeneficiaryUpdateStatus() called " + request1.getApplicationId() + " resultId: " + request1.getRequestId());
        }

        updateService.getBeneficiaryUpdateStatus(request, mHeaders)
                .subscribeOn(Schedulers.io()) // Network call on IO thread
                .observeOn(AndroidSchedulers.mainThread()) // Handle result on UI thread
                .subscribe(
                        responseList -> {
                            // Process the full list of responses
                            for (BeneficiaryUpdateStatusResponse response : responseList) {
                                Log.d("Response",
                                        "App ID: " + response.getApplicationId() +
                                                ", Status: " + response.getResult()
                                );
                            }

                            // Or use directly
                            if (responseList.get(0).getResult() == OperationStatus.SUCCESS) {
                                // First item success handling
                            }
                        },
                        error -> {
                            // Handle error
                            Toast.makeText(this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                );
    }

    public static UpdateFullBeneficiaryRequest readBeneficiariesFromRaw(Context context) {
        try {
            Resources res = context.getResources();
            InputStream inputStream = res.openRawResource(R.raw.beneficiaries);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(inputStream);

            JsonNode beneficiariesNode = rootNode.get("beneficiaries");
            List<BeneficiaryUpdateRequestV2> beneficiaryList = new ArrayList<>();

            if (beneficiariesNode != null && beneficiariesNode.isArray()) {
                for (JsonNode node : beneficiariesNode) {
                    Beneficiary beneficiary = objectMapper.treeToValue(node, Beneficiary.class);
                    BeneficiaryUpdateRequestV2 editRequest = objectMapper.convertValue(beneficiary, BeneficiaryUpdateRequestV2.class);
                    beneficiaryList.add(editRequest);
                }
            }

            return UpdateFullBeneficiaryRequest.builder()
                    .beneficiaries(beneficiaryList)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void showToast(String message) {
        runOnUiThread(() -> Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdownNow();
    }
}