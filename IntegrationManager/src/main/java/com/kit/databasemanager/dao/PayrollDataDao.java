package com.kit.databasemanager.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kit.databasemanager.model.PayrollDataEO;

import java.util.List;

@Dao
public interface PayrollDataDao {
    @Insert
    void insert(PayrollDataEO payrollData);

    @Update
    void update(PayrollDataEO payrollData);

    @Delete
    void delete(PayrollDataEO payrollData);

    @Query("SELECT count(*) FROM payroll_data")
    Integer findTotalPayrollData();

    @Query("SELECT * FROM payroll_data WHERE dataId = :dataId")
    PayrollDataEO findById(int dataId);

    @Query("SELECT * FROM payroll_data WHERE benUUID = :benUUID")
    PayrollDataEO findByUUID(String benUUID);

    @Query("SELECT * FROM payroll_data LIMIT :limit OFFSET :offset")
    List<PayrollDataEO> findAllWithLimitOffset(int limit, int offset);

    @Query("SELECT * FROM payroll_data where status = :status")
    List<PayrollDataEO> finRecordsWithStatus(String status);
    @Query("SELECT * FROM payroll_data")
    List<PayrollDataEO> findAllRecords();

    @Query("DELETE FROM payroll_data WHERE benUUID = :applicationId")
    void deleteByApplicationId(String applicationId);
    @Query("DELETE FROM payroll_data WHERE dataId = :id")
    void deleteById(int id);
}
