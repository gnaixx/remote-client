package com.mlegeb.remote.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.mlegeb.remote.common.Constants;
import com.mlegeb.remote.common.LogUtil;
import com.mlegeb.remote.model.Settings;

/**
 * 名称: RemoteApplication.java
 * 描述: 全局应用程序类：用于保存和调用全局应用配置
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class RemoteApplication extends Application{
    //单例
    private static RemoteApplication instance = null;
    //pre
    private SharedPreferences preferences = null;
    //settings
    private Settings settings;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    /**
     * 获取单例
     * @return
     */
    public static RemoteApplication getInstance(){
        return instance;
    }

    /**
     * 获取配置
     * @return
     */
    public Settings getSettings(){
        if(settings == null){
            readSettins();
        }
        return settings;
    }

    /**
     * 读取配置文件
     */
    public void readSettins(){
        settings = new Settings();
        if(preferences == null) {
            getPref();
        }
        settings.setIpAddress(preferences.getString(Constants.IP_ADDR, Settings.DEFAULT_IP));
        settings.setIsFristOpen(preferences.getBoolean(Constants.IS_FIRST_OPEN, Settings.DEFAULT_iS_FRIST_OPEN));
        settings.setMouseSensibility(preferences.getInt(Constants.MOUSE_SENSI, Settings.DEFAULT_MOUSE_SENSI));

        LogUtil.d(this, "ip:"+ settings.getIpAddress() + ",frist");
    }

    /**
     * 写入配置文件
     */
    public void writeSettings(){
        if(preferences == null) {
            getPref();
        }
        if(settings != null){
            SharedPreferences.Editor mEditor = preferences.edit();
            mEditor.putString(Constants.IP_ADDR, settings.getIpAddress());
            mEditor.putBoolean(Constants.IS_FIRST_OPEN, settings.isFristOpen());
            mEditor.putInt(Constants.MOUSE_SENSI, settings.getMouseSensibility());
            mEditor.apply();
        }
    }

    /**
     * 获取 SharedPreferences
     * @return
     */
    private SharedPreferences getPref(){
        if(preferences == null){
            preferences = getSharedPreferences(Constants.PREF_DATA, Context.MODE_PRIVATE);
        }
        return preferences;
    }
}
