package com.mlegeb.app.transmission;

import com.mlegeb.app.AppConfig;

public class SendState extends Transmission {
	
	public SendState() {
		super(AppConfig.conn_address);
	}
	
	public void ChangeState(int state){
		send("state" + String.valueOf(state));
	}

}
