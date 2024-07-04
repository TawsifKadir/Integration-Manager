package com.kit.integrationmanager.payload.reconcile.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kit.integrationmanager.model.PayrollReconcile;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayrollReconcileBatchRequest {
    @JsonProperty("dataList")
    private List<PayrollReconcile> dataList;
}
