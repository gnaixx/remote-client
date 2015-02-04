package com.mlegeb.app.common;

import com.mlegeb.app.transmission.MouseTransmission;

import android.view.MotionEvent;

/**
 * 名称: MouseManager.java
 * 描述: 鼠标管理工具类
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月4日
 */
public class MouseManager {

	private static float mx = 0; // 发送的鼠标移动的差值
	private static float my = 0;
	private static float lx; // 记录上次鼠标的位置
	private static float ly;
	private static float fx; // 手指第一次接触屏幕时的坐标
	private static float fy;
	@SuppressWarnings("unused")
	private static float lbx = 0; // 鼠标左键移动初始化坐标
	@SuppressWarnings("unused")
	private static float lby = 0;
	
	private MouseTransmission transmission;
	
	public MouseManager(){
		transmission = new MouseTransmission();
	}

	public void onMouseMove(MotionEvent event){
//		System.out.println("--->move");
		float x = event.getX();
		mx = x - lx;
		lx = x;

		float y = event.getY();
		my = y -ly;
		ly = y;
		if(mx!=0 && my!=0){
			transmission.sendMousePoint(mx, my);
		}
	}
	public void onMouseDown(MotionEvent event){
//		System.out.println("--->down");

		lx = event.getX(); // 当手机第一放入时 把当前坐标付给lx
		ly = event.getY();
		fx = event.getX();
		fy = event.getY();
	}

	public void onMouseUp(MotionEvent event){
//		System.out.println("--->up");
		if (fx == event.getX() && fy == event.getY()) {
			transmission.sendMouseButton(1);
		}
	}
	
	
	public void onMouseBtn(int type){
		transmission.sendMouseButton(type);
	}
	
}
