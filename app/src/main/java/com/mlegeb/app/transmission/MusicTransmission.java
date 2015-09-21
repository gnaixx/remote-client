package com.mlegeb.app.transmission;

import com.mlegeb.app.AppConfig;

/**
 * 名称: MusicTransmission.java
 * 描述: 音乐模式消息传送类
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
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
