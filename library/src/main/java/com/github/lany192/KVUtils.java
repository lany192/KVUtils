package com.github.lany192;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.getkeepsafe.relinker.ReLinker;
import com.tencent.mmkv.MMKV;
import com.tencent.mmkv.MMKVLogLevel;

/**
 * MMKV封装工具类
 */
public class KVUtils {
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
        String root = context.getFilesDir().getAbsolutePath() + "/mmkv";
        if (android.os.Build.VERSION.SDK_INT == 19) {
            MMKV.initialize(root, libName -> ReLinker.loadLibrary(context, libName));
        } else {
            MMKV.initialize(context);
        }
    }

    /**
     * 日志等级
     */
    public void setLogLevel(MMKVLogLevel level) {
        MMKV.setLogLevel(level);
    }

    /**
     * 是否开启加密解密
     *
     * @param encrypt  是否开启
     * @param cryptKey 秘钥
     */
    public void setEncrypt(boolean encrypt, String cryptKey) {
        this.encrypt = encrypt;
        this.cryptKey = cryptKey;
    }

    /**
     * 是否迁移旧数据
     */
    public void setMigrate(boolean migrate) {
        this.migrate = migrate;
    }

    private MMKV getMMKV() {
        return getMMKV(null);
    }

    private MMKV getMMKV(String name) {
        MMKV mmkv;
        if (TextUtils.isEmpty(name)) {
            mmkv = encrypt ? MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, cryptKey) : MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, null);
        } else {
            mmkv = encrypt ? MMKV.mmkvWithID(name, MMKV.MULTI_PROCESS_MODE, cryptKey) : MMKV.mmkvWithID(name, MMKV.MULTI_PROCESS_MODE);
        }
        if (migrate) {
            //迁移SharedPreferences旧数据
            SharedPreferences sharedPreferences;
            if (TextUtils.isEmpty(name)) {
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            } else {
                sharedPreferences = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
            }
            mmkv.importFromSharedPreferences(sharedPreferences);
            sharedPreferences.edit().clear().apply();
        }
        return mmkv;
    }

    //Double类型---------------------------------------------------------------------------------
    public void putDouble(String key, double value) {
        getMMKV().encode(key, value);
    }

    public double getDouble(String key) {
        return getMMKV().decodeDouble(key);
    }

    public double getDouble(String key, double defaultValue) {
        return getMMKV().decodeDouble(key, defaultValue);
    }

    public double getDouble(String name, String key, double defaultValue) {
        return getMMKV(name).decodeDouble(key, defaultValue);
    }
    //byte[]类型---------------------------------------------------------------------------------

    public void putByte(String key, byte[] value) {
        getMMKV().encode(key, value);
    }

    public byte[] getBytes(String key) {
        return getMMKV().decodeBytes(key);
    }

    public byte[] getBytes(String key, byte[] defaultValue) {
        return getMMKV().decodeBytes(key, defaultValue);
    }

    public byte[] getBytes(String name, String key, byte[] defaultValue) {
        return getMMKV(name).decodeBytes(key, defaultValue);
    }
    //String类型---------------------------------------------------------------------------------

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

    //Boolean类型-----------------------------------------------------------------------------------------------
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

    //Int类型-----------------------------------------------------------------------------------------------
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

    //Float类型-----------------------------------------------------------------------------------------------
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

    //Long类型-----------------------------------------------------------------------------------------------
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

    //其他方法-----------------------------------------------------------------------------------------------
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

    public boolean contains(String key) {
        return getMMKV().contains(key);
    }

    public boolean contains(String name, String key) {
        return getMMKV(name).contains(key);
    }
}