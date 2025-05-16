package com.kit.integrationmanager.service;

import com.kit.integrationmanager.model.VersionInfo;

import java.util.HashMap;

public interface UpdateCheckService {
    VersionInfo getUpdateInformation(String currentVersion) throws Exception;
}
