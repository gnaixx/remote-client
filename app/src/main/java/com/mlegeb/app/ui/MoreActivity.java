package com.mlegeb.app.ui;

import com.mlegeb.app.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 名称: MoreActivity.java
 * 描述: 更多页面
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class MoreActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.more, menu);
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
