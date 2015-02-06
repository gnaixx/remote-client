package com.mlegeb.app;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * 名称: AppConfig.java
 * 描述: 应用程序配置类-用于保存用户相关信息及设置
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class AppConfig {

	public final static int LISTENER_PORT = 7590;
	public final static String CONNECTION_STR = "link";
	public final static String RETURN_ACTION = "com.mlegeb.socket";
	public final static String PREF_DATA = "pref_data";
	public final static String FIRST_START_PAGE_1 = "first_start_1";
	public final static String FIRST_START_PAGE_2 = "first_start_2";
	public final static String IP_ADDR = "ip_addr";
	public final static String DEF_IP_ADDR = "192.168.6.114";
	public final static String MOUSE_SENSI = "mouse_sensi";
	
	
	public final static int INIT_STATE     = 0;  //初始化
	public final static int GAME_STATE     = 1;  //手柄模式
	public final static int INPUT_STATE    = 2;  //输入模式
	public final static int MOUSE_STATE    = 3;  //鼠标模式
	public final static int MUSIC_STATE    = 4;  //播放器模式
	public final static int PPT_STATE      = 5;  //PPT模式
	public final static int SHUTDOWN_STATE = 6;  //关机模式
	public final static int WINDOW_STATE   = 7;  //窗口模式
	
	public static String conn_address = "";    
	public static int mouseSensibility = 1;
	
	@SuppressWarnings("unused")
	private Context mContext;
	private static AppConfig appConfig;

	
	/**
	 * 单例模式
	 * @param context
	 * @return
	 */
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
