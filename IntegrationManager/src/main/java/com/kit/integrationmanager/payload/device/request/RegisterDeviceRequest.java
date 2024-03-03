package com.kit.integrationmanager.payload.device.request;

import com.kit.integrationmanager.model.Address;
import com.kit.integrationmanager.model.Location;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterDeviceRequest {
    private Address address;
    private Location location;
    private String deviceId;
    private String imei;
}
