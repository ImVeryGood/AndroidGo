package com.newdicooker.tempetek.androidgo.com.all.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by SunPengCheng
 * on 2018/5/25
 * 邮箱：13027699936@163.com.
 * version 2.0.4
 */

public class BaseApplication extends Application {
    public static Context mContext;
    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        if (baseApplication == null) {
            baseApplication = this;
        }
    }

    public  Context getContext() {
        if (mContext == null) {
            mContext = getInstance().getApplicationContext();
        }
        return mContext;
    }

    public static Application getInstance() {
        return baseApplication;
    }
}
