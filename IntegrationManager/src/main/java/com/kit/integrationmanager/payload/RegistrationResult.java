package com.kit.integrationmanager.payload;

import java.util.ArrayList;
import java.util.List;

public class RegistrationResult {
    private RegistrationStatus syncStatus;
    private List<String> applicationIds;

    public RegistrationResult(RegistrationStatus syncStatus, List<String> applicationIds) {
        this.syncStatus = syncStatus;
        this.applicationIds = applicationIds;
    }

    public RegistrationStatus getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(RegistrationStatus syncStatus) {
        this.syncStatus = syncStatus;
    }

    public List<String> getApplicationIds() {
        return applicationIds;
    }

    public void setApplicationIds(ArrayList<String> applicationIds) {
        this.applicationIds = applicationIds;
    }
}
