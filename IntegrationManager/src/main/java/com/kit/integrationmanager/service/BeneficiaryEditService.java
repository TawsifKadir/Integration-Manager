package com.kit.integrationmanager.service;

import com.kit.integrationmanager.model.Beneficiary;
import com.kit.integrationmanager.model.BeneficiaryEdit;
import com.kit.integrationmanager.payload.edit.request.BeneficiaryEditRequest;
import com.kit.integrationmanager.payload.edit.response.BeneficiaryEditResponse;

import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface BeneficiaryEditService {
    BeneficiaryEditResponse editBeneficiary(List<BeneficiaryEdit> beneficiaryEditList, HashMap<String,String> headers) throws Exception;
    Observable<BeneficiaryEditResponse> editBeneficiaryAsync(List<BeneficiaryEdit> beneficiaryEditList, HashMap<String,String> headers , int partitionSize) throws Exception;

    void cancelBeneficiaryEdit();
}
