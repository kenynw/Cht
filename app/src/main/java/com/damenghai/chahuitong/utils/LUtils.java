package com.damenghai.chahuitong.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class LUtils {
    private static final String TAG = "DSK";

    private static final String PREFERENCES_NAME = "chahuitong_prefs";

    public static boolean isDebug = true;

    private static Toast toast;

    private static Context applicationContext;

    public static void initialize(Application app) {
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
        if (toast == null) {
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
        if (toast == null) {
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

    /**
     * 获得屏幕高度
     *
     * @return
     */
    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) applicationContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽度
     *
     * @return
     */
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) applicationContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * dp转px
     *
     * @param dpVal
     * @return
     */
    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, applicationContext.getResources().getDisplayMetrics());
    }

    /**
     * 打卡软键盘
     *
     * @param editText 输入框
     */
    public static void openKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     * @param editText 输入框
     */
    public static void closeKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
