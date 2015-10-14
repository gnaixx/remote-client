package com.mlegeb.remote.netwrok.transmission;

import com.mlegeb.remote.common.Constants;

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
		super();
	}

	public void sendMusicButton(int type){
		int state = Constants.MUSIC_STATE;
		String data = state + ":" + type;
		send(data);
	}
}
