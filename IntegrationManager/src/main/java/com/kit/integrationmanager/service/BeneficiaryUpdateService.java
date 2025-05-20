package com.kit.integrationmanager.service;

import com.kit.integrationmanager.event.DownloadProgressEvent;
import com.kit.integrationmanager.payload.update.request.UpdateFullBeneficiaryRequest;
import com.kit.integrationmanager.payload.update.response.UpdateFullBeneficiaryResponse;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;

public interface BeneficiaryUpdateService {
    Observable<UpdateFullBeneficiaryResponse> updateFullBeneficiary(
            UpdateFullBeneficiaryRequest updateRequest,
            HashMap<String, String> headers
    );

    Observable<DownloadProgressEvent> observeUpdateProgress();
    void cancelUpdate();
}