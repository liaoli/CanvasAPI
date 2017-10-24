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
public class CanvasDrawCircle extends View {

    public CanvasDrawCircle(Context context) {
        super(context);
    }

    public CanvasDrawCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasDrawCircle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        canvas.drawCircle(300,300,100,paint);


        Paint paint2 = new Paint();
        paint2.setColor(Color.RED);
        paint2.setAntiAlias(true);
        canvas.drawCircle(600,300,100,paint2);



        paint2.setColor(Color.BLACK);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(300,600,100,paint2);


        paint2.setColor(Color.BLACK);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(20);
        canvas.drawCircle(600,600,80,paint2);

    }

}
