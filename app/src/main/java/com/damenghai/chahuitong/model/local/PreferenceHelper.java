package com.damenghai.chahuitong.model.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Sgun on 15/8/11.
 */
public class PreferenceHelper {
    public static final String PREFERENCES_NAME = "chahuitong_prefs";
    private static final String KEY = "key";

    private final SharedPreferences mPreferences;

    public PreferenceHelper(Context context) {
        mPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 保存信息到 SharedPreference
     *
     * @param key
     */
    public boolean writeValue(String key, String value) {
        Editor editor = mPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public String readString(String key) {
        return mPreferences.getString(key, "");
    }

    /**
     * 保存 Key 字符串到 SharedPreference
     *
     * @param key 返回 Key 字符串
     */
    public void writeSession(String key) {
        writeValue(KEY, key);
    }

    /**
     * 从 SharedPreference 获取Key
     *
     * @return 返回 Key 字符串
     */
    public String readSession() {
        return readString(KEY);
    }

    /**
     * 清空 SharedPreference 中的 Key 信息
     *
     */
    public boolean remove(String key) {
        Editor editor = mPreferences.edit();
        editor.remove(key);
        return editor.commit();
    }

    /**
     * 清空 SharedPreference 中的 Key 信息
     *
     */
    public boolean clear() {
        Editor editor = mPreferences.edit();
        editor.clear();
        return editor.commit();
    }
}
