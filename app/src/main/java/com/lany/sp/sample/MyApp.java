package com.lany.sp.sample;

import android.app.Application;

import com.lany.sp.SPHelper;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SPHelper.getInstance().init(this, BuildConfig.DEBUG);
    }
}
