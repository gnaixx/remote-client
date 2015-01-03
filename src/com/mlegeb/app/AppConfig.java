package com.mlegeb.app;

import android.content.Context;

/**
 * 应用程序配置类：用于保存用户相关信息及设置
 * @author Mine
 *
 */
public class AppConfig {

	public final static int LISTENER_PORT = 7590;
	public final static String CONNECTION_STR = "link";
	public final static String RETURN_ACTION = "com.mlegeb.socket";
	
	private Context mContext;
	private static AppConfig appConfig;

	public static AppConfig getAppConfig(Context context) {
		if (appConfig == null) {
			appConfig = new AppConfig();
			appConfig.mContext = context;
		}
		return appConfig;
	}
	
	
}
