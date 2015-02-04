package com.mlegeb.app.ui;

import com.mlegeb.app.R;
import com.mlegeb.app.transmission.PowerPointTransmission;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * 名称: PowerPointActivity.java
 * 描述: PPt页面
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class PowerPointActivity extends BaseActivity implements OnClickListener{

	private ImageButton leftBtn;
	private ImageButton rightBtn;
	private ImageButton showBtn;
	private ImageButton exitBtn;
	private ImageButton stateBtn;
	private ImageButton outBtn;


	private PowerPointTransmission transmission;

	private static boolean isNormal = true;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_power_point);

		transmission = new PowerPointTransmission();
		initView();
	}

	private void initView(){
		leftBtn = (ImageButton) findViewById(R.id.imageButton1);
		rightBtn = (ImageButton) findViewById(R.id.imageButton2);
		showBtn = (ImageButton) findViewById(R.id.imageButton3);
		exitBtn = (ImageButton) findViewById(R.id.imageButton4);
		stateBtn = (ImageButton) findViewById(R.id.imageButton5);
		outBtn = (ImageButton) findViewById(R.id.imageButton6);

		leftBtn.setOnClickListener(this);
		rightBtn.setOnClickListener(this);
		showBtn.setOnClickListener(this);
		exitBtn.setOnClickListener(this);
		stateBtn.setOnClickListener(this);
		outBtn.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.power_point, menu);
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
			transmission.sendPPTControl(0);
			break;
		case R.id.imageButton2:
			transmission.sendPPTControl(1);
			break;
		case R.id.imageButton3:
			transmission.sendPPTControl(2);
			break;
		case R.id.imageButton4:
			transmission.sendPPTControl(3);
			break;
		case R.id.imageButton5:
			setType();
			break;
		case R.id.imageButton6:
			transmission.sendPPTControl(4);
			break;
		}
	}
	
	private void setType(){
		System.out.println("--->" + isNormal);
		if(!isNormal){
			isNormal = true;
			stateBtn.setImageResource(R.drawable.ppt_state_1);
			transmission.sendPPTControl(5);
		}
		else{
			isNormal = false;
			stateBtn.setImageResource(R.drawable.ppt_state_2);
			transmission.sendPPTControl(6);
		}
	}
}
