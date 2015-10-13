package com.mlegeb.remote.event;

/**
 * 名称: ConnectedEvent
 * 描述: 连接成功事件
 *
 * @author xiangqing.xue
 * @date 15/10/13
 */
public class ConnectedEvent {
    private boolean isConnected;
    private int resultCode;

    public ConnectedEvent(boolean isConnected, int resultCode) {
        this.isConnected = isConnected;
        this.resultCode = resultCode;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
