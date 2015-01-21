package com.mlegeb.app.ui;

import com.mlegeb.app.R;
import com.mlegeb.app.common.MouseManager;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MouseActivity extends BaseActivity {
	
	private MouseManager mouseManager;
	private FrameLayout touchPanel;
	
	private Button leftBtn;
	private Button rightBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mouse);
		mouseManager = new MouseManager();
		
		initView();
	}

	
	private void initView(){
		touchPanel = (FrameLayout) findViewById(R.id.touch_panel);
		
		touchPanel.setOnTouchListener(new View.OnTouchListener() {
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()){
				case MotionEvent.ACTION_MOVE:
//					System.out.println("--->move");
					mouseManager.onMouseMove(event);
					break;
				case MotionEvent.ACTION_DOWN:
//					System.out.println("--->down");
					mouseManager.onMouseDown(event);
					break;
				case MotionEvent.ACTION_UP:
//					System.out.println("--->up");
					mouseManager.onMouseUp(event);
					break;
				}
				return true;
			}
		});
		
		leftBtn = (Button) findViewById(R.id.button1);
		
		leftBtn.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) { 
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					mouseManager.onMouseBtn(2);
				}
				else if(event.getAction() == MotionEvent.ACTION_UP){
					mouseManager.onMouseBtn(3);
				}
				return true;
			}
		});
		rightBtn = (Button) findViewById(R.id.button2);
		
		rightBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mouseManager.onMouseBtn(4);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mouse, menu);
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
