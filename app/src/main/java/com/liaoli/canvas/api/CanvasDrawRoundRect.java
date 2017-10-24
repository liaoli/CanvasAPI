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
public class CanvasDrawRoundRect extends View {

    public CanvasDrawRoundRect(Context context) {
        super(context);
    }

    public CanvasDrawRoundRect(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasDrawRoundRect(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        RectF rf = new RectF(300, 300, 900, 600);

        canvas.drawRoundRect(rf, 50, 50, paint);


        RectF rf2 = new RectF(300, 900, 900, 1200);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(rf2, 50, 50, paint);

    }

}
