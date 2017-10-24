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
public class CanvasDrawPoint extends View {

    public CanvasDrawPoint(Context context) {
        super(context);
    }

    public CanvasDrawPoint(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasDrawPoint(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(30);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(300,300 ,paint);


        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(600,300,paint);


        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(300,600,paint);

        float[] points = new float[]{300,50,700,400,234,555,678,98};

        paint.setColor(Color.RED);
        canvas.drawPoints(points,paint);


    }

}
