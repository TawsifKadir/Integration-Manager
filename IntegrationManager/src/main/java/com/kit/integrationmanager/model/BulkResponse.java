package com.kit.integrationmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BulkResponse {
    @JsonProperty("applicationId")
    private String applicationId;
    @JsonProperty("result")
    private BulkOperationResult result;
    @JsonProperty("errorMessage")
    private String errorMessage;
    @JsonProperty("requestId")
    private String requestId;
}
