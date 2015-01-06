package com.mlegeb.app.transmission;

import com.mlegeb.app.AppConfig;

public class KeyboardTransmission extends Transmission {

	public KeyboardTransmission() {
		super(AppConfig.conn_address);
	}
	
	public void sendKeyCode(String data){
		send(data);
	}

}
