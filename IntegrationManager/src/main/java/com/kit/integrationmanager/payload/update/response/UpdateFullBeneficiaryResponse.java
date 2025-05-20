package com.kit.integrationmanager.payload.update.response;

import com.kit.integrationmanager.model.BulkResponse;

import java.util.List;

public class UpdateFullBeneficiaryResponse {
    private List<BulkResponse> bulkResponse;
    private int successCount;

    // Getters and Setters
    public List<BulkResponse> getBulkResponse() {
        return bulkResponse;
    }

    public void setBulkResponse(List<BulkResponse> bulkResponse) {
        this.bulkResponse = bulkResponse;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }
}
