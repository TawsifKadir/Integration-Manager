package com.kit.integrationmanager.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;

import com.kit.integrationmanager.APIClient;
import com.kit.integrationmanager.APIInterface;
import com.kit.integrationmanager.event.SyncProgressEvent;
import com.kit.integrationmanager.event.bus.SimpleEventBus;
import com.kit.integrationmanager.interceptor.SyncProgressInterceptor;
import com.kit.integrationmanager.model.Beneficiary;
import com.kit.integrationmanager.model.ServerInfo;
import com.kit.integrationmanager.payload.BatchRegistrationRequest;
import com.kit.integrationmanager.payload.BatchRegistrationResponse;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import io.reactivex.rxjava3.core.Observable;

public class OnlineIntegrationManager implements IntegrationManager {
    private static final String TAG = "OnlineIntegrationManager";
    private final Context context;
    private final ServerInfo serverInfo;
    private Call<BatchRegistrationResponse> activeBatchCall;
    private Call<Void> activeSingleCall;

    public OnlineIntegrationManager(Context context, ServerInfo serverInfo) {
        this.context = context.getApplicationContext();
        this.serverInfo = serverInfo;
    }

    @Override
    public Observable<BatchRegistrationResponse> syncRecords(
            List<Beneficiary> beneficiaries,
            HashMap<String, String> headers
    ) {
        return Observable.create(emitter -> {
            // Check if we're on UI thread
            boolean isUiThread = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    ? Looper.getMainLooper().isCurrentThread()
                    : Thread.currentThread() == Looper.getMainLooper().getThread();

            if (isUiThread) {
                emitter.onError(new IllegalStateException("Network operations must be performed off the UI thread"));
                return;
            }

            if (!DeviceManager.getInstance(context).isOnline()) {
                emitter.onError(new NetworkException("The device is not connected"));
                return;
            }

            if (!isValidBatchRequest(beneficiaries)) {
                emitter.onError(new IllegalArgumentException("Invalid beneficiary list provided"));
                return;
            }

            try {
                // Add sync identifier header for progress tracking
                headers.put(SyncProgressInterceptor.SYNC_IDENTIFIER_HEADER, "batch-sync");

                APIInterface apiInterface = APIClient.getInstance()
                        .setServerInfo(serverInfo)
                        .getRetrofit()
                        .create(APIInterface.class);

                BatchRegistrationRequest request = BatchRegistrationRequest.builder()
                        .beneficiaries(beneficiaries)
                        .build();

                this.activeBatchCall = apiInterface.registerBatch(request, headers);
                Response<BatchRegistrationResponse> response = this.activeBatchCall.execute();

                if (response.isSuccessful()) {
                    BatchRegistrationResponse body = response.body();
                    if (body != null) {
                        emitter.onNext(body);
                        emitter.onComplete();
                    } else {
                        emitter.onError(new RuntimeException("Empty response body"));
                    }
                } else {
                    String errorMsg = response.errorBody() != null ?
                            response.errorBody().string() : "Server error: " + response.code();
                    emitter.onError(new RuntimeException(errorMsg));
                }
            } catch (Exception e) {
                emitter.onError(e);
            } finally {
                this.activeBatchCall = null;
            }
        });
    }

    @Override
    public Observable<Void> syncRecord(
            Beneficiary beneficiary,
            HashMap<String, String> headers
    ) {
        return Observable.create(emitter -> {
            // Check if we're on UI thread
            boolean isUiThread = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    ? Looper.getMainLooper().isCurrentThread()
                    : Thread.currentThread() == Looper.getMainLooper().getThread();

            if (isUiThread) {
                emitter.onError(new IllegalStateException("Network operations must be performed off the UI thread"));
                return;
            }

            if (!DeviceManager.getInstance(context).isOnline()) {
                emitter.onError(new NetworkException("The device is not connected"));
                return;
            }

            if (!isValidSingleRequest(beneficiary)) {
                emitter.onError(new IllegalArgumentException("Invalid beneficiary record"));
                return;
            }

            try {
                // Add sync identifier header for progress tracking
                headers.put(SyncProgressInterceptor.SYNC_IDENTIFIER_HEADER, "single-sync");

                APIInterface apiInterface = APIClient.getInstance()
                        .setServerInfo(serverInfo)
                        .getRetrofit()
                        .create(APIInterface.class);

                this.activeSingleCall = apiInterface.register(beneficiary, headers);
                Response<Void> response = this.activeSingleCall.execute();

                if (response.isSuccessful()) {
                    emitter.onComplete();
                } else {
                    String errorMsg = response.errorBody() != null ?
                            response.errorBody().string() : "Server error: " + response.code();
                    emitter.onError(new RuntimeException(errorMsg));
                }
            } catch (Exception e) {
                emitter.onError(e);
            } finally {
                this.activeSingleCall = null;
            }
        });
    }

    @Override
    public Observable<SyncProgressEvent> observeSyncProgress() {
        return SimpleEventBus.getInstance()
                .filteredObservable(SyncProgressEvent.class)
                .filter(event -> "batch-sync".equals(event.getSyncIdentifier()) ||
                        "single-sync".equals(event.getSyncIdentifier()));
    }

    @SuppressLint("LongLogTag")
    @Override
    public void cancelSync() {
        if (activeBatchCall != null && !activeBatchCall.isCanceled()) {
            activeBatchCall.cancel();
            Log.d(TAG, "Batch sync cancelled");
        }
        if (activeSingleCall != null && !activeSingleCall.isCanceled()) {
            activeSingleCall.cancel();
            Log.d(TAG, "Single sync cancelled");
        }
    }

    private boolean isValidBatchRequest(List<Beneficiary> beneficiaries) {
        return beneficiaries != null && !beneficiaries.isEmpty();
    }

    private boolean isValidSingleRequest(Beneficiary beneficiary) {
        return beneficiary != null;
    }

    private static class NetworkException extends Exception {
        public NetworkException(String message) {
            super(message);
        }
    }
}