package com.mlegeb.app.transmission;

import com.mlegeb.app.AppConfig;

public class WindowTransmssion extends Transmission {

	public WindowTransmssion() {
		super(AppConfig.conn_address);
	}
	
	public void sendWindowControl(String data){
		send(AppConfig.WINDOW_STATE + ":" + data);
	}

}
