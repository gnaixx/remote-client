package com.mlegeb.remote.activity;

import com.mlegeb.remote.R;
import com.mlegeb.remote.transmission.MusicTransmission;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * 名称: MusicActivity.java
 * 描述: 播放器页面
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class MusicActivity extends BaseActivity implements OnClickListener{

	/** 播放器按钮 */
	private ImageButton playBtn;
	private ImageButton rewindBtn;
	private ImageButton forwardBtn;
	private ImageButton upBtn;
	private ImageButton closeBtn;
	private ImageButton downBtn;

	private MusicTransmission transmission;

	private static boolean isPlay = false;
	private static boolean isOpen = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);

		transmission = new MusicTransmission();
		initView();
	}

	private void initView(){
		playBtn    = (ImageButton) findViewById(R.id.imageButton2);
		rewindBtn  = (ImageButton) findViewById(R.id.imageButton1);
		forwardBtn = (ImageButton) findViewById(R.id.imageButton3);
		upBtn      = (ImageButton) findViewById(R.id.imageButton5);
		closeBtn   = (ImageButton) findViewById(R.id.imageButton4);
		downBtn    = (ImageButton) findViewById(R.id.imageButton6);
		
		playBtn.setOnClickListener(this);
		rewindBtn.setOnClickListener(this);
		forwardBtn.setOnClickListener(this);
		upBtn.setOnClickListener(this);
		closeBtn.setOnClickListener(this);
		downBtn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.music, menu);
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageButton1:
			transmission.sendMusicButton(2);
			break;
		case R.id.imageButton2:
			transmission.sendMusicButton(0);
			isPlay();
			break;
		case R.id.imageButton3:
			transmission.sendMusicButton(1);
			break;
		case R.id.imageButton4:
			isClose();
			transmission.sendMusicButton(5);
			break;
		case R.id.imageButton5:
			transmission.sendMusicButton(3);
			break;
		case R.id.imageButton6:
			transmission.sendMusicButton(4);
			break;
		}
	}
	
	private void isPlay(){
		if(!isPlay){
			playBtn.setImageResource(R.drawable.music_ause);
			isPlay = true;
		}
		else{
			playBtn.setImageResource(R.drawable.music_play);
			isPlay = false;
		}
	}
	
	private void isClose(){
		if(!isOpen){
			closeBtn.setImageResource(R.drawable.music_open);
			isOpen = true;
		}
		else{
			closeBtn.setImageResource(R.drawable.music_close);
			isOpen = false;
		}
	}
	
}
