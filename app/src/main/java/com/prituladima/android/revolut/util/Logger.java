package com.prituladima.android.revolut.util;

import android.util.Log;

import com.prituladima.android.revolut.BuildConfig;

public final class Logger {

    public static Logger build(Class clazz) {
        return new Logger(clazz);
    }

    private Logger(Class clazz) {
        TAG = clazz.getSimpleName();
    }

    private final String TAG;
    private final boolean showLogs = BuildConfig.SHOW_LOGS;

    public void log(String message) {
        if (showLogs)
            Log.d(TAG, message);
    }

    public void error(Throwable message) {
        if (showLogs)
            Log.e(TAG, message.toString());
    }

}