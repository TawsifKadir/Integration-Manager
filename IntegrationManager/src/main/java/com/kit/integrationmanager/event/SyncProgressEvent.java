package com.kit.integrationmanager.event;

public class SyncProgressEvent {
    private final String syncIdentifier;
    private final long bytesWritten;
    private final long contentLength;
    private final boolean done;

    public SyncProgressEvent(String syncIdentifier, long bytesWritten, long contentLength, boolean done) {
        this.syncIdentifier = syncIdentifier;
        this.bytesWritten = bytesWritten;
        this.contentLength = contentLength;
        this.done = done;
    }

    public String getSyncIdentifier() {
        return syncIdentifier;
    }

    public long getBytesWritten() {
        return bytesWritten;
    }

    public long getContentLength() {
        return contentLength;
    }

    public boolean isDone() {
        return done;
    }

    public int getProgress() {
        if (contentLength <= 0) return 0;
        return (int) ((100 * bytesWritten) / contentLength);
    }
}