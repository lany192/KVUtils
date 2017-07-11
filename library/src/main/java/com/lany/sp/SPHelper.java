package com.lany.sp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;


/**
 * SharedPreferences封装工具类
 */
public class SPHelper {
    private final String TAG = "SPHelper";
    private volatile static SPHelper instance;
    private String mSpaceName;//默认存储空间名称
    private Context mContext;
    private boolean DEBUG = BuildConfig.DEBUG;

    private SPHelper() {

    }

    public static SPHelper getInstance() {
        if (instance == null) {
            synchronized (SPHelper.class) {
                if (instance == null) {
                    instance = new SPHelper();
                }
            }
        }
        return instance;
    }

    public void init(Context ctx, boolean debug) {
        init(ctx, "", debug);
    }

    public void init(Context ctx, String spaceName, boolean debug) {
        Context app = ctx.getApplicationContext();
        if (app == null) {
            this.mContext = ctx;
        } else {
            this.mContext = ((Application) app).getBaseContext();
        }
        this.mSpaceName = spaceName;
        this.DEBUG = debug;
        Log.i(TAG, "SPHelper init-------------------------------");
    }

    private SharedPreferences getSharedPreferences() {
        return getSharedPreferences(mSpaceName);
    }

    private SharedPreferences getSharedPreferences(String spaceName) {
        if (mContext == null) {
            throw new IllegalArgumentException("The context is null, please call the initialization method first!");
        }
        if (TextUtils.isEmpty(spaceName)) {
            return PreferenceManager.getDefaultSharedPreferences(mContext);
        } else {
            return mContext.getSharedPreferences(spaceName, Context.MODE_PRIVATE);
        }
    }

    public boolean exists(String key) {
        boolean result = getSharedPreferences().contains(key);
        if (DEBUG) {
            Log.i(TAG, "exists---->key:" + key + "   result:" + result);
        }
        return result;
    }

    public boolean exists(String spaceName, String key) {
        boolean result = getSharedPreferences(spaceName).contains(key);
        if (DEBUG) {
            Log.i(TAG, "exists---->key:" + key + "   spaceName:" + spaceName + "   result:" + result);
        }
        return result;
    }

    public String getString(String key) {
        String value = getSharedPreferences().getString(key, "");
        if (DEBUG) {
            Log.i(TAG, "getString---->key:" + key + "    value:" + value);
        }
        return value;
    }

    public String getString(String key, String defaultValue) {
        String value = getSharedPreferences().getString(key, defaultValue);
        if (DEBUG) {
            Log.i(TAG, "getString---->key:" + key + "    value:" + value);
        }
        return value;
    }

    public String getString(String spaceName, String key, String defaultValue) {
        String value = getSharedPreferences(spaceName).getString(key, defaultValue);
        if (DEBUG) {
            Log.i(TAG, "getString---->spaceName:" + spaceName + "    key:" + key + "    value:" + value);
        }
        return value;
    }

    public void putString(String key, String value) {
        if (DEBUG) {
            Log.i(TAG, "putString---->key:" + key + "    value:" + value);
        }
        getSharedPreferences().edit().putString(key, value).apply();
    }

    public void putString(String spaceName, String key, String value) {
        if (DEBUG) {
            Log.i(TAG, "putString---->spaceName:" + spaceName + "    key:" + key + "    value:" + value);
        }
        getSharedPreferences(spaceName).edit().putString(key, value).apply();
    }

    public boolean putStringForResult(String key, String value) {
        if (DEBUG) {
            Log.i(TAG, "putStringForResult---->key:" + key + "    value:" + value);
        }
        return getSharedPreferences().edit().putString(key, value).commit();
    }

    public boolean putStringForResult(String spaceName, String key, String value) {
        if (DEBUG) {
            Log.i(TAG, "putStringForResult---->spaceName:" + spaceName + "    key:" + key + "    value:" + value);
        }
        return getSharedPreferences(spaceName).edit().putString(key, value).commit();
    }

    public boolean getBoolean(String key) {
        boolean value = getSharedPreferences().getBoolean(key, false);
        if (DEBUG) {
            Log.i(TAG, "getBoolean---->key:" + key + "    value:" + value);
        }
        return value;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        boolean value = getSharedPreferences().getBoolean(key, defaultValue);
        if (DEBUG) {
            Log.i(TAG, "getBoolean---->key:" + key + "    value:" + value);
        }
        return value;
    }

    public boolean getBoolean(String spaceName, String key, boolean defaultValue) {
        boolean value = getSharedPreferences(spaceName).getBoolean(key, defaultValue);
        if (DEBUG) {
            Log.i(TAG, "getBoolean---->spaceName:" + spaceName + "    key:" + key + "    value:" + value);
        }
        return value;
    }

    public void putBoolean(String key, boolean value) {
        if (DEBUG) {
            Log.i(TAG, "putBoolean---->key:" + key + "    value:" + value);
        }
        getSharedPreferences().edit().putBoolean(key, value).apply();
    }

    public void putBoolean(String spaceName, String key, boolean value) {
        if (DEBUG) {
            Log.i(TAG, "putBoolean---->spaceName:" + spaceName + "    key:" + key + "    value:" + value);
        }
        getSharedPreferences(spaceName).edit().putBoolean(key, value).apply();
    }

    public boolean putBooleanForResult(String key, boolean value) {
        if (DEBUG) {
            Log.i(TAG, "putBooleanForResult---->key:" + key + "    value:" + value);
        }
        return getSharedPreferences().edit().putBoolean(key, value).commit();
    }

    public boolean putBooleanForResult(String spaceName, String key, boolean value) {
        if (DEBUG) {
            Log.i(TAG, "putBooleanForResult---->spaceName:" + spaceName + "    key:" + key + "    value:" + value);
        }
        return getSharedPreferences(spaceName).edit().putBoolean(key, value).commit();
    }

    public void putInt(String key, int value) {
        if (DEBUG) {
            Log.i(TAG, "putInt---->key:" + key + "    value:" + value);
        }
        getSharedPreferences().edit().putInt(key, value).apply();
    }

    public void putInt(String spaceName, String key, int value) {
        if (DEBUG) {
            Log.i(TAG, "putInt---->spaceName:" + spaceName + "    key:" + key + "    value:" + value);
        }
        getSharedPreferences(spaceName).edit().putInt(key, value).apply();
    }

    public boolean putIntForResult(String key, int value) {
        if (DEBUG) {
            Log.i(TAG, "putIntForResult---->key:" + key + "    value:" + value);
        }
        return getSharedPreferences().edit().putInt(key, value).commit();
    }

    public boolean putIntForResult(String spaceName, String key, int value) {
        if (DEBUG) {
            Log.i(TAG, "putIntForResult---->spaceName:" + spaceName + "    key:" + key + "    value:" + value);
        }
        return getSharedPreferences(spaceName).edit().putInt(key, value).commit();
    }

    public int getInt(String key) {
        int value = getSharedPreferences().getInt(key, 0);
        if (DEBUG) {
            Log.i(TAG, "getInt---->key:" + key + "    value:" + value);
        }
        return value;
    }

    public int getInt(String key, int defaultValue) {
        int value = getSharedPreferences().getInt(key, defaultValue);
        if (DEBUG) {
            Log.i(TAG, "getInt---->key:" + key + "    value:" + value);
        }
        return value;
    }

    public int getInt(String spaceName, String key, int defaultValue) {
        int value = getSharedPreferences(spaceName).getInt(key, defaultValue);
        if (DEBUG) {
            Log.i(TAG, "getInt---->key:" + key + "    value:" + value);
        }
        return value;
    }

    public void putFloat(String key, float value) {
        if (DEBUG) {
            Log.i(TAG, "putFloat---->key:" + key + "    value:" + value);
        }
        getSharedPreferences().edit().putFloat(key, value).apply();
    }

    public void putFloat(String spaceName, String key, float value) {
        if (DEBUG) {
            Log.i(TAG, "putFloat---->spaceName:" + spaceName + "    key:" + key + "    value:" + value);
        }
        getSharedPreferences(spaceName).edit().putFloat(key, value).apply();
    }

    public boolean putFloatForResult(String key, float value) {
        if (DEBUG) {
            Log.i(TAG, "putFloatForResult---->key:" + key + "    value:" + value);
        }
        return getSharedPreferences().edit().putFloat(key, value).commit();
    }

    public boolean putFloatForResult(String spaceName, String key, float value) {
        if (DEBUG) {
            Log.i(TAG, "putFloatForResult---->spaceName:" + spaceName + "    key:" + key + "    value:" + value);
        }
        return getSharedPreferences(spaceName).edit().putFloat(key, value).commit();
    }

    public float getFloat(String key) {
        float value = getSharedPreferences().getFloat(key, 0f);
        if (DEBUG) {
            Log.i(TAG, "getFloat---->key:" + key + "    value:" + value);
        }
        return value;
    }

    public float getFloat(String key, float defaultValue) {
        float value = getSharedPreferences().getFloat(key, defaultValue);
        if (DEBUG) {
            Log.i(TAG, "getFloat---->key:" + key + "    value:" + value);
        }
        return value;
    }

    public float getFloat(String spaceName, String key, float defaultValue) {
        float value = getSharedPreferences(spaceName).getFloat(key, defaultValue);
        if (DEBUG) {
            Log.i(TAG, "getFloat---->spaceName:" + spaceName + "    key:" + key + "    value:" + value);
        }
        return value;
    }

    public void putLong(String key, long value) {
        if (DEBUG) {
            Log.i(TAG, "putLong---->key:" + key + "    value:" + value);
        }
        getSharedPreferences().edit().putLong(key, value).apply();
    }

    public void putLong(String spaceName, String key, long value) {
        if (DEBUG) {
            Log.i(TAG, "putLong---->spaceName:" + spaceName + "    key:" + key + "    value:" + value);
        }
        getSharedPreferences(spaceName).edit().putLong(key, value).apply();
    }

    public boolean putLongForResult(String key, long value) {
        if (DEBUG) {
            Log.i(TAG, "putLongForResult---->key:" + key + "    value:" + value);
        }
        return getSharedPreferences().edit().putLong(key, value).commit();
    }

    public boolean putLongForResult(String spaceName, String key, long value) {
        if (DEBUG) {
            Log.i(TAG, "putLongForResult---->spaceName:" + spaceName + "    key:" + key + "    value:" + value);
        }
        return getSharedPreferences(spaceName).edit().putLong(key, value).commit();
    }

    public long getLong(String key) {
        long value = getSharedPreferences().getLong(key, 0L);
        if (DEBUG) {
            Log.i(TAG, "getLong---->key:" + key + "    value:" + value);
        }
        return value;
    }

    public long getLong(String key, long defaultValue) {
        long value = getSharedPreferences().getLong(key, defaultValue);
        if (DEBUG) {
            Log.i(TAG, "getLong---->key:" + key + "    value:" + value);
        }
        return value;
    }

    public long getLong(String spaceName, String key, long defaultValue) {
        long value = getSharedPreferences(spaceName).getLong(key, defaultValue);
        if (DEBUG) {
            Log.i(TAG, "getLong---->spaceName:" + spaceName + "    key:" + key + "    value:" + value);
        }
        return value;
    }

    public void clearSharedPreferences() {
        if (DEBUG) {
            Log.i(TAG, "clearSharedPreferences");
        }
        Editor editor = getSharedPreferences().edit();
        editor.clear();
        editor.apply();
    }

    public void clearSharedPreferences(String spaceName) {
        if (DEBUG) {
            Log.i(TAG, "clearSharedPreferences---->spaceName:" + spaceName);
        }
        Editor editor = getSharedPreferences(spaceName).edit();
        editor.clear();
        editor.apply();
    }
}