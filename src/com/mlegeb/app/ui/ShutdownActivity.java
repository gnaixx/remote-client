package com.mlegeb.app.ui;

import com.mlegeb.app.R;
import com.mlegeb.app.transmission.ShutdownTransmission;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ShutdownActivity extends BaseActivity implements OnClickListener{

	private EditText command;
	private Button sendBtn;
	private ImageButton powerBtn;
	private ImageButton sleepBtn;
	private ShutdownTransmission transmission;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shut_down);
		
		transmission = new ShutdownTransmission();
		InitView();
	}
	
	private void InitView(){
		command = (EditText) findViewById(R.id.editText1);
		sendBtn = (Button) findViewById(R.id.button1);
		powerBtn = (ImageButton) findViewById(R.id.imageButton1);
		sleepBtn = (ImageButton) findViewById(R.id.imageButton2);
		
		sendBtn.setOnClickListener(this);
		powerBtn.setOnClickListener(this);
		sleepBtn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.shut_down, menu);
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
		case R.id.button1:
			transmission.sendCommand(command.getText().toString());
			command.setText("");
			break;
		case R.id.imageButton1:
			transmission.sendCommand("0");
			break;
		case R.id.imageButton2:
			transmission.sendCommand("1");
			break;
		}
	}
}
