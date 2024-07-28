package com.kit.integrationmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.kit.databasemanager.model.PayrollDataEO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PayrollReconcile {
    @JsonProperty("id")
    @SerializedName("id")
    private Integer id;

    @JsonProperty("supervisor_id")
    @SerializedName("supervisor_id")
    private String supervisorId;

    @JsonProperty("household_number")
    @SerializedName("household_number")
    private String householdNumber;

    @JsonProperty("paid_to")
    @SerializedName("paid_to")
    private String paidTo;

    @JsonProperty("enrolment_number")
    @SerializedName("enrolment_number")
    private String enrolmentNumber;

    @JsonProperty("date_from")
    @SerializedName("date_from")
    private String dateFrom;

    @JsonProperty("date_to")
    @SerializedName("date_to")
    private String dateTo;

    @JsonProperty("exchange_rate")
    @SerializedName("exchange_rate")
    private String exchangeRate;

    @JsonProperty("wage")
    @SerializedName("wage")
    private String wage;

    @JsonProperty("amount")
    @SerializedName("amount")
    private String amount;

    @JsonProperty("work_id")
    @SerializedName("work_id")
    private String workId;

    @JsonProperty("work_code")
    @SerializedName("work_code")
    private String workCode;

    @JsonProperty("attendance")
    @SerializedName("attendance")
    private String attendance;

    @JsonProperty("first_name")
    @SerializedName("first_name")
    private String firstName;

    @JsonProperty("last_name")
    @SerializedName("last_name")
    private String lastName;

    @JsonProperty("surname")
    @SerializedName("surname")
    private String surname;

    @JsonProperty("trans_id")
    @SerializedName("trans_id")
    private String transId;

    @JsonProperty("uuid")
    @SerializedName("uuid")
    private String uuid;

    @JsonProperty("status")
    @SerializedName("status")
    private String status;

    @JsonProperty("generated_at")
    @SerializedName("generated_at")
    private String generatedAt;

    @JsonProperty("created_at")
    @SerializedName("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    @SerializedName("updated_at")
    private String updatedAt;

    @JsonProperty("latitude")
    @SerializedName("latitude")
    private String latitude;

    @JsonProperty("longitude")
    @SerializedName("longitude")
    private String longitude;

    public static PayrollReconcile mapFromEO(PayrollDataEO payrollDataEO) {
        PayrollReconcile dto = new PayrollReconcile();

        dto.setId(payrollDataEO.dataId);
        dto.setSupervisorId("");
        dto.setHouseholdNumber(payrollDataEO.benUUID);
        dto.setPaidTo(payrollDataEO.paidToName);
        dto.setEnrolmentNumber(payrollDataEO.enrollmentNo);
        dto.setDateFrom(payrollDataEO.reqFromDate);
        dto.setDateTo(payrollDataEO.reqToDate);
        dto.setExchangeRate(String.valueOf(payrollDataEO.exchangerateattime));
        dto.setWage(String.valueOf(payrollDataEO.wageRateSSPTotal));
        dto.setAmount(String.valueOf(payrollDataEO.wageRateSSPTotal));
        dto.setWorkId(String.valueOf(payrollDataEO.intWorkid));
        dto.setWorkCode(payrollDataEO.workCode);
        dto.setAttendance(String.valueOf(payrollDataEO.totAttendance));
        dto.setFirstName(payrollDataEO.benFirstName);
        dto.setLastName(payrollDataEO.benLastName);
        dto.setSurname(payrollDataEO.benMiddleName);
        dto.setTransId(String.valueOf(payrollDataEO.intWagesReqBenTransid));
        dto.setUuid(String.valueOf(payrollDataEO.intWagePaymentReqid));
        dto.setStatus(payrollDataEO.status);
        dto.setGeneratedAt(payrollDataEO.getGeneratedAt());
        dto.setCreatedAt(payrollDataEO.dateofCreation);
        dto.setUpdatedAt(payrollDataEO.getUpdatedAt());
        dto.setLatitude(payrollDataEO.getLatitude());
        dto.setLongitude(payrollDataEO.getLongitude());

        return dto;
    }
}
