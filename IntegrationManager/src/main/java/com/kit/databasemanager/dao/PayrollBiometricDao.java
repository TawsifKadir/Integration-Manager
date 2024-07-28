package com.kit.databasemanager.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kit.databasemanager.model.PayrollBiometricEO;

import java.util.List;

@Dao
public interface PayrollBiometricDao {
    @Insert
    void insert(PayrollBiometricEO payrollBiometric);

    @Update
    void update(PayrollBiometricEO payrollBiometric);

    @Delete
    void delete(PayrollBiometricEO payrollBiometric);

    @Query("SELECT * FROM payroll_bio WHERE biometricId = :biometricId")
    PayrollBiometricEO findById(int biometricId);

    @Query("SELECT * FROM payroll_bio LIMIT :limit OFFSET :offset")
    List<PayrollBiometricEO> findAllWithLimitOffset(int limit, int offset);
}

