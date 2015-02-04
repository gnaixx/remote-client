package com.mlegeb.app.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.mlegeb.app.AppConfig;

/**
 * 名称: SavePreference.java
 * 描述: 配置文件保存
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class SavePreference {
	/** log标签 */
	private final static String TAG = "savePreference";
	
	/**  是否打印Log */
	private final static boolean isDebug = true;

	/** 保存IP地址 */
	public static void saveIpAddress(Context context, String value){
		SharedPreferences shared = AppConfig.getSharedPrefernces(context, AppConfig.IP_DATA);
		Editor edit = shared.edit();
		edit.putString("ip", value);
		edit.commit();
		if(isDebug) System.out.println(TAG + ":保存成功！");
	}
	
	/** 读取Ip地址 */
	public static String getIpAddress(Context context){
		SharedPreferences shared = AppConfig.getSharedPrefernces(context, AppConfig.IP_DATA);
		if(isDebug) System.out.println(TAG + ";读取成功");
		return shared.getString("ip", "192.168.1.100");
	}
}
