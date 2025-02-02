package com.kit.integrationmanager.payload.edit.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.kit.integrationmanager.model.BeneficiaryEdit;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class BeneficiaryEditRequest {
    @JsonProperty("beneficiaries")
    @SerializedName("beneficiaries")
    private List<BeneficiaryEdit> beneficiaryEditRequest;
}
