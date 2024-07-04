package com.kit.databasemanager.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kit.databasemanager.model.PayrollEO;

import java.util.List;

@Dao
public interface PayrollDao {
    @Insert
    void insert(PayrollEO payroll);

    @Update
    void update(PayrollEO payroll);

    @Delete
    void delete(PayrollEO payroll);

    @Query("SELECT * FROM payroll WHERE payrollId = :payrollId")
    PayrollEO findById(int payrollId);

    @Query("SELECT * FROM payroll LIMIT :limit OFFSET :offset")
    List<PayrollEO> findAllWithLimitOffset(int limit, int offset);
}
