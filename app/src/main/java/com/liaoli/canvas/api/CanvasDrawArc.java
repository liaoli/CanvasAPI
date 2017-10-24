package com.liaoli.canvas.api;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class CanvasDrawArc extends View {

    public CanvasDrawArc(Context context) {
        super(context);
    }

    public CanvasDrawArc(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasDrawArc(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        RectF rectF = new RectF(300,300,800,600);
        canvas.drawArc(rectF,0,-120,true,paint);


        canvas.drawArc(rectF,30,120,false,paint);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF,-135,-45,false,paint);

    }

}
