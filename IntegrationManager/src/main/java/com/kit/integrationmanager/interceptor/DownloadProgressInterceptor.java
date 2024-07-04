package com.kit.integrationmanager.interceptor;

import android.os.Build;
import android.util.Log;


import com.kit.integrationmanager.custom.response.DownloadProgressResponseBody;
import com.kit.integrationmanager.event.DownloadProgressEvent;
import com.kit.integrationmanager.listener.DownloadProgressListener;
import com.kit.integrationmanager.event.bus.SimpleEventBus;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class DownloadProgressInterceptor implements Interceptor {
    public static final String DOWNLOAD_IDENTIFIER_HEADER = "monitor_progress";
    public DownloadProgressInterceptor() {
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        Response.Builder builder = originalResponse.newBuilder();
        String downloadIdentifier = originalResponse.request().header(DOWNLOAD_IDENTIFIER_HEADER);
        ////boolean isAStream = originalResponse.header("content-type", "").equals("application/octet-stream");
        boolean fileIdentifierIsSet = downloadIdentifier != null && !downloadIdentifier.isEmpty();

        if(fileIdentifierIsSet) { // someone need progress informations !
                Log.d("ProgressInterceptor", "Inside fileIdentifierIsSet");
                builder.body(new DownloadProgressResponseBody(downloadIdentifier, originalResponse.body(), new DownloadProgressListener() {
                    @Override
                    public void update(String identifier, long bytesRead, long contentLength, boolean done) {
                        // we post an event into the Bus !
                        SimpleEventBus.getInstance().post(new DownloadProgressEvent(identifier, contentLength, bytesRead));
                    }
                }));

        } else { // do nothing if it's not a file with an identifier :)
            Log.d("ProgressInterceptor", "Outside fileIdentifierIsSet");
            builder.body(originalResponse.body());
        }

        return builder.build();
    }
}
