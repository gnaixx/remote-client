package com.mlegeb.app.ui;

import com.mlegeb.app.R;
import com.mlegeb.app.transmission.GameTransmission;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;

public class GameActivity extends BaseActivity implements OnTouchListener{

	private ImageButton upBtn;
	private ImageButton downBtn;
	private ImageButton leftBtn;
	private ImageButton rightBtn;
	
	private Button aBtn;
	private Button bBtn;
	private Button cBtn;
	private Button dBtn;
	
	
	private GameTransmission transmission;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		
		transmission = new GameTransmission();
		initView();
	}
	
	private void initView(){
		upBtn = (ImageButton) findViewById(R.id.imageButton1);
		downBtn = (ImageButton) findViewById(R.id.imageButton2);
		leftBtn = (ImageButton) findViewById(R.id.imageButton3);
		rightBtn = (ImageButton) findViewById(R.id.imageButton4);
		
		aBtn = (Button) findViewById(R.id.button1);
		bBtn = (Button) findViewById(R.id.button2);
		cBtn = (Button) findViewById(R.id.button3);
		dBtn = (Button) findViewById(R.id.button4);
		
		upBtn.setOnTouchListener(this);
		downBtn.setOnTouchListener(this);
		leftBtn.setOnTouchListener(this);
		rightBtn.setOnTouchListener(this);
		aBtn.setOnTouchListener(this);
		bBtn.setOnTouchListener(this);
		cBtn.setOnTouchListener(this);
		dBtn.setOnTouchListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.game, menu);
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

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		switch (v.getId()) {
		case R.id.imageButton1:
			onControl(1, event);
			break;
		case R.id.imageButton2:
			onControl(3, event);
			break;
		case R.id.imageButton3:
			onControl(4, event);
			break;
		case R.id.imageButton4:
			onControl(2, event);
			break;
		case R.id.button1:
			onControl(5, event);
			break;
		case R.id.button2:
			onControl(6, event);
			break;
		case R.id.button3:
			onControl(7, event);
			break;
		case R.id.button4:
			onControl(8, event);
			break;
		}
		return true;
	}
	
	
	private void onControl(int type, MotionEvent event){
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			transmission.sendGameButton("D"+type);
		}
		else if(event.getAction() == MotionEvent.ACTION_UP){
			transmission.sendGameButton("U"+type);
		}
	}
}
