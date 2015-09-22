package com.mlegeb.remote.common;

import java.util.Calendar;

import android.content.Context;
import android.util.Log;

/**
 * 名称: DebugUtile.java
 * 描述: 日志工具类
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月5日
 */
public class LogUtil {

	/** debug 开关 */
	public static boolean D = true;
	
	/** info 开关 */
	public static boolean I = true;
	
	/** error 开关 */
	public static boolean E = true;
	
	/** 开始执行时间*/
	public static long start_time = 0;
	
	
	/**
	 * debug 日志
	 * @param tag
	 * @param msg
	 */
	public static void d(String tag, String msg){
		if(D) Log.d(tag, msg);
	}
	
	
	/**
	 * debug 日志
	 * @param context
	 * @param msg
	 */
	public static void d(Context context, String msg){
		String tag = context.getClass().getSimpleName();
		if(D) Log.d(tag, msg);
	}
	
	/**
	 * debug 日志
	 * @param clazz
	 * @param msg
	 */
	public static void d(Class<?> clazz, String msg){
		String tag = clazz.getSimpleName();
		if(D) Log.d(tag, msg);
	}
	/**
	 * info 日志
	 * @param tag
	 * @param msg
	 */
	public static void i(String tag, String msg){
		if(D) Log.d(tag, msg);
	}
	
	
	/**
	 * info 日志
	 * @param context
	 * @param msg
	 */
	public static void i(Context context, String msg){
		String tag = context.getClass().getSimpleName();
		if(D) Log.d(tag, msg);
	}
	
	/**
	 * info 日志
	 * @param clazz
	 * @param msg
	 */
	public static void i(Class<?> clazz, String msg){
		String tag = clazz.getSimpleName();
		if(D) Log.d(tag, msg);
	}
	/**
	 * error 日志
	 * @param tag
	 * @param msg
	 */
	public static void e(String tag, String msg){
		if(D) Log.d(tag, msg);
	}
	
	
	/**
	 * error 日志
	 * @param context
	 * @param msg
	 */
	public static void e(Context context, String msg){
		String tag = context.getClass().getSimpleName();
		if(D) Log.d(tag, msg);
	}
	
	/**
	 * error 日志
	 * @param clazz
	 * @param msg
	 */
	public static void e(Class<?> clazz, String msg){
		String tag = clazz.getSimpleName();
		if(D) Log.d(tag, msg);
	}
	
	
	/**
	 * 描述：记录当前时间毫秒.
	 * 
	 */
	public static void prepareLog(String tag) {
		Calendar current = Calendar.getInstance();
		start_time = current.getTimeInMillis();
		Log.d(tag,"日志计时开始："+start_time);
	}
	
	/**
	 * 描述：记录当前时间毫秒.
	 * 
	 */
	public static void prepareLog(Context context) {
		String tag = context.getClass().getSimpleName();
		prepareLog(tag);
	}
	
	/**
	 * 描述：记录当前时间毫秒.
	 * 
	 */
	public static void prepareLog(Class<?> clazz) {
		String tag = clazz.getSimpleName();
		prepareLog(tag);
	}
	
	/**
	 * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
	 *
	 * @param tag 标记
	 * @param message 描述
	 * @param printTime 是否打印时间
	 */
	public static void d(String tag, String message,boolean printTime) {
		Calendar current = Calendar.getInstance();
		long endLogTimeInMillis = current.getTimeInMillis();
		Log.d(tag,message+":"+(endLogTimeInMillis-start_time)+"ms");
	}
	
	
	/**
	 * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
	 *
	 * @param tag 标记
	 * @param message 描述
	 * @param printTime 是否打印时间
	 */
	public static void d(Context context,String message,boolean printTime) {
		String tag = context.getClass().getSimpleName();
	    d(tag,message,printTime);
	}
	
	/**
	 * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
	 *
	 * @param clazz 标记
	 * @param message 描述
	 * @param printTime 是否打印时间
	 */
	public static void d(Class<?> clazz,String message,boolean printTime) {
		String tag = clazz.getSimpleName();
		d(tag,message,printTime);
	}
	
	/**
	 * debug日志的开关
	 * @param d
	 */
	public static void debug(boolean d) {
		D  = d;
	}
	
	/**
	 * info日志的开关
	 * @param i
	 */
	public static void info(boolean i) {
		I  = i;
	}
	
	/**
	 * error日志的开关
	 * @param e
	 */
	public static void error(boolean e) {
		E  = e;
	}
	
	/**
	 * 设置日志的开关
	 * @param e
	 */
	public static void setVerbose(boolean d,boolean i,boolean e) {
		D  = d;
		I  = i;
		E  = e;
	}
	
	/**
	 * 打开所有日志，默认全打开
	 * @param d
	 */
	public static void openAll() {
		D  = true;
		I  = true;
		E  = true;
	}
	
	/**
	 * 关闭所有日志
	 * @param d
	 */
	public static void closeAll() {
		D  = false;
		I  = false;
		E  = false;
	}
	
	
}
