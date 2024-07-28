package com.kit.databasemanager.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.kit.databasemanager.model.PayrollDataEO;
import com.kit.databasemanager.model.PayrollReconcileStatus;

import java.util.List;

@Dao
public interface PayrollReconcileStatusDao {
    @Insert
    void insert(PayrollReconcileStatus payrollReconcileStatus);

    @Query("SELECT * FROM payroll_reconcile_status")
    List<PayrollReconcileStatus> getAllStatuses();

    @Query("DELETE FROM payroll_reconcile_status")
    void deleteAllStatuses();
    // Update syncDate
    @Query("UPDATE payroll_reconcile_status SET syncDate = :syncDate WHERE id = :id")
    void updateSyncDate(int id, String syncDate);

    // Update syncedBy
    @Query("UPDATE payroll_reconcile_status SET syncedBy = :syncedBy WHERE id = :id")
    void updateSyncedBy(int id, String syncedBy);

    // Update syncStatus
    @Query("UPDATE payroll_reconcile_status SET syncStatus = :syncStatus WHERE id = :id")
    void updateSyncStatus(int id, String syncStatus);

    @Transaction
    default void insertAndDelete(PayrollReconcileStatus payrollReconcileStatus, PayrollDataEO payrollData, PayrollDataDao payrollDataDao) {
        insert(payrollReconcileStatus);
        payrollDataDao.delete(payrollData);
    }

    @Transaction
    default void insertAndDeleteById(PayrollReconcileStatus payrollReconcileStatus, PayrollDataEO payrollData, PayrollDataDao payrollDataDao, PayrollDataEO payrollDataEO) {
        insert(payrollReconcileStatus);
        payrollDataDao.deleteById(payrollDataEO.dataId);
    }
}
