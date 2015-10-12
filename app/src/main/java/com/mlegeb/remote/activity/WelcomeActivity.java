package com.mlegeb.remote.activity;



import com.mlegeb.remote.common.Constants;
import com.mlegeb.remote.R;

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
public class WelcomeActivity extends BaseActivity {
	private final int SPLASH_DISPLAY_LENGHT = 2000; // 延迟3秒  


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initViews();
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_welcome;
	}

	private void initViews(){

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
					Intent intent=new Intent();
					intent.setClass(WelcomeActivity.this, MainActivity.class);
					WelcomeActivity.this.startActivity(intent);
					WelcomeActivity.this.finish();

			}
		},SPLASH_DISPLAY_LENGHT);
	}

}
