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
public class CanvasOval extends View {

    public CanvasOval(Context context) {
        super(context);
    }

    public CanvasOval(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasOval(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        RectF rect = new RectF(300,300,800,600);

        canvas.drawOval(rect,paint);


        paint.setStyle(Paint.Style.STROKE);

        RectF rect1 = new RectF(300,900,800,1200);

        canvas.drawOval(rect1,paint);
    }

}
