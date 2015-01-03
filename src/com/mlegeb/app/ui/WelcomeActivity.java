package com.mlegeb.app.ui;

import com.mlegeb.appclient.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class WelcomeActivity extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 2000; // 延迟3秒  
	private final String SHARED_NAME = "bootsetting";
	private SharedPreferences preferences;  
	private Editor editor;  


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		ViewInit();
	}

	private void ViewInit(){
		preferences = getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);


		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (preferences.getBoolean("firststart", true)) {
					editor = preferences.edit();  
					//将登录标志位设置为false，下次登录时不在显示首次登录界面  
					editor.putBoolean("firststart", false);  
					editor.commit(); 

					Intent intent=new Intent();
					intent.setClass(WelcomeActivity.this, GuideActivity.class);
					WelcomeActivity.this.startActivity(intent);
					WelcomeActivity.this.finish();
				}else{
					Intent intent=new Intent();
					intent.setClass(WelcomeActivity.this, MainActivity.class);
					WelcomeActivity.this.startActivity(intent);
					WelcomeActivity.this.finish();

				}

			}
		},SPLASH_DISPLAY_LENGHT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
