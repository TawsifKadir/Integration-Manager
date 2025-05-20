package com.kit.integrationmanager.interceptor;

import android.util.Log;

import com.kit.integrationmanager.custom.response.DownloadProgressResponseBody;
import com.kit.integrationmanager.custom.response.SyncProgressRequestBody;
import com.kit.integrationmanager.event.DownloadProgressEvent;
import com.kit.integrationmanager.event.SyncProgressEvent;
import com.kit.integrationmanager.event.bus.SimpleEventBus;
import com.kit.integrationmanager.listener.DownloadProgressListener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class SyncProgressInterceptor implements Interceptor {
    public static final String SYNC_IDENTIFIER_HEADER = "X-Sync-Identifier";
    public SyncProgressInterceptor() {
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String syncIdentifier = originalRequest.header(SYNC_IDENTIFIER_HEADER);

        if (syncIdentifier == null) {
            return chain.proceed(originalRequest);
        }

        RequestBody originalRequestBody = originalRequest.body();
        if (originalRequestBody == null) {
            return chain.proceed(originalRequest);
        }

        // Create progress-aware request body
        SyncProgressRequestBody progressRequestBody = new SyncProgressRequestBody(
                originalRequestBody,
                syncIdentifier,
                (bytesWritten, contentLength) -> {
                    // Initial event with total content length
                    SimpleEventBus.getInstance().post(
                            new SyncProgressEvent(syncIdentifier, 0, contentLength, false)
                    );
                },
                (bytesWritten, contentLength) -> {
                    // Progress update
                    SimpleEventBus.getInstance().post(
                            new SyncProgressEvent(syncIdentifier, bytesWritten, contentLength, false)
                    );
                },
                (bytesWritten, contentLength) -> {
                    // Completion
                    SimpleEventBus.getInstance().post(
                            new SyncProgressEvent(syncIdentifier, bytesWritten, contentLength, true)
                    );
                }
        );

        Request progressRequest = originalRequest.newBuilder()
                .method(originalRequest.method(), progressRequestBody)
                .build();

        return chain.proceed(progressRequest);
    }
}