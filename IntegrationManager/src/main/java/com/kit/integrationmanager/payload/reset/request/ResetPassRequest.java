package com.kit.integrationmanager.payload.reset.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResetPassRequest {
    private String newPassword;
}
