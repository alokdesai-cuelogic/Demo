package com.alok.pp.utilities;

import android.util.Log;

/**
 * Created by Audetemi Inc. on 13/12/16.
 */

public class LogUtils {

    private static final String TAG = LogUtils.class.getSimpleName();

    public static void printLogs(String value) {
        //if (BuildConfig.ALLOW_LOGS) {
            Log.d(TAG, ""+value);
       // }
    }
    public static void printLogs(String TAG, String value) {
       // if (BuildConfig.ALLOW_LOGS) {
            Log.d(TAG, ""+value);
       // }
    }
}
