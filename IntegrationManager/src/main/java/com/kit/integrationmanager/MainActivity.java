package com.kit.integrationmanager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kit.integrationmanager.model.ServerInfo;
import com.kit.integrationmanager.payload.download.request.BeneficiaryDownloadRequest;
import com.kit.integrationmanager.payload.download.response.BeneficiaryDownloadResponse;
import com.kit.integrationmanager.payload.login.response.LoginResponse;
import com.kit.integrationmanager.payload.reset.response.ResetPassResponse;
import com.kit.integrationmanager.service.BeneficiaryDownloadService;
import com.kit.integrationmanager.service.BeneficiaryDownloadServiceImpl;
import com.kit.integrationmanager.service.LoginServiceImpl;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements Observer {

    private static final String TAG = "MainActivity";
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);
    private HashMap<String, String> mHeaders;
    private ServerInfo mServerInfo;
    private String authToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

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
        resetPasswordButton.setOnClickListener(v -> testResetPassword());
        getBeneficiariesButton.setOnClickListener(v -> testGetBeneficiaries());
    }

    private void testLogin() {
        showToast("Attempting login...");
        executorService.execute(() -> {
            LoginServiceImpl loginService = new LoginServiceImpl(this, this, mServerInfo);
            String username = "shovon1";
            String password = "HelloWorld";
            loginService.doOnlineLogin(username, password, mHeaders);
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


    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof LoginResponse) {
            LoginResponse response = (LoginResponse) arg;
            runOnUiThread(() -> {
                if (response.isOperationResult()) {
                    showToast("Login successful!");
                    authToken = response.getToken(); // Store the token for future requests
                    Log.d(TAG, "Auth token: " + authToken);
                } else {
                    showToast("Login failed: " + response.getErrorMsg());
                }
            });
        } else if (arg instanceof ResetPassResponse) {
            ResetPassResponse response = (ResetPassResponse) arg;
            runOnUiThread(() -> {
                if (response.isOperationResult()) {
                    showToast("Password reset successful!");
                    authToken = response.getToken(); // Update the token if needed
                } else {
                    showToast("Reset failed: " + response.getErrorMsg());
                }
            });
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