/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kit.integrationmanager.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author anwar
 */
@Data
@Getter
@Setter
public class Payroll {
    @SerializedName("id")
    @JsonProperty("id")
    private Integer payrollId;
    @JsonProperty("state")
    @SerializedName("state")
    private Integer state;
    @JsonProperty("county")
    @SerializedName("county")
    private Integer county;
    @JsonProperty("payam")
    @SerializedName("payam")
    private Integer payam;
    @JsonProperty("boma")
    @SerializedName("boma")
    private Long boma;
    @JsonProperty("payrollData")
    @SerializedName("payrollData")
    private List<PayrollData> payrollData;
}
