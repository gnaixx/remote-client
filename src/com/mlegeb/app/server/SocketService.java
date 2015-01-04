package com.mlegeb.app.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import com.mlegeb.app.AppConfig;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class SocketService extends Service {
	
	private final static String TAG = "soketService";
	private final static boolean isDebug = true;

	private DatagramSocket inSocket;
	private MyHandler myHandler;

	@Override
	public void onCreate() {
		super.onCreate();
		myHandler = new MyHandler();
		try {
			this.inSocket = new DatagramSocket(AppConfig.LISTENER_PORT);
		} catch (SocketException e) {
			Toast.makeText(this, "无法创建Socket",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new Thread(new SocketListener()).start();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	
	class SocketListener implements Runnable{

		@Override
		public void run() {
			Message message = Message.obtain();
			try{
				byte[] buf = new byte[1024];
				DatagramPacket op = new DatagramPacket(buf, buf.length);
				SocketService.this.inSocket.setSoTimeout(2000);
				SocketService.this.inSocket.receive(op);
				String receiveStr = new String(buf).trim();
				if(isDebug) System.out.println(TAG + receiveStr);
				if(receiveStr.equals("Successful")){
					if(isDebug) System.out.println(TAG + ":conntection");
					message.what = 1;
					myHandler.sendMessage(message);
					return;
				}
				
			}catch(Exception e){
				message.what = 0;
				myHandler.sendMessage(message);
				if(isDebug) System.out.println(TAG + ":connection fail");
				e.printStackTrace();
			}
		}
	}
	
	@SuppressLint("HandlerLeak")
	class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Intent intent = new Intent(AppConfig.RETURN_ACTION);
			if(msg.what == 1){
				intent.putExtra("result", true);
			}
			else {
				intent.putExtra("result", false);
			}
			sendBroadcast(intent);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if(isDebug) System.out.println(TAG + ":destroy");
		this.inSocket.close();
	}
}
