package com.mlegeb.remote.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Stack;
import java.util.logging.SimpleFormatter;

import android.content.Context;
import android.text.TextUtils;
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
    //log日志根目录
    public static final String LOG_PATH = FileUtil.BASE_PATH + "log";
    //log日志文件名
    public static final String LOG_FILENAME = "ibaozhang.txt";

    /**
     * debug 开关
     */
    public static boolean D = true;

    /**
     * info 开关
     */
    public static boolean I = true;

    /**
     * error 开关
     */
    public static boolean E = true;

    /**
     * 开始执行时间
     */
    public static long start_time = 0;

    /**
     * 默认 tag
     *
     * @param msg
     */
    public static void d(String msg) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        String tag = getDefaultTag(stackTraceElement);
        d(tag, msg);
    }


    /**
     * debug 日志
     *
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (D) Log.d(tag, msg);
    }


    /**
     * debug 日志
     *
     * @param context
     * @param msg
     */
    public static void d(Context context, String msg) {
        String tag = context.getClass().getSimpleName();
        d(tag, msg);
    }

    /**
     * debug 日志
     *
     * @param clazz
     * @param msg
     */
    public static void d(Class<?> clazz, String msg) {
        String tag = clazz.getSimpleName();
         d(tag, msg);
    }

    /**
     * 默认 tag
     *
     * @param msg
     */
    public static void i(String msg) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        String tag = getDefaultTag(stackTraceElement);
        i(tag, msg);
    }

    /**
     * info 日志
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (I) Log.i(tag, msg);
    }


    /**
     * info 日志
     *
     * @param context
     * @param msg
     */
    public static void i(Context context, String msg) {
        String tag = context.getClass().getSimpleName();
        i(tag, msg);
    }

    /**
     * info 日志
     *
     * @param clazz
     * @param msg
     */
    public static void i(Class<?> clazz, String msg) {
        String tag = clazz.getSimpleName();
        i(tag, msg);
    }

    /**
     * 默认 tag
     *
     * @param msg
     */
    public static void e(String msg, boolean... save) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        String tag = getDefaultTag(stackTraceElement);
        e(tag, msg, save);
    }

    /**
     * error 日志
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg, boolean... save) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        msg = getLogInfo(stackTraceElement) + msg;
        if (E) Log.e(tag, msg);
        //写入文件
        if(save != null){
            writeLog(msg);
        }
    }

    /**
     * error 日志
     *
     * @param context
     * @param msg
     */
    public static void e(Context context, String msg, boolean... save) {
        String tag = context.getClass().getSimpleName();
        e(tag, msg, save);
    }

    /**
     * error 日志
     *
     * @param clazz
     * @param msg
     */
    public static void e(Class<?> clazz, String msg, boolean... save) {
        String tag = clazz.getSimpleName();
        e(tag, msg, save);
    }


    /**
     * 描述：记录当前时间毫秒.
     */
    public static void prepareLog(String tag) {
        Calendar current = Calendar.getInstance();
        start_time = current.getTimeInMillis();
        d(tag, "日志计时开始：" + start_time);
    }

    /**
     * 描述：记录当前时间毫秒.
     */
    public static void prepareLog(Context context) {
        String tag = context.getClass().getSimpleName();
        prepareLog(tag);
    }

    /**
     * 描述：记录当前时间毫秒.
     */
    public static void prepareLog(Class<?> clazz) {
        String tag = clazz.getSimpleName();
        prepareLog(tag);
    }

    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param tag       标记
     * @param message   描述
     * @param printTime 是否打印时间
     */
    public static void d(String tag, String message, boolean printTime) {
        Calendar current = Calendar.getInstance();
        long endLogTimeInMillis = current.getTimeInMillis();
        d(tag, message + ":" + (endLogTimeInMillis - start_time) + "ms");
    }


    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param context   上下文
     * @param message   描述
     * @param printTime 是否打印时间
     */
    public static void d(Context context, String message, boolean printTime) {
        String tag = context.getClass().getSimpleName();
        d(tag, message, printTime);
    }

    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param clazz     标记
     * @param message   描述
     * @param printTime 是否打印时间
     */
    public static void d(Class<?> clazz, String message, boolean printTime) {
        String tag = clazz.getSimpleName();
        d(tag, message, printTime);
    }

    /**
     * 获取默认TAG名称
     *
     * @param stackTraceElement
     * @return TAG名称
     */
    private static String getDefaultTag(StackTraceElement stackTraceElement) {
        String fileName = stackTraceElement.getFileName();
        if (fileName == null) {
            return "REMOTE";
        } else {
            return fileName.split("\\.")[0];
        }
    }

    /**
     * 获取日志具体信息
     *
     * @param stackTraceElement
     * @return
     */
    private static String getLogInfo(StackTraceElement stackTraceElement) {
        StringBuilder builderInfo = new StringBuilder();
        //线程名
        String threadName = Thread.currentThread().getName();
        //线程ID
        long threadID = Thread.currentThread().getId();
        //类名
        String className = stackTraceElement.getClassName();
        //函数名
        String methodName = stackTraceElement.getMethodName();
        //行数
        long lineNumber = stackTraceElement.getLineNumber();

        builderInfo.append("[");
        builderInfo.append("thread=").append(threadID).append("/").append(threadName).append(",");
        builderInfo.append("method=").append(className).append("/").append(methodName).append(",");
        builderInfo.append("line=").append(lineNumber);
        builderInfo.append("] ");

        return builderInfo.toString();
    }

    /**
     * 将日志写到文件
     *
     * @param msg
     */
    private static void writeLog(String msg) {
        if(FileUtil.isExternalStorageMounted() && FileUtil.createFolder(LOG_PATH)){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String sLog = dateFormat.format(new Date()) + " " + msg + "\n";
            FileUtil.writeFile(LOG_PATH + File.separator + LOG_FILENAME, sLog);
        }
    }

    /**
     * debug日志的开关
     *
     * @param d
     */
    public static void debug(boolean d) {
        D = d;
    }

    /**
     * info日志的开关
     *
     * @param i
     */
    public static void info(boolean i) {
        I = i;
    }

    /**
     * error日志的开关
     *
     * @param e
     */
    public static void error(boolean e) {
        E = e;
    }

    /**
     * 设置日志的开关
     *
     * @param e
     */
    public static void setVerbose(boolean d, boolean i, boolean e) {
        D = d;
        I = i;
        E = e;
    }

    /**
     * 打开所有日志，默认全打开
     */
    public static void openAll() {
        D = true;
        I = true;
        E = true;
    }

    /**
     * 关闭所有日志
     */
    public static void closeAll() {
        D = false;
        I = false;
        E = false;
    }


}
