/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kit.databasemanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kit.DateUtils;
import com.kit.integrationmanager.model.PayrollAlternate;
import com.kit.integrationmanager.model.PayrollData;
import com.kit.integrationmanager.model.PayrollHouseHold;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "payroll_data")
public class PayrollDataEO {
    @PrimaryKey
    public int dataId;
    public int payrollId;
    public String benUUID;
    public String benFirstName;
    public String benMiddleName;
    public String benLastName;
    public String benNickName;
    public Integer benAge;
    public Integer benGender;
    public String benPhoneNumber;
    public int benBiometricId;

    public Integer intWagePaymentReqid;
    public String reqFromDate;
    public String reqToDate;
    public Double exchangerateattime;
    public Double wageRateUSD;
    public String reqNo;
    public Integer intWagesReqBenTransid;
    public Integer intBenid;
    public String enrollmentNo;
    public Integer intWorkid;
    public String workCode;
    public Integer totAttendance;
    public Double wageRateUSDTotal;
    public String dateofCreation;
    public String status;
    public String paymentCycle;
    public Double wageRateSSPTotal;
    public String supportType;

    public Integer alternateCount;

    public Integer firstAltID;
    public String firstAltFirstName;
    public String firstAltMiddleName;
    public String firstAltLastName;
    public String firstAltNickName;
    public Integer firstAltAge;
    public Integer firstAltGender;
    public String firstAltPhoneNumber;
    public String firstAltNationalId;
    public int firstAltBiometricId;

    public Integer secondAltID;
    public String secondAltFirstName;
    public String secondAltMiddleName;
    public String secondAltLastName;
    public String secondAltNickName;
    public Integer secondAltAge;
    public Integer secondAltGender;
    public String secondAltPhoneNumber;
    public String secondAltNationalId;
    public int secondAltBiometricId;

    public String paidToName;
    public String generatedAt;
    public String updatedAt;
    public String latitude;
    public String longitude;
    public String paymentCenter;


    public void mapFromBO(PayrollData payrollData,
                          PayrollHouseHold payrollHouseHold,
                          List<PayrollAlternate> alternates
                         ){

        this.intWagePaymentReqid = payrollData.getIntWagePaymentReqid();
        this.reqFromDate = payrollData.getReqFromDate();
        this.reqToDate = payrollData.getReqToDate();
        this.exchangerateattime = payrollData.getExchangerateattime();
        this.wageRateUSD = payrollData.getWageRateUSD();
        this.reqNo = payrollData.getReqNo();
        this.intWagesReqBenTransid = payrollData.getIntWagesReqBenTransid();
        this.intBenid = payrollData.getIntBenid();
        this.enrollmentNo = payrollData.getEnrollmentNo();
        this.intWorkid = payrollData.getIntWorkid();
        this.workCode = payrollData.getWorkCode();
        this.totAttendance = payrollData.getTotAttendance();
        this.wageRateUSDTotal = payrollData.getWageRateUSDTotal();
        this.dateofCreation = payrollData.getDateofCreation();
        this.status = payrollData.getStatus();
        this.paymentCycle = payrollData.getPaymentCycle();
        this.wageRateSSPTotal = payrollData.getWageRateSSPTotal();
        this.supportType = payrollData.getSupportType();

        this.benFirstName = payrollHouseHold.getFirstName();
        this.benMiddleName = payrollHouseHold.getMiddleName();
        this.benLastName = payrollHouseHold.getLastName();
        this.benNickName = payrollHouseHold.getNickName();
        this.benAge = payrollHouseHold.getAge();
        this.benGender = payrollHouseHold.getGender();
        this.benUUID = payrollHouseHold.getHouseHoldNumber();

        this.alternateCount = (alternates==null)?0:alternates.size();

        if(this.alternateCount>0) {
            PayrollAlternate firstAlternate = alternates.get(0);
            this.firstAltID = firstAlternate.getAlternateId();
            this.firstAltFirstName = firstAlternate.getFirstName();
            this.firstAltMiddleName = firstAlternate.getMiddleName();
            this.firstAltLastName = firstAlternate.getLastName();
            this.firstAltNickName = firstAlternate.getNickName();
            this.firstAltAge = firstAlternate.getAge();
            this.firstAltGender = firstAlternate.getGender();
            this.firstAltNationalId = firstAlternate.getNationalId();
            this.firstAltPhoneNumber = firstAlternate.getPhoneNumber();
        }
        if(this.alternateCount>1) {
            PayrollAlternate secondAlternate = alternates.get(1);

            this.secondAltID = secondAlternate.getAlternateId();
            this.secondAltFirstName = secondAlternate.getFirstName();
            this.secondAltMiddleName = secondAlternate.getMiddleName();
            this.secondAltLastName = secondAlternate.getLastName();
            this.secondAltNickName = secondAlternate.getNickName();
            this.secondAltAge = secondAlternate.getAge();
            this.secondAltGender = secondAlternate.getGender();
            this.secondAltNationalId = secondAlternate.getNationalId();
            this.secondAltPhoneNumber = secondAlternate.getPhoneNumber();
        }

        this.paidToName = "NOT_SET";
        this.latitude = "0.0";
        this.longitude = "0.0";
        this.generatedAt = DateUtils.getCurrentDate();
        this.updatedAt = "";
        this.paymentCenter = "";

    }

}