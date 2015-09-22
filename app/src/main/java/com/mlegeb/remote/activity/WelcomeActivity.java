package com.mlegeb.remote.activity;



import com.mlegeb.remote.common.Constants;
import com.mlegeb.remote.R;
import com.mlegeb.remote.common.SettingsUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


/**
 * 名称: WelcomeActivity.java
 * 描述: 欢迎页面
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class WelcomeActivity extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 2000; // 延迟3秒  


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		ViewInit();
	}

	private void ViewInit(){
		
		Constants.mouseSensibility = Integer.valueOf(SettingsUtil.getPref(this, Constants.MOUSE_SENSI, 1));

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
//				boolean isFirst = Boolean.valueOf(SettingsUtil.getPref(WelcomeActivity.this, Constants.FIRST_START, true));
//				if (isFirst) {
//					//将登录标志位设置为false，下次登录时不在显示首次登录界面  
//					SettingsUtil.savePref(WelcomeActivity.this, Constants.FIRST_START, false);
//					
//					Intent intent=new Intent();
//					intent.setClass(WelcomeActivity.this, GuideActivity.class);
//					WelcomeActivity.this.startActivity(intent);
//					WelcomeActivity.this.finish();
//				}else{
					Intent intent=new Intent();
					intent.setClass(WelcomeActivity.this, MainActivity.class);
					WelcomeActivity.this.startActivity(intent);
					WelcomeActivity.this.finish();

//				}

			}
		},SPLASH_DISPLAY_LENGHT);
	}

}
