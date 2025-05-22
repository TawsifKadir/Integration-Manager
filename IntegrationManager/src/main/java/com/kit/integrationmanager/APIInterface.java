package com.kit.integrationmanager;


import com.kit.integrationmanager.model.Beneficiary;
import com.kit.integrationmanager.payload.BatchRegistrationRequest;
import com.kit.integrationmanager.payload.BatchRegistrationResponse;
import com.kit.integrationmanager.payload.BatchRegistrationResponseV2;
import com.kit.integrationmanager.payload.device.request.RegisterDeviceRequest;
import com.kit.integrationmanager.payload.device.response.RegisterDeviceResponse;
import com.kit.integrationmanager.payload.download.request.BeneficiaryDownloadRequest;
import com.kit.integrationmanager.payload.download.request.PayrollLockRequest;
import com.kit.integrationmanager.payload.download.response.BeneficiaryDownloadResponse;
import com.kit.integrationmanager.payload.download.response.PayrollLockResponse;
import com.kit.integrationmanager.payload.edit.request.BeneficiaryEditRequest;
import com.kit.integrationmanager.payload.edit.request.BeneficiaryEditStatusRequest;
import com.kit.integrationmanager.payload.edit.response.BeneficiaryEditResponse;
import com.kit.integrationmanager.payload.edit.response.BeneficiaryEditStatusResponse;
import com.kit.integrationmanager.payload.login.request.LoginRequest;
import com.kit.integrationmanager.payload.login.response.LoginResponse;
import com.kit.integrationmanager.payload.download.request.PayrollRequest;
import com.kit.integrationmanager.payload.download.response.PayrollResponse;
import com.kit.integrationmanager.payload.reconcile.request.PayrollReconcileBatchRequest;
import com.kit.integrationmanager.payload.reconcile.request.PayrollReconcileRequest;
import com.kit.integrationmanager.payload.reconcile.response.PayrollReconcileBatchResponse;
import com.kit.integrationmanager.payload.reconcile.response.PayrollReconcileResponse;
import com.kit.integrationmanager.payload.reset.request.ResetPassRequest;
import com.kit.integrationmanager.payload.reset.response.ResetPassResponse;
import com.kit.integrationmanager.payload.update.request.BeneficiaryUpdateStatusRequest;
import com.kit.integrationmanager.payload.update.request.UpdateFullBeneficiaryRequest;
import com.kit.integrationmanager.payload.update.response.BeneficiaryUpdateStatusResponse;
import com.kit.integrationmanager.payload.update.response.CheckUpdateResponse;
import com.kit.integrationmanager.payload.update.response.UpdateFullBeneficiaryResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @Headers({"READ_TIMEOUT:300", "WRITE_TIMEOUT:300"})
    @POST("/afis/api/beneficiary/register")
    Call<Void> register(@Body Beneficiary beneficiary, @HeaderMap Map<String, String> headers);

    @Headers({"READ_TIMEOUT:300", "WRITE_TIMEOUT:300"})
    @POST("/afis/api/beneficiary/register/batch")
    Call<BatchRegistrationResponse> registerBatch(@Body BatchRegistrationRequest beneficiaryRequest, @HeaderMap Map<String, String> headers);

    @Headers({"READ_TIMEOUT:300", "WRITE_TIMEOUT:300"})
    @POST("/afis/api/beneficiary/register/batch")
    Call<List<BatchRegistrationResponseV2>> registerBatchV2(@Body BatchRegistrationRequest beneficiaryRequest, @HeaderMap Map<String, String> headers);

    @Headers({"READ_TIMEOUT:300", "WRITE_TIMEOUT:300"})
    @POST("/afis/api/beneficiary/update/batch")
    Call<BeneficiaryEditResponse> beneficiaryEditBatch(@Body BeneficiaryEditRequest beneficiaryEditRequest, @HeaderMap Map<String, String> headers);

    @Headers({"READ_TIMEOUT:300", "WRITE_TIMEOUT:300"})
    @POST("/afis/api/beneficiary/update-status")
    Call<List<BeneficiaryEditStatusResponse>> getBeneficiaryEditStatus(@Body BeneficiaryEditStatusRequest beneficiaryEditStatusRequestList, @HeaderMap Map<String, String> headers);

    @GET("afis/api/beneficiary/getBeneficiary/{bomaId}")
    @Headers({"READ_TIMEOUT:300", "WRITE_TIMEOUT:300"})
    Call<BeneficiaryDownloadResponse> getBeneficiaries(@Path("bomaId") String bomaId, @Query("page") int page, @Query("size") int size, @HeaderMap Map<String, String> headers);

    @POST("/afis/api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest, @HeaderMap Map<String, String> headers);

    @POST("/afis/api/user/resetPassword")
    Call<ResetPassResponse> resetPassword(@Body ResetPassRequest resetPassRequest, @HeaderMap Map<String, String> headers);

    @POST("/afis/api/device/register")
    Call<RegisterDeviceResponse> registerDevice(@Body RegisterDeviceRequest registerDeviceRequest, @HeaderMap Map<String, String> headers);

    @GET("/afis/api/common/apk-update/{major}/{minor}/{patch}")
    Call<CheckUpdateResponse> checkUpdate(@Path("major") long major,@Path("minor") long minor,@Path("patch") long patch);

    @Headers({"READ_TIMEOUT:300", "WRITE_TIMEOUT:300"})
    @POST("/afis/api/payroll/getPayroll")
    Call<PayrollResponse> loadPayroll(@Body PayrollRequest payrollRequest, @HeaderMap Map<String, String> headers);

    @POST("/afis/api/payroll/lockPayroll")
    Call<PayrollLockResponse> lockPayroll(@Body PayrollLockRequest payrollLockRequest, @HeaderMap Map<String, String> headers);

    @Headers({"READ_TIMEOUT:300","WRITE_TIMEOUT:300"})
    @POST("/afis/api/payroll/reconciliation/save")
    Call<PayrollReconcileResponse> reconcilePayroll(@Body PayrollReconcileRequest payrollReconRequest, @HeaderMap Map<String, String> headers);
    @Headers({"READ_TIMEOUT:300","WRITE_TIMEOUT:300"})
    @POST("/afis/api/payroll/reconciliation/save/batch")
    Call<PayrollReconcileBatchResponse> reconcilePayrollBatch(@Body PayrollReconcileBatchRequest payrollReconBatchRequest, @HeaderMap Map<String, String> headers);
    @Headers({"READ_TIMEOUT:300", "WRITE_TIMEOUT:300"})
    @POST("/afis/api/beneficiary/update-full-beneficiary")
    Call<UpdateFullBeneficiaryResponse> updateFullBeneficiary(@Body UpdateFullBeneficiaryRequest updateFullBeneficiaryRequest, @HeaderMap Map<String, String> headers);
    @Headers({"READ_TIMEOUT:300", "WRITE_TIMEOUT:300"})
    @POST("/afis/api/beneficiary/update-status")
    Call<List<BeneficiaryUpdateStatusResponse>> getBeneficiaryUpdateStatus(@Body BeneficiaryUpdateStatusRequest updateFullBeneficiaryRequest, @HeaderMap Map<String, String> headers);
}
