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
        String rootDir = MMKV.initialize(context);
        Log.i("SPUtils", "mmkv工作目录: " + rootDir);
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
            mmkv = encrypt ? MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, cryptKey) : MMKV.defaultMMKV();
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

    //---------------------------------------------------------------------------------
    public boolean encode(String key, double value) {
        return getMMKV().encode(key, value);
    }

    public double decodeDouble(String key) {
        return getMMKV().decodeDouble(key);
    }

    public double decodeDouble(String key, double defaultValue) {
        return getMMKV().decodeDouble(key, defaultValue);
    }
    //---------------------------------------------------------------------------------

    public boolean encode(String key, byte[] value) {
        return getMMKV().encode(key, value);
    }

    public byte[] decodeBytes(String key) {
        return getMMKV().decodeBytes(key);
    }

    public byte[] decodeBytes(String key, byte[] defaultValue) {
        return getMMKV().decodeBytes(key, defaultValue);
    }

    //---------------------------------------------------------------------------------

    public String getString(String key) {
        if (encrypt) {
            return getMMKV().decodeString(key, "");
        } else {
            return getMMKV().getString(key, "");
        }
    }

    public String getString(String key, String defaultValue) {
        if (encrypt) {
            return getMMKV().decodeString(key, defaultValue);
        } else {
            return getMMKV().getString(key, defaultValue);
        }
    }

    public String getString(String name, String key, String defaultValue) {
        if (encrypt) {
            return getMMKV(name).decodeString(key, defaultValue);
        } else {
            return getMMKV(name).getString(key, defaultValue);
        }
    }

    public void putString(String key, String value) {
        if (encrypt) {
            getMMKV().encode(key, value);
        } else {
            getMMKV().putString(key, value);
        }
    }

    public void putString(String name, String key, String value) {
        if (encrypt) {
            getMMKV(name).encode(key, value);
        } else {
            getMMKV(name).putString(key, value);
        }
    }

    //-----------------------------------------------------------------------------------------------
    public boolean getBoolean(String key) {
        if (encrypt) {
            return getMMKV().decodeBool(key, false);
        } else {
            return getMMKV().getBoolean(key, false);
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        if (encrypt) {
            return getMMKV().decodeBool(key, defaultValue);
        } else {
            return getMMKV().getBoolean(key, defaultValue);
        }
    }

    public boolean getBoolean(String name, String key, boolean defaultValue) {
        if (encrypt) {
            return getMMKV(name).decodeBool(key, defaultValue);
        } else {
            return getMMKV(name).getBoolean(key, defaultValue);
        }
    }

    public void putBoolean(String key, boolean value) {
        if (encrypt) {
            getMMKV().encode(key, value);
        } else {
            getMMKV().putBoolean(key, value);
        }
    }

    public void putBoolean(String name, String key, boolean value) {
        if (encrypt) {
            getMMKV(name).encode(key, value);
        } else {
            getMMKV(name).putBoolean(key, value);
        }
    }

    //-----------------------------------------------------------------------------------------------
    public void putInt(String key, int value) {
        if (encrypt) {
            getMMKV().encode(key, value);
        } else {
            getMMKV().putInt(key, value);
        }
    }

    public void putInt(String name, String key, int value) {
        if (encrypt) {
            getMMKV(name).encode(key, value);
        } else {
            getMMKV(name).putInt(key, value);
        }
    }

    public int getInt(String key) {
        if (encrypt) {
            return getMMKV().decodeInt(key, 0);
        } else {
            return getMMKV().getInt(key, 0);
        }
    }

    public int getInt(String key, int defaultValue) {
        if (encrypt) {
            return getMMKV().decodeInt(key, defaultValue);
        } else {
            return getMMKV().getInt(key, defaultValue);
        }
    }

    public int getInt(String name, String key, int defaultValue) {
        if (encrypt) {
            return getMMKV(name).decodeInt(key, defaultValue);
        } else {
            return getMMKV(name).getInt(key, defaultValue);
        }
    }

    //-----------------------------------------------------------------------------------------------
    public void putFloat(String key, float value) {
        if (encrypt) {
            getMMKV().encode(key, value);
        } else {
            getMMKV().putFloat(key, value);
        }
    }

    public void putFloat(String name, String key, float value) {
        if (encrypt) {
            getMMKV(name).encode(key, value);
        } else {
            getMMKV(name).putFloat(key, value);
        }
    }

    public float getFloat(String key) {
        if (encrypt) {
            return getMMKV().decodeFloat(key, 0f);
        } else {
            return getMMKV().getFloat(key, 0f);
        }
    }

    public float getFloat(String key, float defaultValue) {
        if (encrypt) {
            return getMMKV().decodeFloat(key, defaultValue);
        } else {
            return getMMKV().getFloat(key, defaultValue);
        }
    }

    public float getFloat(String name, String key, float defaultValue) {
        if (encrypt) {
            return getMMKV(name).decodeFloat(key, defaultValue);
        } else {
            return getMMKV(name).getFloat(key, defaultValue);
        }
    }

    //-----------------------------------------------------------------------------------------------
    public void putLong(String key, long value) {
        if (encrypt) {
            getMMKV().encode(key, value);
        } else {
            getMMKV().putLong(key, value);
        }
    }

    public void putLong(String name, String key, long value) {
        if (encrypt) {
            getMMKV(name).encode(key, value);
        } else {
            getMMKV(name).putLong(key, value);
        }
    }

    public long getLong(String key) {
        if (encrypt) {
            return getMMKV().decodeLong(key, 0L);
        } else {
            return getMMKV().getLong(key, 0L);
        }
    }

    public long getLong(String key, long defaultValue) {
        if (encrypt) {
            return getMMKV().decodeLong(key, defaultValue);
        } else {
            return getMMKV().getLong(key, defaultValue);
        }
    }

    public long getLong(String name, String key, long defaultValue) {
        if (encrypt) {
            return getMMKV(name).decodeLong(key, defaultValue);
        } else {
            return getMMKV(name).getLong(key, defaultValue);
        }
    }

    //-----------------------------------------------------------------------------------------------
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