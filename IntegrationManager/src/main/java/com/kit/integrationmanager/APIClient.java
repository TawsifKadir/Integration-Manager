package com.kit.integrationmanager;

import android.util.Log;

import com.kit.integrationmanager.model.ServerInfo;

import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private Retrofit retrofit;

    private static APIClient apiClient = null;

    @Getter
    private ServerInfo serverInfo;

    private String TAG = "APICLIENT";

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
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
                String server_url = apiClient.getServerInfo().getProtocol() + "://" + apiClient.getServerInfo().getHost_name() + ":" + apiClient.getServerInfo().getPort() + "/";
                this.retrofit = new Retrofit.Builder().baseUrl(server_url).addConverterFactory(GsonConverterFactory.create()).client(client).build();
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


}
