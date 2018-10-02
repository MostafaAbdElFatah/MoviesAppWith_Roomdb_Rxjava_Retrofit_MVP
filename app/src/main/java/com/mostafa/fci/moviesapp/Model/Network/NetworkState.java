package com.mostafa.fci.moviesapp.Model.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkState {

    public static boolean isOnLine(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting() )
            return true;
        else
            return false;

    }
    // return type of network connected
    public static int getConnectivityType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI
                    && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                // wifi network is connected
                return ConnectivityType.TYPE_WIFI;

            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE
                    && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                // mobile network is connected
                return ConnectivityType.TYPE_MOBILE;
            }
        }
        // no any Connectivities to mobile
        return ConnectivityType.TYPE_NOT_CONNECTED;
    }

    // Check if there is any connectivity for a Wifi network
    public static boolean isConnectedWifi(Context context){
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        if (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI){
            return true;
        }
        return false;
    }

    // Check if there is any connectivity for a mobile network
    public static boolean isConnectedMobile(Context context){
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE){
            return true;
        }
        return false;
    }

    // Check all connectivities whether available or not
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }


    public static class ConnectivityType {
        public static int TYPE_NOT_CONNECTED = 0 ;
        public static int  TYPE_WIFI = 1;
        public static int  TYPE_MOBILE = 2 ;
    }
}
