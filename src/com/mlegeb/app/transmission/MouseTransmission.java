package com.mlegeb.app.transmission;

import com.mlegeb.app.AppConfig;

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
