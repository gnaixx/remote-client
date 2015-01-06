package com.mlegeb.app.ui;



import com.mlegeb.app.R;
import com.mlegeb.app.common.KeyboardUtil;
import com.mlegeb.app.transmission.KeyboardTransmission;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;

public class InputActivity extends BaseActivity {

	private EditText editText;
	private KeyboardTransmission transmission;
	private KeyboardUtil keyboard;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input);

		initView();
	}

	private void initView(){
		transmission = new KeyboardTransmission();

		editText = (EditText) findViewById(R.id.edit_tv);
		editText.setInputType(InputType.TYPE_NULL);
		keyboard = new KeyboardUtil(InputActivity.this, 
				InputActivity.this, 
				editText,
				transmission);
		keyboard.showKeyboard();
		editText.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				keyboard.showKeyboard();
				return true;
			}
		});
	}
}
