package com.mlegeb.remote.activity;


import com.mlegeb.remote.common.Constants;
import com.mlegeb.remote.R;
import com.mlegeb.remote.common.LogUtil;
import com.mlegeb.remote.common.SettingsUtil;

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

		initViews();
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_setting;
	}

	private void initViews(){
		mouseSeek = (SeekBar) findViewById(R.id.mouse_sen);
		mouseSeek.setProgress(Constants.mouseSensibility);
		
		mouseSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				mouseProgress = progress;
				Constants.mouseSensibility = progress;
				 LogUtil.d(SettingActivity.this, String.valueOf(mouseProgress));
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				 LogUtil.d(SettingActivity.this, "progressStartTrackingTouch");
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				SettingsUtil.savePref(SettingActivity.this, Constants.MOUSE_SENSI, mouseProgress);
				LogUtil.d(SettingActivity.this, "progressStopTrackingTouch");
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
