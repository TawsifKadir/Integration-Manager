package com.kit.integrationmanager.listener;

public interface DownloadProgressListener {
    void update(String downloadIdentifier, long bytesRead, long contentLength, boolean done);
}
