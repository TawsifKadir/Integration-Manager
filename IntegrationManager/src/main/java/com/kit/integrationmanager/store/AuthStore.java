package com.kit.integrationmanager.store;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kit.integrationmanager.model.Login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AuthStore {
    private static AuthStore myInst = null;
    private Login login = null;
    private String authToken = null;
    private String deviceId = null;
    private Context mContext = null;

    private static Object syncObject = new Object();

    private String TAG = "AuthStore";

    private AuthStore() {

    }

    public static AuthStore getInstance(Context context){
        synchronized (syncObject) {
            if (myInst == null) {
                myInst = new AuthStore();
                myInst.setContext(context);
            }
        }
        return myInst;
    }

    public Login getLoginInfoFromCache() {
        Login login = null;

        if(mContext==null) return null;

        String data = readFromCache();
        if(data==null){
            Log.e(TAG,"Null data read from cache");
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try{
            login = mapper.readValue(data, Login.class);
        }catch(JsonProcessingException jsonException){
            Log.e(TAG,"Error while parsing json data");
            jsonException.printStackTrace();
            return null;
        }
        return login;
    }


    public boolean saveLoginInfoToCache(Login login) {
        ObjectMapper mapper = new ObjectMapper();

        if(mContext==null) return false;

        String data = null;
        try {
            data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(login);
        }catch(JsonProcessingException jsonException){
            Log.e(TAG,jsonException.getMessage());
            jsonException.printStackTrace();
            return false;
        }
        if(data == null){
            Log.e(TAG,"Null data received after JSON parsing");
            return false;
        }
        return writeToCache(data);
    }

    private boolean writeToCache(String data){

        File file = new File(mContext.getFilesDir(), "cache.json");
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch(Exception exc){
                Log.e(TAG,"Error occured while creating cahce file.");
                exc.printStackTrace();
                return false;
            }
        }
        try(FileOutputStream fos = new FileOutputStream(file); OutputStreamWriter osw = new OutputStreamWriter(fos);){
            osw.write(data);
            osw.flush();
        }catch(Exception exc){
            Log.e(TAG,"Error occured while writing to cahce.");
            exc.printStackTrace();
            return false;
        }

        return true;
    }

    private String readFromCache(){

        File file = new File(mContext.getFilesDir(), "cache.json");
        if(!file.exists()) return null;

        try (FileInputStream fis = new FileInputStream(file); InputStreamReader isr = new InputStreamReader(fis); BufferedReader br = new BufferedReader(isr)){
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String text = sb.toString();
            return text;

        }catch(Exception exc){
            Log.e(TAG,"Error occured while reading cahce.");
            exc.printStackTrace();
        }

        return null;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public boolean doOfflineAuthentication(String userName,String password,String deviceID){
        Login login = this.getLoginInfoFromCache();
        if(login==null) return false;
        if( (login.getUserName()==null) || (login.getPassword()==null) || (login.getDeviceID()==null) )
            return false;

        if(login.getUserName().equals(userName)
           && login.getPassword().equals(password)
           && login.getDeviceID().equals(deviceID)
        ) return true;
        return false;
    }
}
