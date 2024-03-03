package com.kit.integrationmanager.service;

import com.kit.integrationmanager.model.Beneficiary;

import java.util.HashMap;
import java.util.List;

public interface IntegrationManager {
    void syncRecords(List<Beneficiary> beneficiaries, HashMap<String,String> headers) throws Exception;
    void syncRecord(Beneficiary beneficiary,HashMap<String,String> headers) throws Exception;
}
