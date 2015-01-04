package com.mlegeb.app.transmission;

public class CheckConnection extends Transmission {

	public CheckConnection(String ip) {
		super(ip);
	}
	
	public void sendCheck(String data){
		send(data);
	}

}
