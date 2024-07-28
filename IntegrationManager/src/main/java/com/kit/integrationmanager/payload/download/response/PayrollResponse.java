package com.kit.integrationmanager.payload.download.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.kit.integrationmanager.model.Payroll;

import java.util.List;

import com.kit.integrationmanager.payload.ResponseHeader;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PayrollResponse extends ResponseHeader {
    @SerializedName("data")
    @JsonProperty("data")
    private List<Payroll> payrolls;
    @SerializedName("totalCount")
    @JsonProperty("totalCount")
    private Integer total;
}
