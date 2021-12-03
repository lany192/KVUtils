package com.github.lany192.spkv;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * SharedPreferences封装工具类
 */
public class SPUtils {
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static String getString(String key) {
        return getPreferences().getString(key, "");
    }

    public static String getString(String key, String defaultValue) {
        return getPreferences().getString(key, defaultValue);
    }

    public static String getString(String name, String key, String defaultValue) {
        return getPreferences(name).getString(key, defaultValue);
    }

    public static void putString(String key, String value) {
        getPreferences().edit().putString(key, value).apply();
    }

    public static void putString(String name, String key, String value) {
        getPreferences(name).edit().putString(key, value).apply();
    }

    public static boolean putStringForResult(String key, String value) {
        return getPreferences().edit().putString(key, value).commit();
    }

    public static boolean putStringForResult(String name, String key, String value) {
        return getPreferences(name).edit().putString(key, value).commit();
    }

    public static boolean getBoolean(String key) {
        return getPreferences().getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return getPreferences().getBoolean(key, defaultValue);
    }

    public static boolean getBoolean(String name, String key, boolean defaultValue) {
        return getPreferences(name).getBoolean(key, defaultValue);
    }

    public static void putBoolean(String key, boolean value) {
        getPreferences().edit().putBoolean(key, value).apply();
    }

    public static void putBoolean(String name, String key, boolean value) {
        getPreferences(name).edit().putBoolean(key, value).apply();
    }

    public static boolean putBooleanForResult(String key, boolean value) {
        return getPreferences().edit().putBoolean(key, value).commit();
    }

    public static boolean putBooleanForResult(String name, String key, boolean value) {
        return getPreferences(name).edit().putBoolean(key, value).commit();
    }

    public static void putInt(String key, int value) {
        getPreferences().edit().putInt(key, value).apply();
    }

    public static void putInt(String name, String key, int value) {
        getPreferences(name).edit().putInt(key, value).apply();
    }

    public static boolean putIntForResult(String key, int value) {
        return getPreferences().edit().putInt(key, value).commit();
    }

    public static boolean putIntForResult(String name, String key, int value) {
        return getPreferences(name).edit().putInt(key, value).commit();
    }

    public static int getInt(String key) {
        return getPreferences().getInt(key, 0);
    }

    public static int getInt(String key, int defaultValue) {
        return getPreferences().getInt(key, defaultValue);
    }

    public static int getInt(String name, String key, int defaultValue) {
        return getPreferences(name).getInt(key, defaultValue);
    }

    public static void putFloat(String key, float value) {
        getPreferences().edit().putFloat(key, value).apply();
    }

    public static void putFloat(String name, String key, float value) {
        getPreferences(name).edit().putFloat(key, value).apply();
    }

    public static boolean putFloatForResult(String key, float value) {
        return getPreferences().edit().putFloat(key, value).commit();
    }

    public static boolean putFloatForResult(String name, String key, float value) {
        return getPreferences(name).edit().putFloat(key, value).commit();
    }

    public static float getFloat(String key) {
        return getPreferences().getFloat(key, 0f);
    }

    public static float getFloat(String key, float defaultValue) {
        return getPreferences().getFloat(key, defaultValue);
    }

    public static float getFloat(String name, String key, float defaultValue) {
        return getPreferences(name).getFloat(key, defaultValue);
    }

    public static void putLong(String key, long value) {
        getPreferences().edit().putLong(key, value).apply();
    }

    public static void putLong(String name, String key, long value) {
        getPreferences(name).edit().putLong(key, value).apply();
    }

    public static boolean putLongForResult(String key, long value) {
        return getPreferences().edit().putLong(key, value).commit();
    }

    public static boolean putLongForResult(String name, String key, long value) {
        return getPreferences(name).edit().putLong(key, value).commit();
    }

    public static long getLong(String key) {
        return getPreferences().getLong(key, 0L);
    }

    public static long getLong(String key, long defaultValue) {
        return getPreferences().getLong(key, defaultValue);
    }

    public static long getLong(String name, String key, long defaultValue) {
        return getPreferences(name).getLong(key, defaultValue);
    }

    public static void remove(String key) {
        getPreferences().edit().remove(key).apply();
    }

    public static void remove(String name, String key) {
        getPreferences(name).edit().remove(key).apply();
    }

    public static void clear() {
        Editor editor = getPreferences().edit();
        editor.clear();
        editor.apply();
    }

    public static void clear(String name) {
        Editor editor = getPreferences(name).edit();
        editor.clear();
        editor.apply();
    }

    private static SharedPreferences getPreferences() {
        return getPreferences(null);
    }

    private static SharedPreferences getPreferences(String name) {
        if (TextUtils.isEmpty(name)) {
            return PreferenceManager.getDefaultSharedPreferences(mContext);
        } else {
            return mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
    }

    public static boolean exists(String key) {
        return getPreferences().contains(key);
    }

    public static boolean exists(String name, String key) {
        return getPreferences(name).contains(key);
    }
}
