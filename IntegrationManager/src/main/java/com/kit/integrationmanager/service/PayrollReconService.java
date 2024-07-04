package com.kit.integrationmanager.service;


import com.kit.integrationmanager.payload.reconcile.request.PayrollReconcileBatchRequest;
import com.kit.integrationmanager.payload.reconcile.request.PayrollReconcileRequest;
import com.kit.integrationmanager.payload.reconcile.response.PayrollReconcileBatchResponse;
import com.kit.integrationmanager.payload.reconcile.response.PayrollReconcileResponse;

import org.reactivestreams.Subscriber;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;

public interface PayrollReconService {
    public Observable<PayrollReconcileResponse> reconcilePayroll(PayrollReconcileRequest payrollReconRequest, HashMap<String,String> headers);
    public Observable<PayrollReconcileBatchResponse> reconcilePayrollBatch(PayrollReconcileBatchRequest payrollReconBatchRequest, HashMap<String,String> headers);
    public void cancelPayrollReconciliation();
}
