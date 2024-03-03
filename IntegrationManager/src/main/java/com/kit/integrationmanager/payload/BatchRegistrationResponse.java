package com.kit.integrationmanager.payload;

import lombok.Data;

import java.util.List;

@Data
public class BatchRegistrationResponse {
    private List<String> applicationIds;
}
