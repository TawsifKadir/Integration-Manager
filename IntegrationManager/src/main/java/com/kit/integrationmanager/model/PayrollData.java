/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kit.integrationmanager.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author anwar
 */
@Data
@Getter
@Setter
public class PayrollData {
    @SerializedName("intWagePaymentReqid")
    @JsonProperty("intWagePaymentReqid")
    private Integer intWagePaymentReqid;
    @JsonProperty("reqFromDate")
    @SerializedName("reqFromDate")
    private String reqFromDate;
    @JsonProperty("reqToDate")
    @SerializedName("reqToDate")
    private String reqToDate;
    @JsonProperty("exchangerateattime")
    @SerializedName("exchangerateattime")
    private Double exchangerateattime;
    @JsonProperty("wageRateUSD")
    @SerializedName("wageRateUSD")
    private Double wageRateUSD;
    @JsonProperty("reqNo")
    @SerializedName("reqNo")
    private String reqNo;
    @JsonProperty("intWagesReqBenTransid")
    @SerializedName("intWagesReqBenTransid")
    private Integer intWagesReqBenTransid;
    @JsonProperty("intBenid")
    @SerializedName("intBenid")
    private Integer intBenid;
    @JsonProperty("enrollmentNo")
    @SerializedName("enrollmentNo")
    private String enrollmentNo;
    @JsonProperty("intWorkid")
    @SerializedName("intWorkid")
    private Integer intWorkid;
    @JsonProperty("workCode")
    @SerializedName("workCode")
    private String workCode;
    @SerializedName("totAttendance")
    @JsonProperty("totAttendance")
    private Integer totAttendance;
    @JsonProperty("wageRateUSDTotal")
    @SerializedName("wageRateUSDTotal")
    private Double wageRateUSDTotal;
    @JsonProperty("supportType")
    @SerializedName("supportType")
    private String supportType;
    @JsonProperty("dateofCreation")
    @SerializedName("dateofCreation")
    private String dateofCreation;
    @JsonProperty("status")
    @SerializedName("status")
    private String status;
    @JsonProperty("paymentCycle")
    @SerializedName("paymentCycle")
    private String paymentCycle;
    @JsonProperty("wageRateSSPTotal")
    @SerializedName("wageRateSSPTotal")
    private Double wageRateSSPTotal;
    @JsonProperty("houseHoldDetail")
    @SerializedName("houseHoldDetail")
    private PayrollHouseHold houseHoldDetail;
    @JsonProperty("alternates")
    @SerializedName("alternates")
    private List<PayrollAlternate> alternates;
}
