package com.mlegeb.remote.activity;

import com.mlegeb.remote.R;
import com.mlegeb.remote.netwrok.transmission.WindowTransmssion;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
/**
 * 名称: WindowActivity.java
 * 描述: 窗口页面
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class WindowActivity extends BaseActivity implements OnClickListener{

	private ImageButton backBtn;
	private ImageButton nextBtn;
	private ImageButton minBtn;
	private ImageButton maxBtn;
	private ImageButton newBtn;
	private ImageButton closeBtn;
	
	private WindowTransmssion transmission;
	private boolean isMax = false;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		transmission = new WindowTransmssion();
		initViews();
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_window;
	}

	private void initViews(){
		backBtn = (ImageButton) findViewById(R.id.imageButton1);
		nextBtn = (ImageButton) findViewById(R.id.imageButton2);
		minBtn = (ImageButton) findViewById(R.id.imageButton3);
		maxBtn = (ImageButton) findViewById(R.id.imageButton4);
		newBtn = (ImageButton) findViewById(R.id.imageButton5);
		closeBtn = (ImageButton) findViewById(R.id.imageButton6);
		
		backBtn.setOnClickListener(this);
		nextBtn.setOnClickListener(this);
		minBtn.setOnClickListener(this);
		maxBtn.setOnClickListener(this);
		newBtn.setOnClickListener(this);
		closeBtn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.window, menu);
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
		switch(v.getId()){
		case R.id.imageButton1:
			transmission.sendWindowControl("-1");
			break;
		case R.id.imageButton2:
			transmission.sendWindowControl("1");
			break;
		case R.id.imageButton3:
			transmission.sendWindowControl("2");
			break;
		case R.id.imageButton4:
			if(isMax){
				transmission.sendWindowControl("3");
				isMax = false;
			}
			else{
				transmission.sendWindowControl("-3");
				isMax = true;
			}
			break;
		case R.id.imageButton5:
			transmission.sendWindowControl("5");
			break;
		case R.id.imageButton6:
			transmission.sendWindowControl("4");
			break;
			
		}
	}
}
