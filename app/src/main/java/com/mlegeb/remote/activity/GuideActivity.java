package com.mlegeb.remote.activity;



import com.mlegeb.remote.R;
import com.mlegeb.remote.common.Constants;
import com.mlegeb.remote.common.FocusPoint;
import com.mlegeb.remote.common.LogUtil;
import com.mlegeb.remote.widget.GuideView;

import android.os.Bundle;

/**
 * 名称: GuideActivity.java
 * 描述: 引导页面安装第一次显示
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class GuideActivity extends BaseActivity {
	
	/** 遮挡用的View*/
	private GuideView guideView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		guideView = new GuideView(this);
		setTheme(R.style.AppTheme);
		setContentView(guideView);
	}

	@Override
	protected int getLayoutResource() {
		return Constants.DEFALUT_LAYOUT_RESOURCE;
	}

	@Override
	protected void onResume() {
		
		try {
			String str = 
					getIntent().getStringExtra("ArrayPoints");
			guideView.setFocusPoints(FocusPoint.toBean(str));
		} catch (Exception e) {
			LogUtil.d(getClass(), "Intent-Str-JSONArray：失败！");
			e.printStackTrace();
		}
		super.onResume();
	}
	
}
