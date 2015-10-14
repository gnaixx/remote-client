package com.mlegeb.remote.netwrok.transmission;

import com.mlegeb.remote.common.Constants;

/**
 * 名称: GameTransmission.java
 * 描述: 手柄模式 消息传送类
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class GameTransmission extends Transmission {

	public GameTransmission() {
		super();
	}
	
	public void sendGameButton(String type){
		int state = Constants.GAME_STATE;
		String data = state + ":" + type;
		send(data);
	}

}
