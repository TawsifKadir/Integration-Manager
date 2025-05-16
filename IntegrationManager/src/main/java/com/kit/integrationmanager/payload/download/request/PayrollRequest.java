package com.kit.integrationmanager.payload.download.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PayrollRequest {
    private String payrollId;
    private String createdFrom;
    private String createdTo;
    private String state;
    private String county;
    private String payam;
    private String boma;
    private String supportType;
}
