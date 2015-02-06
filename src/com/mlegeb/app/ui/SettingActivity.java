package com.mlegeb.app.ui;


import com.mlegeb.app.AppConfig;
import com.mlegeb.app.R;
import com.mlegeb.app.common.LogUtil;
import com.mlegeb.app.common.SettingsUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

/**
 * 名称: SettingActivity.java
 * 描述: 设置页面
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class SettingActivity extends BaseActivity {

	private SeekBar mouseSeek;
	private int mouseProgress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		initViews();
	}
	
	private void initViews(){
		mouseSeek = (SeekBar) findViewById(R.id.mouse_sen);
		mouseSeek.setProgress(AppConfig.mouseSensibility);
		
		mouseSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				mouseProgress = progress;
				if(LogUtil.D) LogUtil.d(SettingActivity.this, String.valueOf(mouseProgress));
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				if(LogUtil.D) LogUtil.d(SettingActivity.this, "progressStartTrackingTouch");
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				SettingsUtil.savePref(SettingActivity.this, AppConfig.MOUSE_SENSI, mouseProgress);
				if(LogUtil.D) LogUtil.d(SettingActivity.this, "progressStopTrackingTouch");
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.setting, menu);
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
