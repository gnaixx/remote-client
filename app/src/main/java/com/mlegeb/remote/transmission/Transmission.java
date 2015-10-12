package com.mlegeb.remote.transmission;

import android.text.TextUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


import com.mlegeb.remote.application.RemoteApplication;
import com.mlegeb.remote.common.Constants;
import com.mlegeb.remote.common.LogUtil;

/**
 * 名称: Transmission.java
 * 描述: 消息传送基类
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class Transmission {

	/** log标签 */
	private final String TAG = "Transmission";
	
	/** UDP消息包 */
	protected DatagramPacket packet;
	
	/** UDP接口 */
	protected DatagramSocket socket;
	
	/** 发送IP地址 */
	private InetAddress ipAddress;
	
	/**
	 * 初始化UDP发送IP
	 */
	public Transmission(){
		String ipStr = RemoteApplication.getInstance().getSettings().getIpAddress();
		if(TextUtils.isEmpty(ipStr)){
			LogUtil.d(TAG, "ip address is null");
		}else {
			try {
				ipAddress = InetAddress.getByName(ipStr);
				socket = new DatagramSocket();
				LogUtil.d(TAG, "初始化Socket");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 发送数据
	 * @param msg
	 */
	protected void send(String msg){
		new Thread(new SendMsg(msg)).start();
	}
	
	/**
	 * 名称: Transmission.java
	 * 描述: 发送线程实现
	 *
	 * @author a_xiang
	 * @version v1.0
	 * @created 2015年2月4日
	 */
	class SendMsg implements Runnable{

		private String msg;
		public SendMsg(String msg){
			this.msg  = msg;
		}
		
		@Override
		public void run() {
			byte[] data = msg.getBytes();
			packet = new DatagramPacket(data, data.length, ipAddress, Constants.LISTENER_PORT);
			try {
				socket.send(packet);
				LogUtil.d(TAG, "发送数据：" + msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
