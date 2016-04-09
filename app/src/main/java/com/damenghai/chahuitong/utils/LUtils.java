package com.damenghai.chahuitong.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.damenghai.chahuitong.model.local.PreferenceHelper;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class LUtils {
    private static final String TAG = "DSK";

    private static final String PREFERENCES_NAME = "chahuitong_prefs";

    public static boolean isDebug = true;

    private static Toast toast;

    private static Context applicationContext;

    public static void initialize (Application app) {
        applicationContext = app.getApplicationContext();
    }

    // 下面四个是默认tag的函数
    public static void log(String msg) {
        log(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void log(String tag, String msg) {
        if (isDebug)
            android.util.Log.i(tag, msg);
    }

    public static void toast(CharSequence text) {
        if(toast == null) {
            toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void toast(@StringRes int resId) {
        toast(applicationContext.getString(resId));
    }

    public static void toastLong(CharSequence text) {
        if(toast == null) {
            toast = Toast.makeText(applicationContext, text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void toastLong(@StringRes int resId) {
        toastLong(applicationContext.getString(resId));
    }

    public static SharedPreferences getPreferences() {
        return getPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
    }

    public static SharedPreferences getPreferences(String name) {
        return getPreferences(name, Activity.MODE_PRIVATE);
    }

    public static SharedPreferences getPreferences(String name, int mode) {
        return applicationContext.getSharedPreferences(name, mode);
    }

}
