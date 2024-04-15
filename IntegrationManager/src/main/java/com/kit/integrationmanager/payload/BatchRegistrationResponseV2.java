package com.kit.integrationmanager.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchRegistrationResponseV2 {
    private String applicationId;
    private String errorMessage;
    private String status;
}
