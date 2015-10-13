package com.mlegeb.remote.activity;

import com.mlegeb.remote.AppManager;
import com.mlegeb.remote.R;
import com.mlegeb.remote.common.Constants;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import de.greenrobot.event.EventBus;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//导入布局
		if(getLayoutResource()!= Constants.DEFALUT_LAYOUT_RESOURCE){
			setContentView(getLayoutResource());
		}

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
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unRegisterEventBus();
	}

	/**
	 * 注销eventBus
	 */
	private void unRegisterEventBus(){
		if(EventBus.getDefault().isRegistered(this)){
			EventBus.getDefault().unregister(this);
		}
	}
}
