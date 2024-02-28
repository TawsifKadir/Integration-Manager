package com.kit.integrationmanager.model;

import lombok.Data;

@Data
public class Device {
    private String deviceId;
    private String imei;
    private Address address;
    private Location location;
}
