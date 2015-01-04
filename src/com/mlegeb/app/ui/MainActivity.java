package com.mlegeb.app.ui;

import com.mlegeb.app.AppConfig;
import com.mlegeb.app.server.SocketService;
import com.mlegeb.appclient.R;

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

public class MainActivity extends BaseActivity implements OnClickListener{

	private DoubleClickExitHelper mDoubleClickExitHelper;

	private ServerReceiver receiver;
	private Button connBtn;
	private Button settingsBtn;
	private Button aboutBtn;
	private EditText serverIpEdit;
	private String serverIpStr = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mDoubleClickExitHelper = new DoubleClickExitHelper(this);

		initViews();
		registerServerReceiver();
	}

	private void initViews(){
		serverIpEdit = (EditText) findViewById(R.id.editText1);

		connBtn = (Button) findViewById(R.id.button1);
		settingsBtn = (Button) findViewById(R.id.button2);
		aboutBtn = (Button) findViewById(R.id.button3);

		connBtn.setOnClickListener(this);
		settingsBtn.setOnClickListener(this);
		aboutBtn.setOnClickListener(this);
	}

	private class ServerReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			Intent intentAct = new Intent(MainActivity.this, MenuActivity.class);
			MainActivity.this.startActivity(intentAct);
		}

	}
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
		} else if (keyCode == KeyEvent.KEYCODE_MENU) {
			//			// 展示快捷栏&判断是否登录
			//			UIHelper.showSettingLoginOrLogout(Main.this,
			//					mGrid.getQuickAction(0));
			//			mGrid.show(fbSetting, true);
			//		} else if (keyCode == KeyEvent.KEYCODE_SEARCH) {
			//			// 展示搜索页
			//			UIHelper.showSearch(Main.this);
		} else {
			flag = super.onKeyDown(keyCode, event);
		}
		return flag;
	}

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


	private void settingEvent(){
		Intent intent = new Intent(this, SettingActivity.class);
		this.startActivity(intent);
	}

	private void connectionEvent(){
		serverIpStr = serverIpEdit.getText().toString();
		Intent intentService = new Intent(this, SocketService.class);
		this.startService(intentService);
	}

	private void aboutEvent(){
		Intent intent = new Intent(this, AboutActivity.class);
		this.startActivity(intent);
	}
}
