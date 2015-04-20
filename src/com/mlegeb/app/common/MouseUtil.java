package com.mlegeb.app.common;

import com.mlegeb.app.AppConfig;
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
public class MouseUtil {

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
	
	/** 发送信息 */
	private MouseTransmission transmission;
	
	public MouseUtil(){
		transmission = new MouseTransmission();
	}

	/**
	 * 触摸移动
	 * @param event
	 */
	public void onMouseMove(MotionEvent event){

		float x = event.getX();
		mx = x - lx;
		lx = x;

		float y = event.getY();
		my = y -ly;
		ly = y;
		if(mx!=0 && my!=0){
			int xx = AppConfig.mouseSensibility / 10;
			transmission.sendMousePoint(mx*xx, my*xx);
		}
	}
	
	/**
	 * 按下触摸区
	 * @param event
	 */
	public void onMouseDown(MotionEvent event){


		lx = event.getX(); // 当手机第一放入时 把当前坐标付给lx
		ly = event.getY();
		fx = event.getX();
		fy = event.getY();
	}

	/**
	 * 释放触摸区
	 * @param event
	 */
	public void onMouseUp(MotionEvent event){

		//没有移动为点击事件
		if (fx == event.getX() && fy == event.getY()) {
			transmission.sendMouseButton(1);
		}
	}
	
	
	/**
	 * 点击鼠标左右键
	 * @param type
	 */
	public void onMouseBtn(int type){
		transmission.sendMouseButton(type);
	}
	
}
