package com.kit.integrationmanager.service;

import com.kit.integrationmanager.event.DownloadProgressEvent;
import com.kit.integrationmanager.payload.download.request.BeneficiaryDownloadRequest;
import com.kit.integrationmanager.payload.download.request.PayrollRequest;
import com.kit.integrationmanager.payload.download.response.BeneficiaryDownloadResponse;

import org.reactivestreams.Subscriber;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;

public interface BeneficiaryDownloadService {
    Observable<BeneficiaryDownloadResponse> downloadBeneficiaryByBoma(
            BeneficiaryDownloadRequest downloadRequest,
            HashMap<String, String> headers
    );

    Observable<DownloadProgressEvent> observeDownloadProgress();
    void cancelDownload();
}
