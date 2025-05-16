package com.kit.integrationmanager.payload.reconcile.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kit.integrationmanager.model.BulkOperation;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayrollReconcileBatchResponse {
    @JsonProperty("bulkResponse")
    private List<BulkOperation> bulkResponse;
}
