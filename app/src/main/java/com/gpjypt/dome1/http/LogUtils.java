package com.gpjypt.dome1.http;

import android.util.Log;

import com.gpjypt.dome1.BuildConfig;


public class LogUtils {
    private static boolean isDebug = BuildConfig.DEBUG;
    private static String fTag = "| -->";

    public static void logV(Class c,String msg) {
        String tag = c.getSimpleName();
        Log.v(tag, fTag +getLineNumber("logV")+msg);
    }
    public static void logE(String msg){
        Log.v("logE", fTag +getLineNumber("logV")+msg);
    }

    private static String getLineNumber(String mn) {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();

        int cIndex = 0;
        for (int i = 0; i < ste.length; i++) {
            if (i == 0 || ste[i] == null) {
                if (ste[0].getMethodName().compareTo(mn) == 0) {
                    cIndex = i + 2;
                } else {
                    cIndex = -1;
                }
            }
        }
        if (cIndex == -1) {
            return "";
        }
        String fn = ste[cIndex].getFileName();
        String ln = ste[cIndex].getLineNumber() + "";
        return fn + ln;
    }
}
