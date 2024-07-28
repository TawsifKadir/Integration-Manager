package com.kit.integrationmanager.payload.reconcile.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kit.integrationmanager.model.PayrollReconcile;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayrollReconcileRequest {
    @JsonProperty("data")
    PayrollReconcile data;
}
