package com.liaoli.canvas.api;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class CanvasDrawPath extends View {

    public CanvasDrawPath(Context context) {
        super(context);
    }

    public CanvasDrawPath(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasDrawPath(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        Path path = new Path();
        RectF rectF1 = new RectF(200, 200, 500, 500);
        path.addArc(rectF1, -225,255);

        RectF rectF2 = new RectF(500, 200, 800, 500);
        path.arcTo(rectF2, 180, 225, false);

        path.lineTo(500, 700);

        canvas.drawPath(path, paint);

        Path path1 = new Path();

        path1.lineTo(300,300);

        path1.moveTo(0,200);

        path1.lineTo(600,700);

        canvas.drawPath(path1, paint);

        Path path2 = new Path();

        path2.addCircle(300,1000,100, Path.Direction.CCW);

        path2.addCircle(370,1000,100, Path.Direction.CCW);

        Paint paint1 = new Paint();

        canvas.drawPath(path2,paint1);

    }

}
