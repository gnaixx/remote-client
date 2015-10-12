package com.mlegeb.remote.activity;

import com.mlegeb.remote.AppManager;
import com.mlegeb.remote.R;
import com.mlegeb.remote.common.Constants;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * 名称: BaseActivity.java
 * 描述: Activity基类
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public abstract class BaseActivity extends AppCompatActivity {
	//导航栏
	private Toolbar mToolbar;
	private TextView mToolbarTitle;


	// 是否允许全屏
	private boolean allowFullScreen = true;

	// 是否允许销毁
	private boolean allowDestroy = true;

	private View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//导入布局
		if(getLayoutResource()!= Constants.DEFALUT_LAYOUT_RESOURCE){
			setContentView(getLayoutResource());
		}

		allowFullScreen = true;
		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);

		//设置导航栏
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		if(mToolbar != null){
			setSupportActionBar(mToolbar);

			//使用自定义toolbarTitle
			mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
			if(mToolbarTitle != null && getSupportActionBar()!=null){
				//默认title不显示
				getSupportActionBar().setDisplayShowTitleEnabled(false);
			}
		}
	}

	/**
	 * 获取布局xml
	 * @return layout resource
	 */
	protected abstract int getLayoutResource();

	/**
	 * 改变标题
	 * @param title
	 * @param color
	 */
	@Override
	protected void onTitleChanged(CharSequence title, int color) {
		super.onTitleChanged(title, color);

		if(mToolbarTitle != null){
			mToolbarTitle.setText(title);
		}
	}

	/**
	 * 显示返回按钮
	 */
	protected void enableBackable(){
		if(getSupportActionBar() != null){
			getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
			//设置监听
			if(mToolbar != null){
				mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						onBackPressed();
					}
				});
			}
		}
	}


	/**
	 * 查看是否全屏
	 * @return
	 */
	public boolean isAllowFullScreen() {
		return allowFullScreen;
	}

	/**
	 * 设置是否可以全屏
	 * 
	 * @param allowFullScreen
	 */
	public void setAllowFullScreen(boolean allowFullScreen) {
		this.allowFullScreen = allowFullScreen;
	}

	public void setAllowDestroy(boolean allowDestroy) {
		this.allowDestroy = allowDestroy;
	}

	public void setAllowDestroy(boolean allowDestroy, View view) {
		this.allowDestroy = allowDestroy;
		this.view = view;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && view != null) {
			view.onKeyDown(keyCode, event);
			if (!allowDestroy) {
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// 结束Activity&从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}
}
