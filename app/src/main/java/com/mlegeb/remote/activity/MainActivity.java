package com.mlegeb.remote.activity;

import java.net.InetAddress;

import com.mlegeb.remote.application.RemoteApplication;
import com.mlegeb.remote.common.Constants;
import com.mlegeb.remote.R;
import com.mlegeb.remote.common.DoubleClickExitHelper;
import com.mlegeb.remote.common.ViewUtil;
import com.mlegeb.remote.event.ConnectedEvent;
import com.mlegeb.remote.model.Settings;
import com.mlegeb.remote.server.SocketService;
import com.mlegeb.remote.transmission.CheckConnection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.greenrobot.event.EventBus;

/**
 * 名称: MainActivity.java
 * 描述: 登入页面
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class MainActivity extends BaseActivity implements OnClickListener {

    //双击工具类
    private DoubleClickExitHelper mDoubleClickExitHelper;
    //连接按钮
    private Button connBtn;
    //设置按钮
    private Button settingsBtn;
    //关于页面
    private Button aboutBtn;
    //IP输入框
    private EditText serverIpEdit;
    //IP地址
    private String serverIpStr = "";
    //配置信息
    private Settings mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获取配置
        mSettings = RemoteApplication.getInstance().getSettings();
        mDoubleClickExitHelper = new DoubleClickExitHelper(this);
        initViews();

        //注册EventBus
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    /**
     * 初始化变量
     */
    private void initViews() {
        serverIpEdit = (EditText) findViewById(R.id.editText1);

        connBtn = (Button) findViewById(R.id.button1);
        settingsBtn = (Button) findViewById(R.id.button2);
        aboutBtn = (Button) findViewById(R.id.button3);

        connBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
        aboutBtn.setOnClickListener(this);

        //读取保存的IP地址
        serverIpEdit.setText(mSettings.getIpAddress());

    }

    /**
     * 连接事件
     *
     * @param event
     */
    public void onEventMainThread(ConnectedEvent event) {
        if(event.isConnected()){
            Toast.makeText(MainActivity.this, "连接成功！", Toast.LENGTH_LONG).show();
            //写入配置
            mSettings.setIpAddress(serverIpEdit.getText().toString());
            RemoteApplication.getInstance().writeSettings();

            //跳转到菜单页面
            Intent intentAct = new Intent(MainActivity.this, MenuActivity.class);
            MainActivity.this.startActivity(intentAct);
        }else{
            Toast.makeText(MainActivity.this, "连接失败！", Toast.LENGTH_LONG).show();

            Intent intentAct = new Intent(MainActivity.this, MenuActivity.class);
            MainActivity.this.startActivity(intentAct);

        }
    }

    /**
     * 监听返回--是否退出程序
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean flag = true;
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 是否退出应用
            return mDoubleClickExitHelper.onKeyDown(keyCode, event);
        } else {
            flag = super.onKeyDown(keyCode, event);
        }
        return flag;
    }

    /**
     * 按钮监听
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                connectionEvent();
                break;
            case R.id.button2:
                settingEvent();
                break;
            case R.id.button3:
                aboutEvent();
                break;
        }

    }

    /**
     * 跳转到设置页面
     */
    private void settingEvent() {
        Intent intent = new Intent(this, SettingsActivity.class);
        this.startActivity(intent);
    }

    /**
     * 开启服务监听UDP，发送连接请求
     */
    private void connectionEvent() {

        serverIpStr = serverIpEdit.getText().toString();
        long time = System.currentTimeMillis();
        try {
            InetAddress.getByName(serverIpStr);

            //开启服务  监听端口
            Intent intentService = new Intent(this, SocketService.class);
            this.startService(intentService);

            CheckConnection check = new CheckConnection();
            check.sendCheck(Constants.CONNECTION_STR);

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "IP地址格式错误", Toast.LENGTH_LONG).show();
            //e.printStackTrace();
        }


    }

    /**
     * 跳转到相关页面
     */
    private void aboutEvent() {
        Intent intent = new Intent(this, AboutActivity.class);
        this.startActivity(intent);
    }

    /**
     * 第一次运行时调用
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        boolean isFirst = mSettings != null ? mSettings.isFristOpen() : true;
        if (isFirst) {
            //保存配置
            mSettings.setIsFristOpen(false);
            RemoteApplication.getInstance().writeSettings();

            Intent intent = new Intent(MainActivity.this, GuideActivity.class);
            intent.putExtra("ArrayPoints",
                    ViewUtil.guidePoints(serverIpEdit));
            startActivity(intent);
        }
    }


}
