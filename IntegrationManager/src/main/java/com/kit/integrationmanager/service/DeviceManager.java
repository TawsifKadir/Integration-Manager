package com.kit.integrationmanager.service;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;

public class DeviceManager {
    private static DeviceManager myInst = null;
    private Context mContext = null;
    private boolean isConnected = false;
    private  boolean unmetered = false;
    private static Object syncObject = new Object();
    private String TAG = "Device Manager";


    public static DeviceManager getInstance(Context context){

        synchronized (syncObject) {
            if (context == null) return null;

            if (myInst == null) {
                myInst = new DeviceManager();
            }
        }
        myInst.setContext(context);
        return myInst;

    }

    public boolean isOnline(){

        if(mContext==null){
            return false;
        }
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(myInst.mContext,ConnectivityManager.class);
        if(connectivityManager.getActiveNetwork()!=null) return true;
        return false;
    }

    public void setContext(Context context){
        this.mContext = context;
    }
    public Context getmContext(){
        return mContext;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public boolean isUnmetered() {
        return unmetered;
    }

    public void setUnmetered(boolean unmetered) {
        this.unmetered = unmetered;
    }
    public String getDeviceUniqueID(){
        try{
            return Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        }catch(Exception exc){
            Log.e(TAG,"Error while retrieving device unique id.");
            exc.printStackTrace();
        }
        return null;
    }
}
