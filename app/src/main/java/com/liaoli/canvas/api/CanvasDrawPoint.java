package com.liaoli.canvas.api;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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

        canvas.drawColor(Color.parseColor("#7766ed7f"));
    }

}
