package com.mlegeb.app.widget;

import com.mlegeb.app.R;
import com.mlegeb.app.bean.FocusPoint;
import com.mlegeb.app.common.LogUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
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

	/** 焦点区*/
	private Bitmap focus = 
			BitmapFactory.decodeResource(getResources(), 
					R.drawable.bg_guide_focus);
	/** 隐藏区 */
	private Bitmap bg = 
			BitmapFactory.decodeResource(getResources(),
					R.drawable.bg_guide);
	
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
	
	/** 画笔 */
	private Paint paint;
	
	private Paint paints;
	

	
	
	
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
		
		paint = new Paint(Color.WHITE);
		paint.setStyle(Paint.Style.FILL);
		paint.setAlpha(0);
		paints = new Paint();
		holder.addCallback(this);
		getHolder().setFormat(PixelFormat.TRANSLUCENT);
	} 
	
	
	public void setFocusPoints(FocusPoint[] points){
		this.setVisibility(View.VISIBLE);
		this.points = points;
		flag = true;
		invalidate();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		LogUtil.d(getClass(), "suufaceCreated");
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
				bg.recycle();
			}
		}
		return super.onTouchEvent(event);
	}
	
	
	private void doDraw(int step){
		//获取锁定的画布
		Canvas canvas = getHolder().lockCanvas();
		//设置半透明，
		canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
		Rect srcBg = new Rect(0, 0, bg.getWidth(), bg.getHeight());
		
		
		if(step < this.points.length){
			int left = (int)(points[step].x - points[step].w/10*7);
			int right = (int)(points[step].x + points[step].w/10*7);
			int top = (int)(points[step].y - points[step].h/10*7);
			int bottom = (int)(points[step].y + points[step].h/10*7);
			
			if(LogUtil.D)	LogUtil.d(context, 
					points[step].x+","+points[step].y+","+points[step].w+","+points[step].h);
			if(LogUtil.D)	LogUtil.d(this.getClass(), left+","+top+","+right+","+bottom);
			
			RectF dstFocus = new RectF(left, top, right, bottom); 
	        canvas.drawBitmap(focus, null, dstFocus, null);

			Rect dstTop = new Rect(0, 0, screenW, top);
			Rect dstBottom = new Rect(0, bottom, screenW, screenH);
			Rect dstLeft = new Rect(0, top, left, bottom);
			Rect dstRight = new Rect(right, top, screenW, bottom);
  		    canvas.drawBitmap(bg, srcBg, dstTop, null);
			canvas.drawBitmap(bg, srcBg, dstLeft, null);
			canvas.drawBitmap(bg, srcBg, dstRight, null);
			canvas.drawBitmap(bg, srcBg, dstBottom, null);
			
			
			paints.setColor(Color.WHITE);
			paints.setTextSize(20);
			paints.setAntiAlias(true);
			paints.setTextAlign(Align.CENTER);
			canvas.drawText(points[step].msg, points[step].x, bottom+100, paints);
			switch (step) {
			case 0:
				// first to do something
				break;
			case 1:
				// second to do something
				break;
			case 2:
				// third to do something
				break;
			default:
				break;
			}
		}
		this.step++;
		getHolder().unlockCanvasAndPost(canvas);
	}

}
