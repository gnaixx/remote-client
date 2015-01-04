package com.mlegeb.app.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.mlegeb.app.AppConfig;

public class SavePreference {
	private final static String TAG = "savePreference";
	private final static boolean isDebug = true;

	public static void saveIpAddress(Context context, String value){
		SharedPreferences shared = AppConfig.getSharedPrefernces(context, AppConfig.IP_DATA);
		Editor edit = shared.edit();
		edit.putString("ip", value);
		edit.commit();
		if(isDebug) System.out.println(TAG + ":保存成功！");
	}
	
	public static String getIpAddress(Context context){
		SharedPreferences shared = AppConfig.getSharedPrefernces(context, AppConfig.IP_DATA);
		if(isDebug) System.out.println(TAG + ";读取成功");
		return shared.getString("ip", "192.168.1.100");
	}
}
