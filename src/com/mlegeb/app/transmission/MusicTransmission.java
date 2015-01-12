package com.mlegeb.app.transmission;

import com.mlegeb.app.AppConfig;

public class MusicTransmission extends Transmission {

	public MusicTransmission() {
		super(AppConfig.conn_address);
	}

	public void sendMusicButton(int type){
		int state = AppConfig.MUSIC_STATE;
		String data = state + ":" + type;
		send(data);
	}
}
