package com.liaoli.canvas.api;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class CanvasDrawRect extends View {

    public CanvasDrawRect(Context context) {
        super(context);
    }

    public CanvasDrawRect(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasDrawRect(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        canvas.drawRect(200 ,200,400,400,paint);


        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(600 ,200,800,400,paint);


        Rect rect = new Rect(200,600,400,800);

        canvas.drawRect(rect,paint);


        RectF rectf = new RectF(600,600,800,800);

        canvas.drawRect(rectf,paint);

    }

}
