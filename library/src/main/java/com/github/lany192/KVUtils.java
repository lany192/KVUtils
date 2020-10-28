package com.github.lany192;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.tencent.mmkv.MMKV;

/**
 * MMKV封装工具类
 */
public final class KVUtils {
    private volatile static KVUtils instance;
    private Context mContext;

    private KVUtils() {
    }

    public static KVUtils get() {
        if (instance == null) {
            synchronized (KVUtils.class) {
                if (instance == null) {
                    instance = new KVUtils();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        mContext = context;
        String rootDir = MMKV.initialize(context);
        Log.i("SPUtils", "mmkv工作目录: " + rootDir);
    }

    private SharedPreferences getPreferences() {
        return getPreferences(null);
    }

    private SharedPreferences getOldPreferences(String name) {
        if (TextUtils.isEmpty(name)) {
            return PreferenceManager.getDefaultSharedPreferences(mContext);
        } else {
            return mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
    }

    private SharedPreferences getPreferences(String name) {
        MMKV mmkv;
        if (TextUtils.isEmpty(name)) {
            mmkv = MMKV.mmkvWithID(mContext.getPackageName() + "_preferences", MMKV.MULTI_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(name, MMKV.MULTI_PROCESS_MODE);
        }
        // 迁移旧数据 start，2021年11月以后可以删除这段代码
        SharedPreferences oldSharedPreferences = getOldPreferences(name);
        mmkv.importFromSharedPreferences(oldSharedPreferences);
        oldSharedPreferences.edit().clear().apply();
        // 迁移旧数据 end
        return mmkv;
    }

    public String getString(String key) {
        return getPreferences().getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        return getPreferences().getString(key, defaultValue);
    }

    public String getString(String name, String key, String defaultValue) {
        return getPreferences(name).getString(key, defaultValue);
    }

    public void putString(String key, String value) {
        getPreferences().edit().putString(key, value);
    }

    public void putString(String name, String key, String value) {
        getPreferences(name).edit().putString(key, value);
    }

    public boolean getBoolean(String key) {
        return getPreferences().getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return getPreferences().getBoolean(key, defaultValue);
    }

    public boolean getBoolean(String name, String key, boolean defaultValue) {
        return getPreferences(name).getBoolean(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        getPreferences().edit().putBoolean(key, value);
    }

    public void putBoolean(String name, String key, boolean value) {
        getPreferences(name).edit().putBoolean(key, value);
    }

    public void putInt(String key, int value) {
        getPreferences().edit().putInt(key, value);
    }

    public void putInt(String name, String key, int value) {
        getPreferences(name).edit().putInt(key, value);
    }

    public int getInt(String key) {
        return getPreferences().getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return getPreferences().getInt(key, defaultValue);
    }

    public int getInt(String name, String key, int defaultValue) {
        return getPreferences(name).getInt(key, defaultValue);
    }

    public void putFloat(String key, float value) {
        getPreferences().edit().putFloat(key, value);
    }

    public void putFloat(String name, String key, float value) {
        getPreferences(name).edit().putFloat(key, value);
    }

    public float getFloat(String key) {
        return getPreferences().getFloat(key, 0f);
    }

    public float getFloat(String key, float defaultValue) {
        return getPreferences().getFloat(key, defaultValue);
    }

    public float getFloat(String name, String key, float defaultValue) {
        return getPreferences(name).getFloat(key, defaultValue);
    }

    public void putLong(String key, long value) {
        getPreferences().edit().putLong(key, value);
    }

    public void putLong(String name, String key, long value) {
        getPreferences(name).edit().putLong(key, value);
    }

    public long getLong(String key) {
        return getPreferences().getLong(key, 0L);
    }

    public long getLong(String key, long defaultValue) {
        return getPreferences().getLong(key, defaultValue);
    }

    public long getLong(String name, String key, long defaultValue) {
        return getPreferences(name).getLong(key, defaultValue);
    }

    public void remove(String key) {
        getPreferences().edit().remove(key);
    }

    public void remove(String name, String key) {
        getPreferences(name).edit().remove(key);
    }

    public void clear() {
        Editor editor = getPreferences().edit();
        editor.clear();
    }

    public void clear(String name) {
        Editor editor = getPreferences(name).edit();
        editor.clear();
    }

    public boolean exists(String key) {
        return getPreferences().contains(key);
    }

    public boolean exists(String name, String key) {
        return getPreferences(name).contains(key);
    }
}