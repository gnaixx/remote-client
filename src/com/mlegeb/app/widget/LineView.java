package com.mlegeb.app.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class LineView extends View {

	public LineView(Context context, AttributeSet attrs) {
		super(context, attrs); 
	} 
    
    @Override
    protected void onDraw(Canvas canvas) {
       super.onDraw(canvas);
       canvas.drawColor(Color.BLACK);
    }
}
