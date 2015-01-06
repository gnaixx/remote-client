package com.mlegeb.app.ui;


import com.mlegeb.app.AppConfig;
import com.mlegeb.app.R;
import com.mlegeb.app.transmission.SendState;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MenuActivity extends BaseActivity implements OnClickListener{

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
		setContentView(R.layout.activity_menu);
		
		sendState = new SendState();
		
		initView();
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
			sendState.ChangeState(AppConfig.MOUSE_STATE);
			intent.setClass(this, MouseActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButton2:
			sendState.ChangeState(AppConfig.PPT_STATE);
			intent.setClass(this, PowerPointActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButton3:
			sendState.ChangeState(AppConfig.INPUT_STATE);
			intent.setClass(this, InputActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButton4:
			sendState.ChangeState(AppConfig.SHUTDOWN_STATE);
			intent.setClass(this, ShutdownActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButton5:
			sendState.ChangeState(AppConfig.GAME_STATE);
			intent.setClass(this, GameActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButton6:
			sendState.ChangeState(AppConfig.MUSIC_STATE);
			intent.setClass(this, MusicActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButton7:
			sendState.ChangeState(AppConfig.WINDOW_STATE);
			intent.setClass(this, WindowActivity.class);
			startActivity(intent);
			break;
		case R.id.imageButton8:
			sendState.ChangeState(AppConfig.INIT_STATE);
			intent.setClass(this, MoreActivity.class);
			startActivity(intent);
			break;
		}
		
	}
}
