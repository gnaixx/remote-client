package com.mlegeb.remote.model;

/**
 * 名称: Settings
 * 描述: 配置信息
 *
 * @author xiangqing.xue
 * @date 15/10/12
 */
public class Settings {
    //上一次连接的ip
    private String ipAddress;
    //是否第一打开
    private boolean isFirstOpen;
    //鼠标灵敏度
    private int mouseSensibility;

    /* 默认值 */
    public final static String DEFAULT_IP = "192.168.6.114";
    public final static int DEFAULT_MOUSE_SENSI = 50;
    public final static boolean DEFAULT_iS_FRIST_OPEN = true;


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isFristOpen() {
        return isFirstOpen;
    }

    public void setIsFristOpen(boolean isFristOpen) {
        this.isFirstOpen = isFristOpen;
    }

    public int getMouseSensibility() {
        return mouseSensibility;
    }

    public void setMouseSensibility(int mouseSensibility) {
        this.mouseSensibility = mouseSensibility;
    }
}
