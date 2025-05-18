package com.kit.integrationmanager.service;

import android.content.Context;
import android.net.Uri;

import com.kit.integrationmanager.event.DownloadProgressEvent;
import com.kit.integrationmanager.model.Payroll;
import com.kit.integrationmanager.payload.download.request.PayrollLockRequest;
import com.kit.integrationmanager.payload.download.request.PayrollRequest;
import com.kit.integrationmanager.payload.download.response.PayrollLockResponse;
import com.kit.integrationmanager.payload.download.response.PayrollResponse;

import org.reactivestreams.Subscriber;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;

public interface DownloadService {
    Observable<PayrollResponse> loadPayrol(PayrollRequest payrollRequest, HashMap<String, String> headers, final Subscriber<DownloadProgressEvent> observeProgress);
    Observable<PayrollResponse> loadPayrolFromFile(Context context, Uri payrolFile);
    Observable<PayrollLockResponse> lockPayrol(PayrollLockRequest payrollLockRequest, HashMap<String, String> headers);
    void cancelPayrollLoading();
}
