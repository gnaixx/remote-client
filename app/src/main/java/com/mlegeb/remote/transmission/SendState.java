package com.mlegeb.remote.transmission;

import com.mlegeb.remote.common.Constants;

/**
 * 名称: SendState.java
 * 描述: 状态消息传送类
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class SendState extends Transmission {
	
	public SendState() {
		super();
	}
	
	public void ChangeState(int state){
		send("state" + String.valueOf(state));
	}

}
