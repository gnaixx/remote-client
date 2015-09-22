package com.mlegeb.remote.transmission;

/**
 * 名称: CheckConnection.java
 * 描述: 连接 消息传送类
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class CheckConnection extends Transmission {

	public CheckConnection(String ip) {
		super(ip);
	}
	
	public void sendCheck(String data){
		send(data);
	}

}
