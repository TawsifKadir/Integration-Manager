package com.kit.integrationmanager.custom.response;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class SyncProgressRequestBody extends RequestBody {
    private final RequestBody requestBody;
    private final String syncIdentifier;
    private final ProgressListener contentLengthListener;
    private final ProgressListener progressListener;
    private final ProgressListener completionListener;

    public SyncProgressRequestBody(RequestBody requestBody,
                            String syncIdentifier,
                            ProgressListener contentLengthListener,
                            ProgressListener progressListener,
                            ProgressListener completionListener) {
        this.requestBody = requestBody;
        this.syncIdentifier = syncIdentifier;
        this.contentLengthListener = contentLengthListener;
        this.progressListener = progressListener;
        this.completionListener = completionListener;
    }

    @Override
    public long contentLength() throws IOException {
        long contentLength = requestBody.contentLength();
        contentLengthListener.onProgress(0, contentLength);
        return contentLength;
    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        CountingSink countingSink = new CountingSink(sink);
        BufferedSink bufferedSink = Okio.buffer(countingSink);

        requestBody.writeTo(bufferedSink);
        bufferedSink.flush();

        completionListener.onProgress(countingSink.bytesWritten, contentLength());
    }

    private final class CountingSink extends ForwardingSink {
        private long bytesWritten = 0;

        CountingSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            bytesWritten += byteCount;
            progressListener.onProgress(bytesWritten, contentLength());
        }
    }

    public interface ProgressListener {
        void onProgress(long bytesWritten, long contentLength);
    }
}
