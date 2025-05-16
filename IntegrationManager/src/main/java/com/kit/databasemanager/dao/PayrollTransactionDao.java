package com.kit.databasemanager.dao;

import android.util.Log;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Transaction;


import com.kit.databasemanager.model.PayrollBiometricEO;
import com.kit.databasemanager.model.PayrollDataEO;
import com.kit.databasemanager.model.PayrollEO;

@Dao
public abstract class PayrollTransactionDao {


    private int payrollDataIdSequence = 1;
    private int payrollBiometricIdSequence = 1;

    private Object LOCK = new Object();

    @Transaction
    public void insertPayrollRecord(PayrollEO payroll,
                                    PayrollDataEO payrollDataEO,
                                    PayrollBiometricEO beneficiaryBiometricEO,
                                    PayrollBiometricEO firstAlternateBiometricEO ,
                                    PayrollBiometricEO secondAlternateBiometricEO

                                    )
    {

            if(payroll!=null){
                payrollDataEO.setPayrollId(payroll.getPayrollId());
            }
            if(beneficiaryBiometricEO!=null){
                beneficiaryBiometricEO.setBiometricId(this.getNextPayrollBiometricId());
                insert(beneficiaryBiometricEO);
                payrollDataEO.setBenBiometricId(beneficiaryBiometricEO.getBiometricId());
            }
            if(firstAlternateBiometricEO!=null){
                firstAlternateBiometricEO.setBiometricId(this.getNextPayrollBiometricId());
                insert(firstAlternateBiometricEO);
                payrollDataEO.setFirstAltBiometricId(firstAlternateBiometricEO.getBiometricId());
            }
            if(secondAlternateBiometricEO!=null){
                secondAlternateBiometricEO.setBiometricId(this.getNextPayrollBiometricId());
                insert(secondAlternateBiometricEO);
                payrollDataEO.setSecondAltBiometricId(secondAlternateBiometricEO.getBiometricId());
            }
            if(payrollDataEO!=null){
                payrollDataEO.setDataId(this.getNextPayrollDataId());
                insert(payrollDataEO);
            }
    }


    private int getNextPayrollDataId(){
        synchronized (LOCK){
            return payrollDataIdSequence++;
        }
    }

    private int getNextPayrollBiometricId(){
        synchronized (LOCK){
            return payrollBiometricIdSequence++;
        }
    }

    @Insert
    abstract void insert(PayrollEO payrollEO);
    @Insert
    abstract void insert(PayrollDataEO payrollDataEO);

    @Insert
    abstract void insert(PayrollBiometricEO payrollBiometricEO);
}
