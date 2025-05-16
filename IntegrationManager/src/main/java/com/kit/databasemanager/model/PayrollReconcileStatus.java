package com.kit.databasemanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "payroll_reconcile_status")
public class PayrollReconcileStatus {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String syncStatus;
    public String syncedBy;
    public String applicationId;
    public String syncDate;

    // Constructor
    public PayrollReconcileStatus(String syncStatus, String syncedBy, String applicationId, String syncDate) {
        this.syncStatus = syncStatus;
        this.syncedBy = syncedBy;
        this.applicationId = applicationId;
        this.syncDate = syncDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getSyncedBy() {
        return syncedBy;
    }

    public void setSyncedBy(String syncedBy) {
        this.syncedBy = syncedBy;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(String syncDate) {
        this.syncDate = syncDate;
    }

}