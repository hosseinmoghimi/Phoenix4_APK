package com.khafonline.phoenix4.core;

import android.util.Log;

public class Core {

    public static void addLog(String msg) {
        Log.d(Constants.LOG_TAG, msg);
    }

    public static String token() {
        return SharedPref.getProfile().getToken();
    }

}
