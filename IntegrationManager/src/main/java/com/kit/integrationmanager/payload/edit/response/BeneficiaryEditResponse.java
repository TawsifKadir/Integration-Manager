package com.kit.integrationmanager.payload.edit.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeneficiaryEditResponse {
    @JsonProperty("successCount")
    @SerializedName("successCount")
    private int successCount;
    @JsonProperty("bulkResponse")
    @SerializedName("bulkResponse")
    private List<BeneficiaryEditResponseEntry> beneficiaryEditResponseList;
}
