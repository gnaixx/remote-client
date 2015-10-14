package com.mlegeb.remote.widget;

import com.mlegeb.remote.R;
import com.mlegeb.remote.model.FocusPoint;
import com.mlegeb.remote.common.LogUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * 名称: GuideView.java
 * 描述: 功能引导页面
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年2月5日
 */
public class GuideView extends SurfaceView implements SurfaceHolder.Callback{
	/** 上下文 */
	private Context context;
	
	/** 操作接口 */
	private SurfaceHolder holder;

	/** 屏幕宽度 */
	private int screenW;
	
	/** 屏幕高度 */
	private int screenH;
	
	/** 焦点坐标对象 */
	private FocusPoint[] points;
	
	/** 是否显示引导 */
	private boolean flag = false;
	
	/** 步数 */
	private int step = 0;
	
	/** 背景画笔 */
	private Paint bgPaint;
	/** 文字画笔 */
	private Paint textPaint;
	
	public GuideView(Context context){
		this(context, null);
	}

	@SuppressWarnings("deprecation")
	public GuideView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;	
		this.holder = this.getHolder();
		Display mDisplay = 
				((Activity) context).getWindowManager()
				.getDefaultDisplay();
		
		screenW = mDisplay.getWidth();
		screenH = mDisplay.getHeight();

		bgPaint = new Paint(Color.WHITE);

		textPaint = new Paint();
		textPaint.setColor(Color.WHITE);
		textPaint.setTextSize(getResources().getDimension(R.dimen.text_size_28pt));
		textPaint.setAntiAlias(true);
		holder.addCallback(this);
		getHolder().setFormat(PixelFormat.TRANSLUCENT);
	}

	/**
	 * 获取焦点
	 * @param points
	 */
	public void setFocusPoints(FocusPoint[] points){
		this.setVisibility(View.VISIBLE);
		this.points = points;
		flag = true;
		invalidate();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		LogUtil.d(getClass(), "surfaceCreated");
		if(flag){
			doDraw(step);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		LogUtil.d(getClass(), "surfaceChanged");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		LogUtil.d(getClass(), "surfaceDestroyed");
	}
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(flag){
			if(step < this.points.length){
				doDraw(step);
			}else{
				((Activity) context).finish();
			}
		}
		return super.onTouchEvent(event);
	}
	
	private void doDraw(int step){
		//获取锁定的画布
		Canvas canvas = getHolder().lockCanvas();

		if(step < this.points.length){
			int left = (int)(points[step].x - points[step].w/10*7);
			int right = (int)(points[step].x + points[step].w/10*7);
			int top = (int)(points[step].y - points[step].h/10*7);
			int bottom = (int)(points[step].y + points[step].h/10*7);
			
			LogUtil.d(context,
					points[step].x + "," + points[step].y + "," + points[step].w + "," + points[step].h);
			LogUtil.d(this.getClass(), left + "," + top + "," + right + "," + bottom);

			//绘制背景
			canvas.drawColor(getResources().getColor(R.color.status_view));

			//定义画笔
			bgPaint.setStyle(Paint.Style.FILL);
			bgPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
			//绘制焦点区域
			bgPaint.setAlpha(0);
			RectF focusRect = new RectF(left, top, right, bottom);
			canvas.drawRoundRect(focusRect, 5, 5, bgPaint);

			textPaint.setTextAlign(Align.CENTER);
			canvas.drawText(points[step].msg, points[step].x, bottom+100, textPaint);
		}
		this.step++;
		getHolder().unlockCanvasAndPost(canvas);
	}
}
