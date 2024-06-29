package com.kit.integrationmanager.payload.update.request;

import lombok.Builder;

@Builder
public class CheckUpdateRequest {
    private long major;
    private long minor;
    private long patch;
}
