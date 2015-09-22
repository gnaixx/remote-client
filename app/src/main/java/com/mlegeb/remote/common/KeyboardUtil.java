package com.mlegeb.remote.common;

import java.util.List;

import com.mlegeb.remote.R;
import com.mlegeb.remote.transmission.KeyboardTransmission;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * 名称: KeyboardUtil.java
 * 描述: 自定义键盘工具类
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */

@SuppressLint("DefaultLocale")
public class KeyboardUtil {
	/** 上下文 */
	private Context myContext;

	/** 调用者activity */
	private Activity myActivity;

	/** 键盘View */
	private KeyboardView keyboardView;

	/** 字母键盘 */
	private Keyboard key_english;

	/**  数字键盘及第二个菜单 */
	private Keyboard symbols,symbols_shift;

	/** 是否数据键盘 */
	public boolean isnun = false;

	/** 是否大写 */
	public boolean isupper = false;

	/** 输入显示EditText */
	private EditText ed;

	/**  键盘信息传送类 */
	private KeyboardTransmission transmission;


	/**
	 * 构造函数初始化成员变量
	 * @param activity 调用者activity
	 * @param context 上下文
	 * @param editText 用于显示输入
	 * @param mtransmission 信息传送类
	 */
	public KeyboardUtil(Activity activity, 
			Context context, 
			EditText editText,
			KeyboardTransmission mtransmission) {
		this.transmission = mtransmission;
		this.myActivity = activity;
		this.myContext = context;
		this.ed = editText;
		key_english = new Keyboard(myContext, R.xml.english);
		symbols = new Keyboard(myContext, R.xml.symbols);
		symbols_shift=new Keyboard(myContext, R.xml.symbols_shift);

		keyboardView = (KeyboardView) myActivity
				.findViewById(R.id.keyboard_view);
		keyboardView.setKeyboard(key_english);
		keyboardView.setEnabled(true);
		keyboardView.setPreviewEnabled(true);
		keyboardView.setOnKeyboardActionListener(listener);
	}

	/**
	 * 键盘事件监听器
	 */
	private OnKeyboardActionListener listener = new OnKeyboardActionListener() {
		@Override
		public void swipeUp() {
		}

		@Override
		public void swipeRight() {
		}

		@Override
		public void swipeLeft() {
		}

		@Override
		public void swipeDown() {
		}

		@Override
		public void onText(CharSequence text) {
		}

		@Override
		public void onRelease(int primaryCode) {
		}

		@Override
		public void onPress(int primaryCode) {
		}

		/**
		 * 点击键盘事件
		 */
		@Override
		public void onKey(int primaryCode, int[] keyCodes) {
			Editable editable = ed.getText();
			int start = ed.getSelectionStart();
			if (primaryCode == Keyboard.KEYCODE_CANCEL) {// 完成
				// 完成按钮所做的动作
				hideKeyboard();
			} else if (primaryCode == Keyboard.KEYCODE_DELETE) {// 回退
				// 删除按钮所做的动作
				if (editable != null && editable.length() > 0) {
					if (start > 0) {
						editable.delete(start - 1, start);
					}
				}
				transmission.sendKeyCode(Constants.INPUT_STATE + ":\\b");
			} else if (primaryCode == Keyboard.KEYCODE_SHIFT) {
				// 大小写切换
				changeKey();
				keyboardView.setKeyboard(key_english);

			} else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE) {
				// 数字键盘切换,默认是英文键盘
				if (isnun) {
					isnun = false;
					keyboardView.setKeyboard(key_english);
				} else {
					isnun = true;
					keyboardView.setKeyboard(symbols);
					if(primaryCode==-102){
						keyboardView.setKeyboard(symbols_shift);
					} 

				}
			} 

			else if (primaryCode == 57419) { // go left
				if (start > 0) {
					ed.setSelection(start - 1);
				}
			} else if (primaryCode == 57421) { // go right
				if (start < ed.length()) {
					ed.setSelection(start + 1);
				}
			}
			else {
				//普通字母键
				editable.insert(start, Character.toString((char) primaryCode));
				Log.i("YUNCUNCHU",
						primaryCode + "|||"
								+ Character.toString((char) primaryCode));

				if(primaryCode == 32){
					transmission.sendKeyCode(Constants.INPUT_STATE + ":space");
				}
				else{
					transmission.sendKeyCode(Constants.INPUT_STATE + ":" +
							Character.toString((char) primaryCode));
				}
			}
		}
	};

	/**
	 * 键盘大小写切换
	 */
	private void changeKey() {
		List<Key> keylist = key_english.getKeys();
		if (isupper) {// 大写切换小写
			isupper = false;
			for (Key key : keylist) {
				if (key.label != null && isword(key.label.toString())) {
					key.label = key.label.toString().toLowerCase();
					key.codes[0] = key.codes[0] + 32;
				}
			}
		} else {// 小写切换大写
			isupper = true;
			for (Key key : keylist) {
				if (key.label != null && isword(key.label.toString())) {
					key.label = key.label.toString().toUpperCase();
					key.codes[0] = key.codes[0] - 32;
				}
			}
		}
	}

	/**
	 * 显示键盘
	 */
	public void showKeyboard() {
		int visibility = keyboardView.getVisibility();
		if (visibility == View.GONE || visibility == View.INVISIBLE) {
			keyboardView.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 隐藏键盘
	 */
	public void hideKeyboard() {
		int visibility = keyboardView.getVisibility();
		if (visibility == View.VISIBLE) {
			keyboardView.setVisibility(View.INVISIBLE);
		}
	}

	/**
	 * 判断是否输入的为字母
	 * @param str
	 * @return
	 */
	private boolean isword(String str) {
		String wordstr = "abcdefghijklmnopqrstuvwxyz";
		if (wordstr.indexOf(str.toLowerCase()) > -1) {
			return true;
		}
		return false;
	}

}
