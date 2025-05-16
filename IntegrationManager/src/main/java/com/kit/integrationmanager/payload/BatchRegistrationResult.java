package com.kit.integrationmanager.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchRegistrationResult {
    private RegistrationStatus syncStatus;
    private List<BatchRegistrationResponseV2> responseList;

    public BatchRegistrationResult(RegistrationStatus syncStatus, List<BatchRegistrationResponseV2> responseList) {
        this.syncStatus = syncStatus;
        this.responseList = responseList;
    }
}
