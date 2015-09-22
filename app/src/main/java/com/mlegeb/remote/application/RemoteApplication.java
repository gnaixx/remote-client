package com.mlegeb.remote.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.mlegeb.remote.common.Constants;

/**
 * 名称: RemoteApplication.java
 * 描述: 全局应用程序类：用于保存和调用全局应用配置
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class RemoteApplication extends Application{
    private static RemoteApplication instance = null;

    private SharedPreferences sharedPreferences = null;

    private static RemoteApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    private SharedPreferences getPref(){
        if(sharedPreferences == null){
            sharedPreferences = getSharedPreferences(Constants.PREF_DATA, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }
}
