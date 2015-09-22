package com.mlegeb.remote.activity;



import com.mlegeb.remote.R;
import com.mlegeb.remote.common.KeyboardUtil;
import com.mlegeb.remote.transmission.KeyboardTransmission;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;

/**
 * 名称: InputActivity.java
 * 描述: 键盘窗口
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class InputActivity extends BaseActivity {

	/** 输入框 */
	private EditText editText;
	
	/** 消息传送 */
	private KeyboardTransmission transmission;
	
	/** 键盘工具  */
	private KeyboardUtil keyboard;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input);

		initView();
	}

	@SuppressLint("ClickableViewAccessibility")
	private void initView(){
		transmission = new KeyboardTransmission();

		editText = (EditText) findViewById(R.id.edit_tv);
		editText.setInputType(InputType.TYPE_NULL);
		keyboard = new KeyboardUtil(InputActivity.this, 
				InputActivity.this, 
				editText,
				transmission);
		keyboard.showKeyboard();
		//点击输入框显示键盘
		editText.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				keyboard.showKeyboard();
				return true;
			}
		});
	}
}
