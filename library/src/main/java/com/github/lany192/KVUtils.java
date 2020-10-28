package com.github.lany192;

import android.content.Context;
import android.content.SharedPreferences;
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
    /**
     * 是否加密
     */
    private boolean encrypt;
    /**
     * 秘钥
     */
    private String cryptKey;
    /**
     * 是否迁移SharedPreferences旧数据
     */
    private boolean migrate;

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

    public void setEncrypt(boolean encrypt, String cryptKey) {
        this.encrypt = encrypt;
        this.cryptKey = cryptKey;
    }

    public void setMigrate(boolean migrate) {
        this.migrate = migrate;
    }

    private MMKV getMMKV() {
        return getMMKV(null);
    }

    private SharedPreferences getOldPreferences(String name) {
        if (TextUtils.isEmpty(name)) {
            return PreferenceManager.getDefaultSharedPreferences(mContext);
        } else {
            return mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
    }

    private MMKV getMMKV(String name) {
        MMKV mmkv;
        if (TextUtils.isEmpty(name)) {
            mmkv = encrypt ? MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, cryptKey) : MMKV.defaultMMKV();
        } else {
            mmkv = encrypt ? MMKV.mmkvWithID(name, MMKV.MULTI_PROCESS_MODE, cryptKey) : MMKV.mmkvWithID(name, MMKV.MULTI_PROCESS_MODE);
        }
        if (migrate) {
            //迁移SharedPreferences旧数据
            SharedPreferences oldSharedPreferences = getOldPreferences(name);
            mmkv.importFromSharedPreferences(oldSharedPreferences);
            oldSharedPreferences.edit().clear().apply();
        }
        return mmkv;
    }

    public String getString(String key) {
        return getMMKV().getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        return getMMKV().getString(key, defaultValue);
    }

    public String getString(String name, String key, String defaultValue) {
        return getMMKV(name).getString(key, defaultValue);
    }

    public void putString(String key, String value) {
        getMMKV().putString(key, value);
    }

    public void putString(String name, String key, String value) {
        getMMKV(name).putString(key, value);
    }

    public boolean getBoolean(String key) {
        return getMMKV().getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return getMMKV().getBoolean(key, defaultValue);
    }

    public boolean getBoolean(String name, String key, boolean defaultValue) {
        return getMMKV(name).getBoolean(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        getMMKV().putBoolean(key, value);
    }

    public void putBoolean(String name, String key, boolean value) {
        getMMKV(name).putBoolean(key, value);
    }

    public void putInt(String key, int value) {
        getMMKV().putInt(key, value);
    }

    public void putInt(String name, String key, int value) {
        getMMKV(name).putInt(key, value);
    }

    public int getInt(String key) {
        return getMMKV().getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return getMMKV().getInt(key, defaultValue);
    }

    public int getInt(String name, String key, int defaultValue) {
        return getMMKV(name).getInt(key, defaultValue);
    }

    public void putFloat(String key, float value) {
        getMMKV().putFloat(key, value);
    }

    public void putFloat(String name, String key, float value) {
        getMMKV(name).putFloat(key, value);
    }

    public float getFloat(String key) {
        return getMMKV().getFloat(key, 0f);
    }

    public float getFloat(String key, float defaultValue) {
        return getMMKV().getFloat(key, defaultValue);
    }

    public float getFloat(String name, String key, float defaultValue) {
        return getMMKV(name).getFloat(key, defaultValue);
    }

    public void putLong(String key, long value) {
        getMMKV().putLong(key, value);
    }

    public void putLong(String name, String key, long value) {
        getMMKV(name).putLong(key, value);
    }

    public long getLong(String key) {
        return getMMKV().getLong(key, 0L);
    }

    public long getLong(String key, long defaultValue) {
        return getMMKV().getLong(key, defaultValue);
    }

    public long getLong(String name, String key, long defaultValue) {
        return getMMKV(name).getLong(key, defaultValue);
    }

    public void remove(String key) {
        getMMKV().remove(key);
    }

    public void remove(String name, String key) {
        getMMKV(name).remove(key);
    }

    public void clear() {
        getMMKV().clear();
    }

    public void clear(String name) {
        getMMKV(name).clear();
    }

    public boolean exists(String key) {
        return getMMKV().contains(key);
    }

    public boolean exists(String name, String key) {
        return getMMKV(name).contains(key);
    }
}