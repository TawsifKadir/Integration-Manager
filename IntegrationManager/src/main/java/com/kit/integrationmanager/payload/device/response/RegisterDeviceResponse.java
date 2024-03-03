package com.kit.integrationmanager.payload.device.response;

import com.kit.integrationmanager.payload.ResponseHeader;

import lombok.Data;

@Data
public class RegisterDeviceResponse extends ResponseHeader {
    private Integer id;
    private String deviceId;
}
