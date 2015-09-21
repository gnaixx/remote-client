package com.mlegeb.app.common;

import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.mlegeb.app.AppConfig;

/**
 * 名称: SettingsUtil.java
 * 描述: 配置文件保存
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class SettingsUtil {

	/** 保存配置信息 */
	@SuppressWarnings("rawtypes")
	public static void savePref(Context context, Map<String, String> map){
		SharedPreferences shared = AppConfig.getSharedPrefernces(context, AppConfig.PREF_DATA);
		Editor edit = shared.edit();
		Iterator iter = map.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    edit.putString((String)entry.getKey(), (String)entry.getValue());
		}
		edit.commit();
		LogUtil.d(context, "配置信息保存成功！");
	}
	
	/** 保存配置信息 -单条*/
	public static void savePref(Context context, String key, Object value){
		SharedPreferences shared = AppConfig.getSharedPrefernces(context, AppConfig.PREF_DATA);
		Editor edit = shared.edit();
		edit.putString(key, value.toString());
		edit.commit();
		LogUtil.d(context, "配置信息保存成功！");
	}
	
	/** 读取配置信息 */
	public static String getPref(Context context, String key, Object defValue){
		String value = null;
		SharedPreferences shared = AppConfig.getSharedPrefernces(context, AppConfig.PREF_DATA);
		LogUtil.d(context,"配置信息读取成功！");	
		value = shared.getString(key, defValue.toString());
		return value;
	}
	
}
