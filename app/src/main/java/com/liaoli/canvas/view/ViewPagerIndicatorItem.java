package com.liaoli.canvas.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoli.canvas.R;


/**
 * Created by Administrator on 2016/5/24 0024.
 */
public class ViewPagerIndicatorItem extends LinearLayout {


    private ImageView icon;
    private TextView textView;

    public ViewPagerIndicatorItem(Context context) {
        this(context, null);
    }

    public ViewPagerIndicatorItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerIndicatorItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        setGravity(Gravity.CENTER);

        inflate(context, R.layout.viewpager_indicator_item_layout, this);

        icon = (ImageView) findViewById(R.id.viewpager_indicator_item_icon);

        textView = (TextView) findViewById(R.id.viewpager_indicator_item_text);

    }


    public void setIconRes(int res) {
        icon.setImageResource(res);
    }

    public void setText(int resid){
        textView.setText(resid);
    }

    public void setText(String text){
        textView.setText(text);
    }

    public  void setTextColor(int color){
        textView.setTextColor(color);
    }

    public  void setTextSize(float textSize){
        textView.setTextSize(textSize);
    }

}
