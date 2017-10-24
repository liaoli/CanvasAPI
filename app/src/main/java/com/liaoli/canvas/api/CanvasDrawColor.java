package com.liaoli.canvas.api;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.liaoli.canvas.R;

/**
 * TODO: document your custom view class.
 */
public class CanvasDrawColor extends View {

    public CanvasDrawColor(Context context) {
        super(context);
    }

    public CanvasDrawColor(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasDrawColor(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.parseColor("#7766ed7f"));
    }

}
