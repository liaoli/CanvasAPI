package com.liaoli.canvas.api;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class CanvasDrawLine extends View {

    public CanvasDrawLine(Context context) {
        super(context);
    }

    public CanvasDrawLine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasDrawLine(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        canvas.drawLine(100,100,1000,500,paint);

        float[] points = new float[]{ 100,100,800,900,100,300,400,600,100,833,44,555 };

        canvas.drawLines(points,2, 8,paint);

    }

}
