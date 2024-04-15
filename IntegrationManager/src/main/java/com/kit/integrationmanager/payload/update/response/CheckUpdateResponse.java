package com.kit.integrationmanager.payload.update.response;

import com.kit.integrationmanager.payload.ResponseHeader;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckUpdateResponse extends ResponseHeader {
 private long major;
 private long minor;
 private long patch;
 private boolean updateAvailable;
 private boolean forcedUpdate;
 private String apkUrl;
}
