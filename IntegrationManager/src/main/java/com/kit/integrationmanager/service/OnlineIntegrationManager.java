package com.kit.integrationmanager.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.kit.integrationmanager.APIClient;
import com.kit.integrationmanager.APIInterface;
import com.kit.integrationmanager.model.Beneficiary;
import com.kit.integrationmanager.model.ServerInfo;
import com.kit.integrationmanager.payload.BatchRegistrationRequest;
import com.kit.integrationmanager.payload.BatchRegistrationResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

public class OnlineIntegrationManager implements IntegrationManager {

    private final Context context;
    private final ServerInfo serverInfo;
    private APIInterface apiInterface;
    private static final String TAG = "OnlineIntegrationManager";

    @SuppressLint("LongLogTag")
    public OnlineIntegrationManager(Context context, ServerInfo serverInfo) {
        this.context = context.getApplicationContext();
        this.serverInfo = serverInfo;
        try {
            this.apiInterface = APIClient.getInstance()
                    .setServerInfo(serverInfo)
                    .getRetrofit()
                    .create(APIInterface.class);
        } catch (Exception e) {
            Log.e(TAG, "Failed to create API interface", e);
        }
    }

    @SuppressLint({"CheckResult", "LongLogTag"})
    @Override
    public void syncRecords(List<Beneficiary> beneficiaries, HashMap<String, String> headers) throws Exception {
        if (apiInterface == null) {
            throw new IllegalStateException("API Interface not initialized");
        }

        Observable.fromCallable(() -> {
                    if (!DeviceManager.getInstance(context).isOnline()) {
                        throw new NetworkException("The device is not connected");
                    }
                    if (beneficiaries == null || beneficiaries.isEmpty()) {
                        throw new IllegalArgumentException("Empty beneficiary list provided");
                    }
                    return BatchRegistrationRequest.builder()
                            .beneficiaries(beneficiaries)
                            .build();
                })
                .flatMap(request -> convertCallToObservable(apiInterface.registerBatch(request, headers)))
                .subscribeOn(Schedulers.io())
                .subscribe(
                        response -> {
                            if (response.isSuccessful()) {
                                Log.d(TAG, "Batch sync successful");
                            } else {
                                Log.e(TAG, "Batch sync failed: " + response.code());
                            }
                        },
                        throwable -> Log.e(TAG, "Batch sync failed", throwable)
                );
    }

    @SuppressLint({"CheckResult", "LongLogTag"})
    @Override
    public void syncRecord(Beneficiary beneficiary, HashMap<String, String> headers) throws Exception {
        if (apiInterface == null) {
            throw new IllegalStateException("API Interface not initialized");
        }

        Single.fromCallable(() -> {
                    if (!DeviceManager.getInstance(context).isOnline()) {
                        throw new NetworkException("The device is not connected");
                    }
                    if (beneficiary == null) {
                        throw new IllegalArgumentException("Beneficiary record is null");
                    }
                    return beneficiary;
                })
                .flatMap(benef -> convertCallToSingle(apiInterface.register(benef, headers)))
                .subscribeOn(Schedulers.io())
                .subscribe(
                        response -> {
                            if (response.isSuccessful()) {
                                Log.d(TAG, "Single sync successful");
                            } else {
                                Log.e(TAG, "Single sync failed: " + response.code());
                            }
                        },
                        throwable -> Log.e(TAG, "Single sync failed", throwable)
                );
    }

    // Helper method to convert Call to Observable
    private Observable<Response<BatchRegistrationResponse>> convertCallToObservable(Call<BatchRegistrationResponse> call) {
        return Observable.create(emitter -> {
            try {
                Response<BatchRegistrationResponse> response = call.execute();
                if (!emitter.isDisposed()) {
                    emitter.onNext(response);
                    emitter.onComplete();
                }
            } catch (Exception e) {
                if (!emitter.isDisposed()) {
                    emitter.onError(e);
                }
            }
        });
    }

    // Helper method to convert Call to Single
    private Single<Response<Void>> convertCallToSingle(Call<Void> call) {
        return Single.create(emitter -> {
            try {
                Response<Void> response = call.execute();
                if (!emitter.isDisposed()) {
                    emitter.onSuccess(response);
                }
            } catch (Exception e) {
                if (!emitter.isDisposed()) {
                    emitter.onError(e);
                }
            }
        });
    }

    private static class NetworkException extends Exception {
        public NetworkException(String message) {
            super(message);
        }
    }
}