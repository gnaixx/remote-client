package com.mlegeb.remote.netwrok.transmission;

import com.mlegeb.remote.common.Constants;

/**
 * 名称: WindowTransmssion.java
 * 描述: 窗口模式消息传送类
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class WindowTransmssion extends Transmission {

	public WindowTransmssion() {
		super();
	}
	
	public void sendWindowControl(String data){
		send(Constants.WINDOW_STATE + ":" + data);
	}

}
