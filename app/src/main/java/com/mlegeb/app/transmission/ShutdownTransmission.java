package com.mlegeb.app.transmission;

import com.mlegeb.app.AppConfig;

/**
 * 名称: ShutdownTransmission.java
 * 描述: 关机模式消息传送类
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class ShutdownTransmission extends Transmission {

	public ShutdownTransmission() {
		super(AppConfig.conn_address);
	}
	
	public void sendCommand(String data){
		send(AppConfig.SHUTDOWN_STATE + ":" + data);
	}

}
