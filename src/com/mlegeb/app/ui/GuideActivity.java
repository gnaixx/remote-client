package com.mlegeb.app.ui;

import com.mlegeb.appclient.R;





import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class GuideActivity extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 3000; // 延迟3秒  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);

		initView();
	}

	private void initView(){
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				Intent intent=new Intent();
				intent.setClass(GuideActivity.this, MainActivity.class);
				GuideActivity.this.startActivity(intent);
				GuideActivity.this.finish();
			}
		},SPLASH_DISPLAY_LENGHT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.guide, menu);
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
