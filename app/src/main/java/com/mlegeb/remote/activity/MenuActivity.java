package com.mlegeb.remote.activity;


import com.mlegeb.remote.common.Constants;
import com.mlegeb.remote.R;
import com.mlegeb.remote.common.SettingsUtil;
import com.mlegeb.remote.common.ViewUtil;
import com.mlegeb.remote.transmission.SendState;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * 名称: MenuActivity.java
 * 描述: 菜单页面
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class MenuActivity extends BaseActivity implements OnClickListener{

	/** 不同模式按钮 */
	private ImageButton mouseBtn;
	private ImageButton pptBtn;
	private ImageButton inputBtn;
	private ImageButton shutdownBtn;
	private ImageButton gameBtn;
	private ImageButton musicBtn;
	private ImageButton windowBtn;
	private ImageButton moreBtn;
	
	private SendState sendState;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		sendState = new SendState();
		
		initView();
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_menu;
	}

	private void initView(){
		mouseBtn = (ImageButton) findViewById(R.id.imageButton1);
		pptBtn = (ImageButton) findViewById(R.id.imageButton2);
		inputBtn = (ImageButton) findViewById(R.id.imageButton3);
		shutdownBtn = (ImageButton) findViewById(R.id.imageButton4);
		gameBtn = (ImageButton) findViewById(R.id.imageButton5);
		musicBtn = (ImageButton) findViewById(R.id.imageButton6);
		windowBtn = (ImageButton) findViewById(R.id.imageButton7);
		moreBtn = (ImageButton) findViewById(R.id.imageButton8);
		
		mouseBtn.setOnClickListener(this);
		pptBtn.setOnClickListener(this);
		inputBtn.setOnClickListener(this);
		shutdownBtn.setOnClickListener(this);
		gameBtn.setOnClickListener(this);
		musicBtn.setOnClickListener(this);
		windowBtn.setOnClickListener(this);
		moreBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		
		switch (v.getId()) {
		case R.id.imageButton1:
			sendState.ChangeState(Constants.MOUSE_STATE);
			intent.setClass(this, MouseActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButton2:
			sendState.ChangeState(Constants.PPT_STATE);
			intent.setClass(this, PowerPointActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButton3:
			sendState.ChangeState(Constants.INPUT_STATE);
			intent.setClass(this, InputActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButton4:
			sendState.ChangeState(Constants.SHUTDOWN_STATE);
			intent.setClass(this, ShutdownActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButton5:
			sendState.ChangeState(Constants.GAME_STATE);
			intent.setClass(this, GameActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButton6:
			sendState.ChangeState(Constants.MUSIC_STATE);
			intent.setClass(this, MusicActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButton7:
			sendState.ChangeState(Constants.WINDOW_STATE);
			intent.setClass(this, WindowActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButton8:
			sendState.ChangeState(Constants.INIT_STATE);
			intent.setClass(this, MoreActivity.class);
			startActivity(intent);
			break;
		}
		
	}
	
	/**
	 * 第一次运行时调用
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		boolean isFirst = 
				Boolean.valueOf(SettingsUtil.getPref(MenuActivity.this,
						Constants.FIRST_START_PAGE_2, true));
		if(isFirst){
			Intent intent = new Intent(MenuActivity.this, GuideActivity.class);
			intent.putExtra("ArrayPoints",
					ViewUtil.guidePoints(mouseBtn));
			startActivity(intent);
			//将登录标志位设置为false，下次登录时不在显示首次登录界面  
			SettingsUtil.savePref(MenuActivity.this, 
					Constants.FIRST_START_PAGE_2, false);
			isFirst = false;
		}
	}
}
