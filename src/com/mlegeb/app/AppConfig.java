package com.mlegeb.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 应用程序配置类：用于保存用户相关信息及设置
 * @author Mine
 *
 */
public class AppConfig {

	public final static int LISTENER_PORT = 7590;
	public final static String CONNECTION_STR = "link";
	public final static String RETURN_ACTION = "com.mlegeb.socket";
	public final static String IP_DATA = "perf_ipaddress";
	
	private Context mContext;
	private static AppConfig appConfig;

	public static AppConfig getAppConfig(Context context) {
		if (appConfig == null) {
			appConfig = new AppConfig();
			appConfig.mContext = context;
		}
		return appConfig;
	}
	
	/**
	 * 获取Preference设置
	 * @param context
	 * @return
	 */
	public static SharedPreferences getSharedPrefernces(Context context, String name){
		return context.getApplicationContext().getSharedPreferences(name, 
				Context.MODE_PRIVATE);
	}
	
	
}
