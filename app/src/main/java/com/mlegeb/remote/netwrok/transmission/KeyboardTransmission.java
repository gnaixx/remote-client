package com.mlegeb.remote.netwrok.transmission;

/**
 * 名称: KeyboardTransmission.java
 * 描述: 键盘模式消息传送类
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class KeyboardTransmission extends Transmission {

	public KeyboardTransmission() {
		super();
	}
	
	public void sendKeyCode(String data){
		send(data);
	}

}
