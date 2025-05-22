package com.kit.integrationmanager.service;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Looper;
import android.util.Log;

import com.kit.integrationmanager.APIClient;
import com.kit.integrationmanager.APIInterface;
import com.kit.integrationmanager.event.DownloadProgressEvent;
import com.kit.integrationmanager.event.bus.SimpleEventBus;
import com.kit.integrationmanager.interceptor.DownloadProgressInterceptor;
import com.kit.integrationmanager.model.ServerInfo;
import com.kit.integrationmanager.payload.update.BeneficiaryUpdateRequestBody;
import com.kit.integrationmanager.payload.update.request.BeneficiaryUpdateStatusRequest;
import com.kit.integrationmanager.payload.update.request.UpdateFullBeneficiaryRequest;
import com.kit.integrationmanager.payload.update.response.BeneficiaryUpdateStatusResponse;
import com.kit.integrationmanager.payload.update.response.UpdateFullBeneficiaryResponse;

import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.core.ObservableEmitter;
import retrofit2.Call;
import retrofit2.Response;

import io.reactivex.rxjava3.core.Observable;

public class BeneficiaryUpdateServiceImpl implements BeneficiaryUpdateService {
    private static final String TAG = "BeneficiaryUpdateService";
    private final ServerInfo serverInfo;
    private Call<UpdateFullBeneficiaryResponse> activeCall;

    public BeneficiaryUpdateServiceImpl(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    @Override
    public Observable<UpdateFullBeneficiaryResponse> updateFullBeneficiary(
            UpdateFullBeneficiaryRequest updateRequest,
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

            if (!isValidRequest(updateRequest)) {
                emitter.onError(new IllegalArgumentException("Invalid beneficiary update request"));
                return;
            }

            try {
                // Add update identifier header for progress tracking
                headers.put(DownloadProgressInterceptor.DOWNLOAD_IDENTIFIER_HEADER, "beneficiary-update");

                APIInterface apiInterface = APIClient.getInstance()
                        .setServerInfo(serverInfo)
                        .getRetrofit()
                        .create(APIInterface.class);

                this.activeCall = apiInterface.updateFullBeneficiary(updateRequest, headers);
                Response<UpdateFullBeneficiaryResponse> response = this.activeCall.execute();

                if (response.isSuccessful()) {
                    UpdateFullBeneficiaryResponse body = response.body();
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
                this.activeCall = null;
            }
        });
    }

    @Override
    public Observable<DownloadProgressEvent> observeUpdateProgress() {
        return SimpleEventBus.getInstance()
                .filteredObservable(DownloadProgressEvent.class)
                .filter(event -> "beneficiary-update".equals(event.getDownloadIdentifier()));
    }

    @Override
    public Observable<List<BeneficiaryUpdateStatusResponse>> getBeneficiaryUpdateStatus(
            BeneficiaryUpdateStatusRequest statusRequest,
            HashMap<String, String> headers
    ) {
        return Observable.create(emitter -> {
            // Check UI thread
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    ? Looper.getMainLooper().isCurrentThread()
                    : Thread.currentThread() == Looper.getMainLooper().getThread()) {
                emitter.onError(new IllegalStateException("Network operations must be performed off the UI thread"));
                return;
            }

            if (!isValidStatusRequest(statusRequest)) {
                emitter.onError(new IllegalArgumentException("Invalid beneficiary status request"));
                return;
            }

            try {
                headers.put(DownloadProgressInterceptor.DOWNLOAD_IDENTIFIER_HEADER, "beneficiary-status-check");

                APIInterface apiInterface = APIClient.getInstance()
                        .setServerInfo(serverInfo)
                        .getRetrofit()
                        .create(APIInterface.class);

                Call<List<BeneficiaryUpdateStatusResponse>> call =
                        apiInterface.getBeneficiaryUpdateStatus(statusRequest, headers);

                Response<List<BeneficiaryUpdateStatusResponse>> response = call.execute();

                if (response.isSuccessful()) {
                    List<BeneficiaryUpdateStatusResponse> responseList = response.body();
                    if (responseList != null) {
                        emitter.onNext(responseList); // Emit the entire list
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
            }
        });
    }

    private void handleStatusResponse(
            Response<List<BeneficiaryUpdateStatusResponse>> response,
            ObservableEmitter<List<BeneficiaryUpdateStatusResponse>> emitter
    ) throws Exception {
        if (response.isSuccessful()) {
            List<BeneficiaryUpdateStatusResponse> body = response.body();
            if (body != null) {
                emitter.onNext(body);
                emitter.onComplete();
            } else {
                emitter.onError(new RuntimeException("Empty response body"));
            }
        } else {
            String errorMsg = response.errorBody() != null
                    ? response.errorBody().string()
                    : "Server error: " + response.code();
            emitter.onError(new RuntimeException(errorMsg));
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    public void cancelUpdate() {
        if (activeCall != null && !activeCall.isCanceled()) {
            activeCall.cancel();
            Log.d(TAG, "Beneficiary update cancelled");
        }
    }

    private boolean isValidStatusRequest(BeneficiaryUpdateStatusRequest requests) {
        if (requests != null){
            for(BeneficiaryUpdateRequestBody request : requests.getRequests()){
                if (request.getApplicationId() == null || request.getApplicationId().isEmpty()) return false;
                if (request.getRequestId() == null || request.getRequestId().isEmpty()) return false;
            }
            return true;
        }
        return false;
    }

    private boolean isValidRequest(UpdateFullBeneficiaryRequest request) {
        if (request == null) return false;
        if (request.getBeneficiaries() == null || request.getBeneficiaries().isEmpty()) return false;
        return true;
    }
}