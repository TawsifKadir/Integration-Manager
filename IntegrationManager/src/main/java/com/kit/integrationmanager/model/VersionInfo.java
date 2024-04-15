package com.kit.integrationmanager.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VersionInfo {
    private String appVersion;
    private boolean updateAvailable;
    private boolean forceUpdate;
    private String apkUrl;
}
