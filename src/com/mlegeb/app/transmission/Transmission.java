package com.mlegeb.app.transmission;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


import com.mlegeb.app.AppConfig;

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
	
	/**  是否打印Log */
	private final boolean isDebug = true;
	
	/** UDP消息包 */
	protected DatagramPacket packet;
	
	/** UDP接口 */
	protected DatagramSocket socket;
	
	/** 发送IP地址 */
	private InetAddress ipAddress;
	
	/**
	 * 初始化UDP发送IP
	 * @param ip
	 */
	public Transmission(String ip){
		try {
			ipAddress = InetAddress.getByName(ip);
			socket = new DatagramSocket();
			if(isDebug)	System.out.println(TAG + ":初始化");
		} catch (Exception e) {
			e.printStackTrace();
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
			packet = new DatagramPacket(data, data.length, ipAddress, AppConfig.LISTENER_PORT);
			try {
				socket.send(packet);
				if(isDebug)	System.out.println(TAG + ":发送成功");
			} catch (IOException e) {
				if(isDebug)	System.out.println(TAG + ":发送失败");
				e.printStackTrace();
			}
		}
		
	}
}
