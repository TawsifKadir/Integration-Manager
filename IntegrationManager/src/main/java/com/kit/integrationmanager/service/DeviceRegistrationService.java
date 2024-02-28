package com.kit.integrationmanager.service;


import com.kit.integrationmanager.payload.device.request.RegisterDeviceRequest;

import java.util.HashMap;

public interface DeviceRegistrationService {
    public void registerDevice(RegisterDeviceRequest registerDeviceRequest,HashMap<String,String > headers);
}
