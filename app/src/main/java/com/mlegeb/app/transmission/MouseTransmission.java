package com.mlegeb.app.transmission;

import com.mlegeb.app.AppConfig;

/**
 * 名称: MouseTransmission.java
 * 描述: 鼠标模式消息传送类
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class MouseTransmission extends Transmission {

	public MouseTransmission() {
		super(AppConfig.conn_address);
	}
	
	public void sendMousePoint(float x, float y){	
		int state = AppConfig.MOUSE_STATE;
		String data = state + ":0" + x + "," + y; 
		send(data);
	}
	
	public void sendMouseButton(int type){
		int state = AppConfig.MOUSE_STATE;
		String data = state + ":" + type;
		send(data);
	}

}
