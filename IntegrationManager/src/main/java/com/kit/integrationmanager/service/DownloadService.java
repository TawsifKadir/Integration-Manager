package com.kit.integrationmanager.service;

import android.content.Context;
import android.net.Uri;

import com.kit.integrationmanager.event.DownloadProgressEvent;
import com.kit.integrationmanager.model.Payroll;
import com.kit.integrationmanager.payload.download.request.PayrollRequest;
import com.kit.integrationmanager.payload.download.response.PayrollResponse;

import org.reactivestreams.Subscriber;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;

public interface DownloadService {
    public Observable<PayrollResponse> loadPayrol(PayrollRequest payrollRequest, HashMap<String,String> headers, final Subscriber<DownloadProgressEvent> observeProgress);
    public Observable<PayrollResponse> loadPayrolFromFile(Context context, Uri payrolFile);
    public void cancelPayrollLoading();
}
