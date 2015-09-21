package com.mlegeb.app.ui;



import com.mlegeb.app.common.FocusPoint;
import com.mlegeb.app.common.LogUtil;
import com.mlegeb.app.widget.GuideView;

import android.app.Activity;
import android.os.Bundle;

/**
 * 名称: GuideActivity.java
 * 描述: 引导页面安装第一次显示
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class GuideActivity extends Activity {
	
	/** 遮挡用的View*/
	private GuideView guideView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		guideView = new GuideView(this);
	
		setContentView(guideView);
		
		
	
	}
	
	@Override
	protected void onResume() {
		
		try {
			String str = 
					getIntent().getStringExtra("ArrayPoints");
			guideView.setFocusPoints(FocusPoint.toBean(str));
		} catch (Exception e) {
			if(LogUtil.D) LogUtil.d(getClass(), "Intent-Str-JSONArray：失败！");
			e.printStackTrace();
		}
		super.onResume();
	}
	
}
