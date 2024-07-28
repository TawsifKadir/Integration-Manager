package com.kit.integrationmanager;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.kit.integrationmanager.interceptor.DownloadProgressInterceptor;
import com.kit.integrationmanager.model.ServerInfo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lombok.Getter;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final String CONNECT_TIMEOUT = "CONNECT_TIMEOUT";
    private static final String READ_TIMEOUT = "READ_TIMEOUT";
    private static final String WRITE_TIMEOUT = "WRITE_TIMEOUT";

    private int DEFAULT_CONNECT_TIMEOUT = 10;
    private int DEFAULT_READ_TIMEOUT = 10;
    private int DEFAULT_WRITE_TIMEOUT = 10;
    private Retrofit retrofit;
    private static APIClient apiClient = null;
    @Getter
    private ServerInfo serverInfo;
    private String TAG = "APICLIENT";

    Interceptor timeoutInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            int connectTimeout = chain.connectTimeoutMillis();
            int readTimeout = chain.readTimeoutMillis();
            int writeTimeout = chain.writeTimeoutMillis();

            String connectNew = request.header(CONNECT_TIMEOUT);
            String readNew = request.header(READ_TIMEOUT);
            String writeNew = request.header(WRITE_TIMEOUT);

            if (!TextUtils.isEmpty(connectNew)) {
                connectTimeout = Integer.valueOf(connectNew);
            }
            if (!TextUtils.isEmpty(readNew)) {
                readTimeout = Integer.valueOf(readNew);
            }
            if (!TextUtils.isEmpty(writeNew)) {
                writeTimeout = Integer.valueOf(writeNew);
            }

            return chain
                    .withConnectTimeout(connectTimeout, TimeUnit.SECONDS)
                    .withReadTimeout(readTimeout, TimeUnit.SECONDS)
                    .withWriteTimeout(writeTimeout, TimeUnit.SECONDS)
                    .proceed(request);
        }
    };

    public APIClient() {
        this.serverInfo = null;
        this.retrofit = null;
    }

    public static APIClient getInstance() throws Exception{

        if(apiClient==null){
            apiClient = new APIClient();
        }
        return apiClient;
    }

    public Retrofit getRetrofit() throws Exception {

        if (apiClient == null) {
            throw new Exception("API CLient is not initialized");
        }
        if (serverInfo == null) {
            throw new Exception("Missing host information");
        }
        Log.d(TAG, "Protocol : " + serverInfo.getProtocol());
        Log.d(TAG, "Server Host : " + serverInfo.getHost_name());
        Log.d(TAG, "Server Port : " + serverInfo.getPort());

        if (    (serverInfo.getProtocol() != null) &&
                (serverInfo.getProtocol().equalsIgnoreCase("HTTP") || serverInfo.getProtocol().equalsIgnoreCase("HTTPS")) &&
                (serverInfo.getHost_name() != null && serverInfo.getHost_name().length() > 0) &&
                (serverInfo.getPort() == 80 || serverInfo.getPort() == 443 || serverInfo.getPort() == 8090)
        ) {

            if (this.retrofit == null) {
                HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
                logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                String server_url = apiClient.getServerInfo().getProtocol() + "://" + apiClient.getServerInfo().getHost_name() + ":" + apiClient.getServerInfo().getPort() + "/";


                OkHttpClient httpClient = null;

                if(BuildConfig.isDebug) {
                    httpClient = new OkHttpClient.Builder()
                            .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                            .addInterceptor(timeoutInterceptor)
                            .addInterceptor(new DownloadProgressInterceptor())
                            .addInterceptor(logInterceptor)
                            .build();
                }else{
                    httpClient = new OkHttpClient.Builder()
                            .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                            .addInterceptor(timeoutInterceptor)
                            .addInterceptor(new DownloadProgressInterceptor())
                            .build();
                }

                 this.retrofit = new Retrofit.Builder()
                        .baseUrl(server_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient)
                        .build();


                apiClient.setRetrofit(this.retrofit);
            }
        }else{
            throw new Exception("Invalid server parameters");
        }

        return this.retrofit;
    }

    public APIClient setServerInfo(ServerInfo serverInfo){
        this.serverInfo = serverInfo;
        return this;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public int getDEFAULT_CONNECT_TIMEOUT() {
        return DEFAULT_CONNECT_TIMEOUT;
    }

    public void setDEFAULT_CONNECT_TIMEOUT(int DEFAULT_CONNECT_TIMEOUT) {
        this.DEFAULT_CONNECT_TIMEOUT = DEFAULT_CONNECT_TIMEOUT;
    }

    public int getDEFAULT_READ_TIMEOUT() {
        return DEFAULT_READ_TIMEOUT;
    }

    public void setDEFAULT_READ_TIMEOUT(int DEFAULT_READ_TIMEOUT) {
        this.DEFAULT_READ_TIMEOUT = DEFAULT_READ_TIMEOUT;
    }

    public int getDEFAULT_WRITE_TIMEOUT() {
        return DEFAULT_WRITE_TIMEOUT;
    }

    public void setDEFAULT_WRITE_TIMEOUT(int DEFAULT_WRITE_TIMEOUT) {
        this.DEFAULT_WRITE_TIMEOUT = DEFAULT_WRITE_TIMEOUT;
    }
}
