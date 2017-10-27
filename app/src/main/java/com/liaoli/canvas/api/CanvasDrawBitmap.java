package com.liaoli.canvas.api;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.liaoli.canvas.R;

/**
 * TODO: document your custom view class.
 */
public class CanvasDrawBitmap extends View {

    private static final String TAG = "CanvasDrawBitmap";
    private int mTotalWidth;
    private int mTotalHeight;
    private Rect res;
    private Rect des;
    private int bitmapW;
    private int bitmapH;

    public CanvasDrawBitmap(Context context) {
        super(context);
    }

    public CanvasDrawBitmap(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasDrawBitmap(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);

        //canvas.drawBitmap(bitmap, 200,300, new Paint());

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);

        bitmapW = bitmap.getWidth();
        bitmapH = bitmap.getHeight();
        res = new Rect(0, 0, bitmapW, bitmapH);
        if (des == null) {
            des = new Rect(0, 0, bitmapW, bitmapW);
        }

        canvas.drawBitmap(bitmap, res, des, paint);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mTotalWidth = w;
        mTotalHeight = h;

    }


    public void startTranlate(final float toleft, final float toTop, long time) {


        final int startLeft = des.left;
        final int startTop = des.top;

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);

        valueAnimator.setDuration(time);

        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                int currentLeft = (int) ((toleft - startLeft) * fraction + startLeft);
                int currentTop = (int) ((toTop - startTop) * fraction + startTop);


                des.left = currentLeft;

                des.top = currentTop;

                des.right = des.left + bitmapW;

                des.bottom = des.top + bitmapH;

                postInvalidate();
            }
        });
        valueAnimator.start();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()){

            case MotionEvent.ACTION_UP:
                float x = event.getX();
                float y = event.getY();

                startTranlate(x,y,300);

                Log.e(TAG,"onTouchEvent x = " + x + ",y = " + y);
                break;
        }

        return true;
    }
}
