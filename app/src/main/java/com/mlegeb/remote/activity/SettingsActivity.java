package com.mlegeb.remote.activity;


import com.mlegeb.remote.application.RemoteApplication;
import com.mlegeb.remote.common.Constants;
import com.mlegeb.remote.R;
import com.mlegeb.remote.common.LogUtil;
import com.mlegeb.remote.model.Settings;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

/**
 * 名称: SettingsActivity.java
 * 描述: 设置页面
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class SettingsActivity extends BaseActivity {

    //鼠标灵敏度
    private SeekBar mouseSeek;
    //灵敏度值
    private int mouseSensi;
    //配置信息
    private Settings mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSettings = RemoteApplication.getInstance().getSettings();
        mouseSensi = (mSettings != null) ? mSettings.getMouseSensibility(): 0;
        initViews();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_setting;
    }

    /**
     * 初始化界面
     */
    private void initViews() {
        mouseSeek = (SeekBar) findViewById(R.id.mouse_sen);
        mouseSeek.setProgress(mouseSensi);

        mouseSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                mouseSensi = progress;
                LogUtil.d(SettingsActivity.this, String.valueOf(mouseSensi));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                LogUtil.d(SettingsActivity.this, "progressStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //保存配置
                mSettings.setMouseSensibility(mouseSensi);
                RemoteApplication.getInstance().writeSettings();
                LogUtil.d(SettingsActivity.this, "progressStopTrackingTouch");
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.setting, menu);
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
