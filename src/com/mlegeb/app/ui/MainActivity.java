package com.mlegeb.app.ui;

import java.util.HashMap;
import java.util.Map;

import com.mlegeb.app.AppConfig;
import com.mlegeb.app.R;
import com.mlegeb.app.common.DoubleClickExitHelper;
import com.mlegeb.app.common.LogUtil;
import com.mlegeb.app.common.SettingsUtil;
import com.mlegeb.app.common.ViewUtil;
import com.mlegeb.app.server.SocketService;
import com.mlegeb.app.transmission.CheckConnection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 名称: MainActivity.java
 * 描述: 登入页面
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class MainActivity extends BaseActivity implements OnClickListener{

	/** 双击工具类 */
	private DoubleClickExitHelper mDoubleClickExitHelper;
	
	/** 广播接收者*/
	private ServerReceiver receiver;
	
	/** 连接按钮 */
	private Button connBtn;
	
	/** 设置按钮 */
	private Button settingsBtn;
	
	/** 关于页面 */
	private Button aboutBtn;
	
	/** 输入框 */
	private EditText serverIpEdit;
	
	/** IP地址 */
	private String serverIpStr = "";
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mDoubleClickExitHelper = new DoubleClickExitHelper(this);

		initViews();
		//动态注册广播
		registerServerReceiver();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//注销广播
		this.unregisterReceiver(receiver);
	}

	/**
	 * 初始化变量
	 */
	private void initViews(){
		serverIpEdit = (EditText) findViewById(R.id.editText1);

		connBtn = (Button) findViewById(R.id.button1);
		settingsBtn = (Button) findViewById(R.id.button2);
		aboutBtn = (Button) findViewById(R.id.button3);

		connBtn.setOnClickListener(this);
		settingsBtn.setOnClickListener(this);
		aboutBtn.setOnClickListener(this);
		
		//读取保存的IP地址
		serverIpEdit.setText(SettingsUtil.getPref(this, AppConfig.IP_ADDR, AppConfig.DEF_IP_ADDR).toString());
		
		//读取保存的鼠标灵敏度
		AppConfig.mouseSensibility = Integer.parseInt(SettingsUtil.getPref(this, AppConfig.MOUSE_SENSI, 1));
		LogUtil.d("TTTTTT", AppConfig.mouseSensibility+"");
	}

	/**
	 * 名称: MainActivity.java
	 * 描述: 广播接收者
	 *
	 * @author a_xiang
	 * @version v1.0
	 * @created 2015年2月4日
	 */
	private class ServerReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent.getAction().equals(AppConfig.RETURN_ACTION)){
				if(intent.getBooleanExtra("result", false)){
					Toast.makeText(MainActivity.this, "连接成功！", Toast.LENGTH_LONG).show();
					AppConfig.conn_address = serverIpStr;
					
					//保存IP地址
					SettingsUtil.savePref(MainActivity.this, AppConfig.IP_ADDR, AppConfig.conn_address);
					//跳转到菜单页面
					Intent intentAct = new Intent(MainActivity.this, MenuActivity.class);
					MainActivity.this.startActivity(intentAct);
				}
				else{
					Toast.makeText(MainActivity.this, "连接失败！", Toast.LENGTH_LONG).show();
				}
			}
		}

	}
	
	/**
	 * 注册广播
	 */
	private void registerServerReceiver(){
		IntentFilter filter = new IntentFilter(AppConfig.RETURN_ACTION);
		receiver = new ServerReceiver();
		registerReceiver(receiver, filter);
	}

	/**
	 * 监听返回--是否退出程序
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean flag = true;
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 是否退出应用
			return mDoubleClickExitHelper.onKeyDown(keyCode, event);
		}else {
			flag = super.onKeyDown(keyCode, event);
		}
		return flag;
	}
	
	/**
	 * 点击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {  
		case R.id.button1:  
			connectionEvent();
			break;  
		case R.id.button2:  
			settingEvent();
			break;  
		case R.id.button3:
			aboutEvent();
			break;
		}  

	}


	/**
	 * 跳转到设置页面
	 */
	private void settingEvent(){
		Intent intent = new Intent(this, SettingActivity.class);
		this.startActivity(intent);
	}

	/**
	 * 开启服务监听UDP，发送连接请求
	 */
	private void connectionEvent(){
		
		serverIpStr = serverIpEdit.getText().toString();
		
		//开启服务  监听端口
		Intent intentService = new Intent(this, SocketService.class);
		this.startService(intentService);

		CheckConnection check = new CheckConnection(serverIpStr);
		check.sendCheck(AppConfig.CONNECTION_STR);

	}

	/**
	 * 跳转到相关页面
	 */
	private void aboutEvent(){
		Intent intent = new Intent(this, AboutActivity.class);
		this.startActivity(intent);
	}
	
	/**
	 * 第一次运行时调用
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		boolean isFirst = 
				Boolean.valueOf(SettingsUtil.getPref(MainActivity.this,
						AppConfig.FIRST_START_PAGE_1, true));
		if(isFirst){
			Intent intent = new Intent(MainActivity.this, GuideActivity.class);
			intent.putExtra("ArrayPoints",
					ViewUtil.guidePoints(serverIpEdit));
			startActivity(intent);
			//将登录标志位设置为false，下次登录时不在显示首次登录界面  
			SettingsUtil.savePref(MainActivity.this, 
					AppConfig.FIRST_START_PAGE_1, false);
			isFirst = false;
		}
	}
	
	
}
