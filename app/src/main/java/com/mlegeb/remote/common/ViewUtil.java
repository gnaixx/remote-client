package com.mlegeb.remote.common;

import android.view.View;

import com.mlegeb.remote.R;

/**
 * 名称: ViewUtil.java
 * 描述: View工具类
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月6日
 */
public class ViewUtil {
	/**
	 * 获取焦点位置的坐标，长宽
	 * @param views
	 * @return
	 */
	public static String guidePoints(View... views){
		FocusPoint[] points = new FocusPoint[views.length];
		int[] position  = new int[2];
		int w, h;
		for(int i=0; i<views.length; i++){
			FocusPoint point = new FocusPoint();
			views[i].getLocationOnScreen(position);
			w = views[i].getWidth();
			h = views[i].getHeight();
			point.x = position[0] + w/2;
			point.y = position[1];
			point.w = w;
			point.h = h;
			
			switch (views[i].getId()) {
			case R.id.editText1:
				point.msg = "填入PC端显示的IP地址";
				break;
			case R.id.imageButton1:
				point.msg = "选择一个功能（模拟鼠标）";
				point.y += point.h/3;
				break;
			}
			points[i] = point;	
			if(LogUtil.D)	LogUtil.d(ViewUtil.class, 
					points[i].x+","+points[i].y+","+points[i].w+","+points[i].h);
		}
		return FocusPoint.toString(points);
	}
}
