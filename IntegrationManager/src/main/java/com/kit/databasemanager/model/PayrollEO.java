/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kit.databasemanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kit.integrationmanager.model.Payroll;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "payroll")
public class PayrollEO {
    @PrimaryKey
    public int payrollId;
    public Integer state;
    public Integer county;
    public Integer payam;
    public Long boma;

    public void mapFromBO(Payroll payroll){
        this.payrollId = payroll.getPayrollId();
        this.state = payroll.getState();
        this.county = payroll.getCounty();
        this.payam = payroll.getPayam();
        this.boma = payroll.getBoma();
    }

}
