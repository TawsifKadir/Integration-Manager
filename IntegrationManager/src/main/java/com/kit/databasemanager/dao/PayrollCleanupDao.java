package com.kit.databasemanager.dao;

import android.util.Log;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;

@Dao
public abstract class PayrollCleanupDao {
    private final static String TAG = "PayrollCleanupDao";
    @Transaction
    public void deleteAllPayroll(){
        try{
            deletePayrollBiometric();
            deletePayrollData();
            deletePayroll();
        }catch(Throwable t){
            Log.e(TAG,"Error occured in cleanup "+t.getMessage());
        }
    }

    @Query("delete from payroll")
    abstract void deletePayroll();
    @Query("delete from payroll_data")
    abstract void deletePayrollData();

    @Query("delete from payroll_bio")
    abstract void deletePayrollBiometric();
}
