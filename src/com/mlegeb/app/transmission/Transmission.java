package com.mlegeb.app.transmission;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


import com.mlegeb.app.AppConfig;

public class Transmission {

	private final String TAG = "Transmission";
	private final boolean isDebug = true;
	
	protected DatagramPacket packet;
	protected DatagramSocket socket;
	private InetAddress ipAddress;
	
	public Transmission(String ip){
		try {
			ipAddress = InetAddress.getByName(ip);
			socket = new DatagramSocket();
			if(isDebug)	System.out.println(TAG + ":初始化");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected void send(String msg){
		new Thread(new SendMsg(msg)).start();
	}
	
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
