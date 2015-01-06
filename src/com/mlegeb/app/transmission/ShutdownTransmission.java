package com.mlegeb.app.transmission;

import com.mlegeb.app.AppConfig;

public class ShutdownTransmission extends Transmission {

	public ShutdownTransmission() {
		super(AppConfig.conn_address);
	}
	
	public void sendCommand(String data){
		send(AppConfig.SHUTDOWN_STATE + ":" + data);
	}

}
