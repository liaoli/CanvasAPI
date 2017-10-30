package com.liaoli.canvas.api;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class CanvasDrawPieChat extends View {

    float[] itemCount = new float[]{200, 300, 534, 223.5f, 444, 650,500,30,100,546};

    int ridus = 150;

    int lineLength = 100;

    int interval = 20;

    float slope = (float) (Math.PI/12);

    Point center = new Point(400, 400);

    public CanvasDrawPieChat(Context context) {
        super(context);
    }

    public CanvasDrawPieChat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasDrawPieChat(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        List<ItemBean> itemBeans = getitemsPercent();

        if (itemBeans == null) {
            return;
        }

        RectF rectF = new RectF(center.x - ridus, center.y - ridus, center.x + ridus, center.y + ridus);

        Paint paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        for (ItemBean itemBean : itemBeans) {
            paint.setColor(itemBean.percentColor);
            canvas.drawArc(rectF, itemBean.startArc, itemBean.percent * 360, true, paint);
            paint.setColor(Color.BLACK);

            String text = itemBean.percent + "%";
            int textStartX, textStartY;
            Point lineStart, lineEnd, third = new Point();
            Path lPath = new Path();
            if (itemBean.onLeft) {

                Rect rect = new Rect();
                paint.getTextBounds(text, 0, text.length(), rect);
                int w = rect.width();
                int h = rect.height();


                lineStart = new Point(itemBean.point);
                lineEnd = new Point(itemBean.point.x - lineLength, itemBean.point.y);


                if (itemBean.onTop) {

                    third.x = (int) (lineEnd.x - 50 * Math.sin(slope));
                    third.y = (int) (lineEnd.y - 50 * Math.sin(slope));


                } else {
                    third.x = (int) (lineEnd.x - 50 * Math.sin(slope));
                    third.y = (int) (lineEnd.y + 50 * Math.sin(slope));

                }

                textStartX = third.x - interval - w;
                textStartY = third.y;

            } else {
                lineStart = new Point(itemBean.point);
                lineEnd = new Point(itemBean.point.x + lineLength, itemBean.point.y);


                if (itemBean.onTop) {

                    third.x = (int) (lineEnd.x + 50 * Math.sin(slope));
                    third.y = (int) (lineEnd.y - 50 * Math.sin(slope));


                } else {
                    third.x = (int) (lineEnd.x + 50 * Math.sin(slope));
                    third.y = (int) (lineEnd.y + 50 * Math.sin(slope));

                }
                textStartX = third.x + interval;
                textStartY = third.y;

            }


            lPath.moveTo(lineStart.x, lineStart.y);
            lPath.lineTo(lineEnd.x, lineEnd.y);
            lPath.lineTo(third.x, third.y);


            //canvas.drawLine(lineStart.x, lineStart.y, lineEnd.x, lineEnd.y, paint);

            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(lPath, paint);
            paint.setColor(itemBean.nameColor);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawText(text, textStartX, textStartY, paint);

        }

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        center.x = (int) (w * 1.0 / 2);

        center.y = (int) (h * 1.0 / 2);

    }

    private List<ItemBean> getitemsPercent() {
        float allSum = getAllItemSum(itemCount);

        float startDegress = 0;
        ArrayList<ItemBean> itemPercents = new ArrayList<>();
        for (float ic : itemCount) {
            float ip = ic / allSum;
            ItemBean itemBean = new ItemBean();

            itemBean.percent = ip;

            int r = (int) (Math.random() * 255);
            int g = (int) (Math.random() * 255);
            int b = (int) (Math.random() * 255);
            itemBean.percentColor = Color.argb(255, r, g, b);
            itemBean.startArc = startDegress;
            itemBean.endArc = startDegress + ip * 360;
            itemBean.lineStartDegree = (itemBean.startArc + itemBean.endArc) / 2;

            if (itemBean.lineStartDegree > 90 && itemBean.lineStartDegree < 270) {
                itemBean.onLeft = true;

            } else {
                itemBean.onLeft = false;
            }


            if (itemBean.lineStartDegree > 0 && itemBean.lineStartDegree < 180) {
                itemBean.onTop = false;
            } else {
                itemBean.onTop = true;
            }

            itemBean.point.x = (int) (center.x + ridus * Math.cos(itemBean.lineStartDegree / 360 * Math.PI * 2));

            itemBean.point.y = (int) (center.y + ridus * Math.sin(itemBean.lineStartDegree / 360 * Math.PI * 2));


            itemPercents.add(itemBean);

            startDegress += ip * 360;

        }

        return itemPercents;
    }

    private float getAllItemSum(float[] itemCount) {
        float allSum = 0;
        for (float ic : itemCount) {
            allSum += ic;
        }
        return allSum;
    }


    class ItemBean {
        float count;
        float startArc, endArc;
        float percent;
        float lineStartDegree;
        Point point = new Point();
        int percentColor;
        String name;
        int nameColor = Color.GRAY;
        boolean onLeft;
        boolean onTop;
        int stringWidh;
        int stringHight;


    }

}
