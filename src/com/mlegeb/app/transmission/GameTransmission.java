package com.mlegeb.app.transmission;

import com.mlegeb.app.AppConfig;

public class GameTransmission extends Transmission {

	public GameTransmission() {
		super(AppConfig.conn_address);
	}
	
	public void sendGameButton(int type){
		int state = AppConfig.GAME_STATE;
		String data = state + ":" + type;
		send(data);
	}

}
