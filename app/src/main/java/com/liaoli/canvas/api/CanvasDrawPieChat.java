package com.liaoli.canvas.api;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

    float[] itemCount = new float[]{200, 300, 534, 223.5f, 444, 650};

    int  ridus = 150;

    int lineLength = 100;

    int interval = 20;

    Point center = new Point(400,400);

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

        if(itemBeans == null){
            return;
        }

        RectF rectF = new RectF(center.x - ridus,center.y - ridus,center.x + ridus,center.y + ridus);

        Paint paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        for(ItemBean itemBean :itemBeans){
            paint.setColor(itemBean.percentColor);
            canvas.drawArc(rectF,itemBean.startArc,itemBean.percent * 360,true,paint);
            paint.setColor(Color.BLACK);

            String text = itemBean.percent + "%";
            int textStartX,textStartY ;
            Point lineStart, lineEnd;
            if(itemBean.onLeft){


                Rect rect = new Rect();
                paint.getTextBounds(text, 0, text.length(), rect);
                int w = rect.width();
                int h = rect.height();

                textStartX = itemBean.point.x - lineLength - interval - w;
                textStartY = itemBean.point.y;

                lineStart = new Point(itemBean.point);

                lineEnd = new Point(itemBean.point.x - lineLength ,itemBean.point.y);


            }else{


                lineStart = new Point(itemBean.point);
                lineEnd = new Point(itemBean.point.x + lineLength,itemBean.point.y);

                textStartX = itemBean.point.x + lineLength + interval;
                textStartY = itemBean.point.y;


            }
            canvas.drawLine(lineStart.x ,lineStart.y,lineEnd.x,lineEnd.y,paint);
            paint.setColor(itemBean.nameColor);
            canvas.drawText(text,textStartX ,textStartY,paint);

        }

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
            itemBean.percentColor =  Color.argb(255,r,g,b);
            itemBean.startArc =  startDegress;
            itemBean.endArc =  startDegress + ip * 360;
            itemBean.lineStartDegree =   (itemBean.startArc + itemBean.endArc)/2 ;

            if(itemBean.lineStartDegree >90 && itemBean.lineStartDegree < 270 ){
                itemBean.onLeft = true;

            }else{
                itemBean.onLeft = false;
            }

            itemBean.point.x = (int) (center.x + ridus*Math.cos(itemBean.lineStartDegree/360 * Math.PI *2));

            itemBean.point.y = (int) (center.y + ridus*Math.sin(itemBean.lineStartDegree/360 * Math.PI *2));


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


    class ItemBean{
        float count;
        float startArc,endArc;
        float percent;
        float lineStartDegree;
        Point point = new Point();
        int percentColor;
        String name;
        int nameColor = Color.GRAY;
        boolean onLeft;
        int stringWidh;
        int stringHight;


    }

}
