package com.liaoli.canvas.api;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class CanvasPathtFillType extends View {

    public CanvasPathtFillType(Context context) {
        super(context);
    }

    public CanvasPathtFillType(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasPathtFillType(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Path path = new Path();
        path.addCircle(300,300,100, Path.Direction.CCW);
        path.addCircle(380,300,100, Path.Direction.CCW);
        Paint paint = new Paint();

        //WINDING 非零环绕数原则
        path.setFillType(Path.FillType.WINDING);
        canvas.drawPath(path,paint);



        Path path2 = new Path();
        path2.addCircle(300,550,100, Path.Direction.CCW);
        path2.addCircle(380,550,100, Path.Direction.CW);

        //WINDING 非零环绕数原则
        path2.setFillType(Path.FillType.WINDING);
        canvas.drawPath(path2,paint);


        Path path3 = new Path();
        path3.addCircle(300,800,100, Path.Direction.CCW);
        path3.addCircle(380,800,100, Path.Direction.CCW);


        //WINDING 非零环绕数原则
        path3.setFillType(Path.FillType.INVERSE_WINDING);
        canvas.drawPath(path3,paint);



        Path path4 = new Path();
        path4.addCircle(300,1050,100, Path.Direction.CCW);
        path4.addCircle(380,1050,100, Path.Direction.CW);

        //WINDING 非零环绕数原则
        path4.setFillType(Path.FillType.INVERSE_WINDING);
        canvas.drawPath(path4,paint);



        Path path5 = new Path();
        path5.addCircle(650,300,100, Path.Direction.CCW);
        path5.addCircle(720,300,100, Path.Direction.CCW);

        //WINDING 奇偶原则
        path5.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path5,paint);



        Path path6 = new Path();
        path6.addCircle(650,550,100, Path.Direction.CCW);
        path6.addCircle(720,550,100, Path.Direction.CW);

        path6.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        canvas.drawPath(path6,paint);


    }

}
