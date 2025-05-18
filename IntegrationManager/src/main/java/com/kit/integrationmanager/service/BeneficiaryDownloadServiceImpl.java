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
import com.kit.integrationmanager.payload.download.request.BeneficiaryDownloadRequest;
import com.kit.integrationmanager.payload.download.response.BeneficiaryDownloadResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;

import io.reactivex.rxjava3.core.Observable;

public class BeneficiaryDownloadServiceImpl implements BeneficiaryDownloadService {
    private static final String TAG = "BeneficiaryDownloadService";
    private final ServerInfo serverInfo;
    private Call<BeneficiaryDownloadResponse> activeCall;

    public BeneficiaryDownloadServiceImpl(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    @Override
    public Observable<BeneficiaryDownloadResponse> downloadBeneficiaryByBoma(
            BeneficiaryDownloadRequest downloadRequest,
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

            if (!isValidRequest(downloadRequest)) {
                emitter.onError(new IllegalArgumentException("Invalid beneficiary download request"));
                return;
            }

            try {
                // Add download identifier header for progress tracking
                headers.put(DownloadProgressInterceptor.DOWNLOAD_IDENTIFIER_HEADER, "beneficiary-download");

                APIInterface apiInterface = APIClient.getInstance()
                        .setServerInfo(serverInfo)
                        .getRetrofit()
                        .create(APIInterface.class);

                this.activeCall = apiInterface.getBeneficiaries(String.valueOf(downloadRequest.getBomaId()), downloadRequest.getPage(), downloadRequest.getSize(), headers);
                Response<BeneficiaryDownloadResponse> response = this.activeCall.execute();

                if (response.isSuccessful()) {
                    BeneficiaryDownloadResponse body = response.body();
                    if (body != null && body.isOperationResult()) {
                        emitter.onNext(body);
                        emitter.onComplete();
                    } else {
                        String errorMsg = body != null ? body.getErrorMsg() : "Unknown error occurred";
                        emitter.onError(new RuntimeException(errorMsg));
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
    public Observable<DownloadProgressEvent> observeDownloadProgress() {
        return SimpleEventBus.getInstance()
                .filteredObservable(DownloadProgressEvent.class)
                .filter(event -> "beneficiary-download".equals(event.getDownloadIdentifier()));
    }

    @SuppressLint("LongLogTag")
    @Override
    public void cancelDownload() {
        if (activeCall != null && !activeCall.isCanceled()) {
            activeCall.cancel();
            Log.d(TAG, "Beneficiary download cancelled");
        }
    }

    private boolean isValidRequest(BeneficiaryDownloadRequest request) {
        if (request == null) return false;
        if (request.getSize() == 0 || request.getSize() < -1) return false;
        return request.getPage() >= 0;
    }
}