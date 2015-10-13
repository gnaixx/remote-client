package com.mlegeb.remote.activity;

import com.mlegeb.remote.R;

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

		enableBackable();
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_more;
	}

}
