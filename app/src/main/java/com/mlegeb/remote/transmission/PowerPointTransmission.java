package com.mlegeb.remote.transmission;

import com.mlegeb.remote.common.Constants;

/**
 * 名称: PowerPointTransmission.java
 * 描述: ppt模式消息传送类
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class PowerPointTransmission extends Transmission {

	public PowerPointTransmission() {
		super(Constants.conn_address);
	}
	
	public void sendPPTControl(int type){
		int state = Constants.PPT_STATE;
		String data = state + ":" + type;
		send(data);
	}

}
