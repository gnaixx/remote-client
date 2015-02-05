package com.mlegeb.app.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mlegeb.app.common.LogUtil;

/**
 * 名称: FocusPoint.java
 * 描述: 焦点坐标
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月5日
 */
public class FocusPoint {

	public int x;			//x轴坐标
	public int y;			//y轴坐标
	public int h;		//高度
	public int w;		//宽度

	public FocusPoint(){
		
	}

	public FocusPoint(int x, int y, int h, int w){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = y;
	}

	/**
	 * 将point对象转化为Josn字符串
	 * @param points
	 * @return
	 */
	public static String toString(FocusPoint... points){
		JSONArray arrayJson = new JSONArray();
		for(int i=0; i<points.length; i++){
			JSONObject json = new JSONObject();
			try {
				json.put("x", points[i].x);
				json.put("y", points[i].y);
				json.put("w", points[i].w);
				json.put("h", points[i].h);
			} catch (JSONException e) {
				LogUtil.d(FocusPoint.class, "bean-json:失败！");
				e.printStackTrace();
			}
			
			arrayJson.put(json);
		}
		return arrayJson.toString();
	}
	
	/**
	 * 将Json字符串转化为Point对象
	 * @param str
	 * @return
	 */
	public static FocusPoint[] toBean(String str){
		JSONArray arrayJson = null;
		try {
			arrayJson = new JSONArray(str);
		} catch (JSONException e) {
			LogUtil.d(FocusPoint.class, "json-bean:失败！");
			e.printStackTrace();
		}
		FocusPoint[] points = new FocusPoint[arrayJson.length()];
		for(int i=0; i<arrayJson.length(); i++){
			FocusPoint point = new FocusPoint(); 
			try {
				point.x = arrayJson.getJSONObject(i).getInt("x");
				point.y = arrayJson.getJSONObject(i).getInt("y");
				point.w = arrayJson.getJSONObject(i).getInt("w");
				point.h = arrayJson.getJSONObject(i).getInt("h");
				points[i] = point;
				
			} catch (JSONException e) {
				LogUtil.d(FocusPoint.class, "json-bean:出错！");
				e.printStackTrace();
			}
		}
		return points;
		
	}

}
