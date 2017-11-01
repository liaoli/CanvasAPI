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
public class CanvasDrawText extends View {

    public CanvasDrawText(Context context) {
        super(context);
    }

    public CanvasDrawText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasDrawText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        paint.setTextSize(100);
        paint.setColor(Color.parseColor("#5feedc"));

        canvas.drawText("i love you",100,100,paint);


    }

}
