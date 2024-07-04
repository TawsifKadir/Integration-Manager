package com.kit.integrationmanager.event;

public class DownloadProgressEvent {
    final int progress;
    final long contentLength;
    final String downloadIdentifier;
    final long bytesRead;

    public DownloadProgressEvent(String identifier, long contentLength, long bytesRead) {
        this.contentLength = contentLength;
        if(contentLength>0) {
            this.progress = (int) (bytesRead / (contentLength / 100f));
        }else{
            this.progress = (int)bytesRead;
        }
        downloadIdentifier = identifier;
        this.bytesRead = bytesRead;
    }

    public int getProgress(){
        return progress;
    }

    public String getDownloadIdentifier() {
        return downloadIdentifier;
    }

    public long getBytesRead() {
        return bytesRead;
    }

    public boolean percentIsAvailable() {
        return contentLength > 0;
    }
}
