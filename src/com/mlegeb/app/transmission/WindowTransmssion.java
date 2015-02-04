package com.mlegeb.app.transmission;

import com.mlegeb.app.AppConfig;

/**
 * 名称: WindowTransmssion.java
 * 描述: 窗口模式消息传送类
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class WindowTransmssion extends Transmission {

	public WindowTransmssion() {
		super(AppConfig.conn_address);
	}
	
	public void sendWindowControl(String data){
		send(AppConfig.WINDOW_STATE + ":" + data);
	}

}
