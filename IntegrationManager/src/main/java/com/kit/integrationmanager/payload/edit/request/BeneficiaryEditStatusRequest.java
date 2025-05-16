package com.kit.integrationmanager.payload.edit.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeneficiaryEditStatusRequest {
    @JsonProperty("requests")
    @SerializedName("requests")
    private List<BeneficiaryEditStatusEntry> requests;
}
