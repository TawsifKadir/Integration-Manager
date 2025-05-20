package com.kit.integrationmanager.service;

import com.kit.integrationmanager.event.SyncProgressEvent;
import com.kit.integrationmanager.model.Beneficiary;
import com.kit.integrationmanager.payload.BatchRegistrationResponse;

import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface IntegrationManager {
    Observable<BatchRegistrationResponse> syncRecords(List<Beneficiary> beneficiaries, HashMap<String,String> headers) throws Exception;
    Observable<Void> syncRecord(Beneficiary beneficiary, HashMap<String,String> headers) throws Exception;
    Observable<SyncProgressEvent> observeSyncProgress();
    void cancelSync();
}
