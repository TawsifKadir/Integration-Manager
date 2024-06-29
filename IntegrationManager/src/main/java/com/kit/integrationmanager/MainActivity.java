package com.kit.integrationmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kit.integrationmanager.model.Address;
import com.kit.integrationmanager.model.Beneficiary;
import com.kit.integrationmanager.model.Device;
import com.kit.integrationmanager.model.Location;
import com.kit.integrationmanager.model.Login;
import com.kit.integrationmanager.model.ServerInfo;
import com.kit.integrationmanager.model.VersionInfo;
import com.kit.integrationmanager.payload.RegistrationResult;
import com.kit.integrationmanager.payload.RegistrationStatus;
import com.kit.integrationmanager.payload.device.request.RegisterDeviceRequest;
import com.kit.integrationmanager.payload.device.response.RegisterDeviceResponse;
import com.kit.integrationmanager.payload.login.request.LoginRequest;
import com.kit.integrationmanager.payload.login.response.LoginResponse;
import com.kit.integrationmanager.payload.reset.request.ResetPassRequest;
import com.kit.integrationmanager.payload.reset.response.ResetPassResponse;
import com.kit.integrationmanager.service.DeviceManager;
import com.kit.integrationmanager.service.DeviceRegistrationServiceImpl;
import com.kit.integrationmanager.service.IntegrationManager;
import com.kit.integrationmanager.service.DeviceRegistrationService;

import com.kit.integrationmanager.service.LoginService;
import com.kit.integrationmanager.service.LoginServiceImpl;
import com.kit.integrationmanager.service.OnlineIntegrationManager;
import com.kit.integrationmanager.service.UpdateCheckService;
import com.kit.integrationmanager.service.UpdateCheckServiceImpl;
import com.kit.integrationmanager.store.AuthStore;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private ObjectMapper mapper = null;
    private Login mLogin = null;
    private String mAuthToken = null;

    private String UniqueID = null;
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
        mapper = new ObjectMapper();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    InputStream is = getResources().openRawResource(R.raw.batch_reg);
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//                    Beneficiary beneficiary = mapper.readValue(br, Beneficiary.class);
                    ////https://snsopafis.southsudansafetynet.info/afis/swagger-ui.html
                    List<Beneficiary> beneficiaries = mapper.readValue(br,new TypeReference<List<Beneficiary>>(){});
                    ServerInfo serverInfo = new ServerInfo();
                    serverInfo.setPort(443);
                    serverInfo.setProtocol("https");
                    serverInfo.setHost_name("snsopafis.southsudansafetynet.info");
                    HashMap<String,String> headers = new HashMap<>();
                    headers.put("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzaG92b24iLCJpYXQiOjE3MDg4ODY5OTYsImV4cCI6MTg2NjU2Njk5Nn0.L-75R-EYM1GbrAqj-KdRpWLjxfxCMdVsAboepITEnI2I6AtTUtRhTgQaevzb5GOLWPnGaAUzggcC6SsArnMj-g");
                    headers.put("DeviceId","d5a58ff3-dc14-4333-8076-72b0fb4cab7a");
                    IntegrationManager integrationManager = new OnlineIntegrationManager(MainActivity.this,MainActivity.this,serverInfo);
                    integrationManager.syncRecords(beneficiaries,headers);
                }catch (Exception ex){
                    Log.e(TAG,"Error while sending data : "+ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    if(DeviceManager.getInstance(MainActivity.this).isOnline()){
                        mLogin = new Login();
                        mLogin.setUserName("fakadir_23");
                        mLogin.setPassword("f230878");
                        mLogin.setDeviceID("47951385-a13f-409a-9a79-c4aaef0e3f9b");

                        ServerInfo serverInfo = new ServerInfo();
                        serverInfo.setPort(8090);
                        serverInfo.setProtocol("http");
                        serverInfo.setHost_name("snsopafis.karoothitbd.com");
                        HashMap<String,String> headers = new HashMap<>();
                        ///headers.put("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTcwNzkyODUwOCwiZXhwIjoxODY1NjA4NTA4fQ.yRgZYaP2WlSoTtP8ZjhFLCTD3_Ov7SZtVLzrWG9BK7qDrXSCIlMwJM5kS0HDyrD1_qNbJFPm8Hz9KlkFGDfQ7Q");
                        headers.put("DeviceId",mLogin.getDeviceID());

                        LoginRequest loginRequest = LoginRequest.builder().userName(mLogin.getUserName()).password(mLogin.getPassword()).deviceId(mLogin.getDeviceID()).build();

                        LoginService loginService = new LoginServiceImpl(MainActivity.this,MainActivity.this,serverInfo);

                        loginService.doOnlineLogin(loginRequest,headers);
                    }else {
                        AuthStore mAuthStore = AuthStore.getInstance(MainActivity.this);
                        mLogin = mAuthStore.getLoginInfoFromCache();
                        if(mLogin==null){
                            Log.e(TAG,"No cache available for offline login");
                        }else {
                            boolean authResult = AuthStore.getInstance(MainActivity.this).doOfflineAuthentication("fakadir_23",
                                    "f230878", "47951385-a13f-409a-9a79-c4aaef0e3f9b");
                            if(authResult){
                                Log.d(TAG,"Login Successful");
                            }else{
                                Log.e(TAG,"Login Error");
                            }
                        }
                    }
                }catch(Exception exc){
                    Log.e(TAG,"Error while sending data : "+exc.getMessage());
                    exc.printStackTrace();
                }
            }
        });

        registerDeviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    Runnable r = new Runnable() {
                        @Override
                        public void run() {

                            try {
                                ServerInfo serverInfo = new ServerInfo();
                                serverInfo.setPort(443);
                                serverInfo.setProtocol("https");
                                serverInfo.setHost_name("snsopafis.southsudansafetynet.info");

                                UpdateCheckService updateCheckService = new UpdateCheckServiceImpl(MainActivity.this, serverInfo);
                                VersionInfo versionInfo = updateCheckService.getUpdateInformation("0.59.1");
                                Log.d(TAG,"Version = "+versionInfo.getAppVersion());
                                Log.d(TAG,"apkUrl = "+versionInfo.getApkUrl());
                                Log.d(TAG,"update Available = "+versionInfo.isUpdateAvailable());
                            }catch(Exception exc){
                                exc.printStackTrace();
                            }
                        }
                    };

                    Thread t = new Thread(r);
                    t.start();



                }catch(Exception exc){
                    Log.e(TAG,"Error while sending data : "+exc.getMessage());
                    exc.printStackTrace();
                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ServerInfo serverInfo = new ServerInfo();
                    serverInfo.setPort(8090);
                    serverInfo.setProtocol("http");
                    mLogin.setPassword("f230878");
                    serverInfo.setHost_name("snsopafis.karoothitbd.com");
                    HashMap<String,String> headers = new HashMap<>();
                    headers.put("Authorization","Bearer "+mAuthToken);
                    headers.put("DeviceId","47951385-a13f-409a-9a79-c4aaef0e3f9b");

                    ResetPassRequest resetPassRequest = ResetPassRequest.builder().newPassword(mLogin.getPassword()).build();

                    LoginService loginService = new LoginServiceImpl(MainActivity.this,MainActivity.this,serverInfo);

                    loginService.doResetPassword(resetPassRequest,headers);

                }catch(Exception exc){
                    Log.e(TAG,"Error while sending data : "+exc.getMessage());
                    exc.printStackTrace();
                }
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        try{
            ObjectMapper mapper = new ObjectMapper();

            Log.d(TAG, "Received update>>>>");

            if (arg == null) {
                Log.e(TAG, "Received null parameter in update. Returning...");
                return;
            }

            if(arg instanceof LoginResponse){
                LoginResponse loginResponse = (LoginResponse)arg;
                try{
                    String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(loginResponse);
                    Log.d(TAG,"JSON Formatted Login Response: "+data);
                }catch(JsonProcessingException exc){
                    Log.e(TAG,"JSON Parse Exception.");
                    exc.printStackTrace();
                }

                if(loginResponse.isOperationResult()){
                    if("ACTIVE".equalsIgnoreCase(loginResponse.getStatus())){
                        mAuthToken = loginResponse.getToken();
                        AuthStore.getInstance(MainActivity.this).saveLoginInfoToCache(MainActivity.this.mLogin);
                        AuthStore.getInstance(MainActivity.this).setAuthToken(mAuthToken);
                        Log.d(TAG,"Online Authentication Successful");
                        ////Success - Login the user and go to dashboard
                    }else if("PENDING".equalsIgnoreCase(loginResponse.getStatus())){
                        mAuthToken = loginResponse.getToken();
                        Log.d(TAG,"Received authentication token : "+mAuthToken);
                        ///User needs to Reset Password
                    }
                }
                Log.d(TAG,"Received login request update");
            }else if(arg instanceof ResetPassResponse){
                ResetPassResponse resetPassResponse = (ResetPassResponse)arg;
                try{
                    String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resetPassResponse);
                    Log.d(TAG,"JSON Formatted Reset Password Response: "+data);
                }catch(JsonProcessingException exc){
                    Log.e(TAG,"JSON Parse Exception.");
                    exc.printStackTrace();
                }


                if(resetPassResponse.isOperationResult()){
                    if(AuthStore.getInstance(MainActivity.this).saveLoginInfoToCache(mLogin)) {
                        AuthStore.getInstance(MainActivity.this).setAuthToken(mAuthToken);
                    }
                    Log.d(TAG,"Password reset successful");
                    ///Successful. Take user back to Login Page

                }

            }else if(arg instanceof RegistrationResult){
                Log.d(TAG, "Received parameter in update.");
                RegistrationResult registrationResult = (RegistrationResult) arg;
                if (registrationResult.getSyncStatus() == RegistrationStatus.SUCCESS) {

                    Log.d(TAG, "Registration Successfull");

                    List<String> appIds = registrationResult.getApplicationIds();
                    if (appIds == null) {
                        Log.e(TAG, "No beneficiary list received. Returning ... ");
                        return;
                    }

                    Log.d(TAG, "Registered following users: ");
                    for (String nowId : appIds) {
                        Log.d(TAG, "Beneficiary ID : " + nowId);
                    }

                } else {
                    Log.d(TAG, "Registration Failed");
                    Log.d(TAG, "Error code : "+String.valueOf(registrationResult.getSyncStatus().getErrorCode()));
                    Log.d(TAG, "Error Msg : "+registrationResult.getSyncStatus().getErrorMsg());
                }
            }else if(arg instanceof RegisterDeviceResponse){
                try{
                    RegisterDeviceResponse registerDeviceResponse = (RegisterDeviceResponse)arg;
                    String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(registerDeviceResponse);
                    Log.d(TAG,"JSON Formatted Register Device Response: "+data);
                }catch(JsonProcessingException exc){
                    Log.e(TAG,"JSON Parse Exception.");
                    exc.printStackTrace();
                }
            }
        }catch(Exception exc){
            Log.e(TAG,"Error while processing update : "+exc.getMessage());
        }
    }
}