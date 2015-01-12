package com.mlegeb.app.transmission;

import com.mlegeb.app.AppConfig;

public class PowerPointTransmission extends Transmission {

	public PowerPointTransmission() {
		super(AppConfig.conn_address);
	}
	
	public void sendPPTControl(int type){
		int state = AppConfig.PPT_STATE;
		String data = state + ":" + type;
		send(data);
	}

}
