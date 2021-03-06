package com.liaoli.canvas.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.liaoli.canvas.R;

import java.util.List;

/**
 * /**
 * Created by liaoli on 2016/4/11 0011.
 */
public class ViewPagerIndicator extends LinearLayout {
    /**
     * 绘制Indicator的画笔
     */
    private Paint mPaint;
    /**
     * path构成一个形状
     */
    private Path mPath;

    private Path mBackPath;

    /**
     * Indicator的宽度
     */
    private int mTriangleWidth;
    /**
     * Indicator的高度
     */
    private int mTriangleHeight;

    /**
     * Indicator的宽度为单个Tab的1/6
     */
    private static final float RADIO_TRIANGEL = 1.0f / 6;
    /**
     * Indicator的最大宽度
     */
    private final int DIMENSION_TRIANGEL_WIDTH = (int) (getScreenWidth() / 3 * RADIO_TRIANGEL);

    /**
     * 初始时，Indicator指示器的偏移量
     */
    private int mInitTranslationX;
    /**
     * 手指滑动时的偏移量
     */
    private float mTranslationX;

    /**
     * 默认的Tab数量
     */
    private static final int COUNT_DEFAULT_TAB = 4;
    /**
     * tab数量
     */
    private int mTabVisibleCount = COUNT_DEFAULT_TAB;

    /**
     * tab上的内容
     */
    private List<String> mTabTitles;
    /**
     * 与之绑定的ViewPager
     */
    public ViewPager mViewPager;

    /**
     * 标题正常时的颜色
     */
    private static final int COLOR_TEXT_NORMAL = 0x77F5FFFF;
    /**
     * 标题选中时的颜色
     */
    private static final int COLOR_TEXT_HIGHLIGHTCOLOR = 0xFFFFFFFF;

    /**
     * indicator的默认颜色
     */
    private static final int COLOR_INDICATOR_COLOR = 0xFFFFFFFF;
    private static final String TAG = "ViewPagerIndicator";

    private int mIndicatorColor;

    private int mTextNomalColor;

    private int mTextSelectedColor;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 获得自定义属性，tab的数量
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ViewPagerIndicator);
        mTabVisibleCount = a.getInt(R.styleable.ViewPagerIndicator_item_count,
                COUNT_DEFAULT_TAB);
        if (mTabVisibleCount < 0)
            mTabVisibleCount = COUNT_DEFAULT_TAB;

        mIndicatorColor = a.getColor(
                R.styleable.ViewPagerIndicator_indicator_color,
                COLOR_INDICATOR_COLOR);

        mTextNomalColor = a.getColor(
                R.styleable.ViewPagerIndicator_text_normal_color,
                COLOR_TEXT_NORMAL);

        mTextSelectedColor = a.getColor(
                R.styleable.ViewPagerIndicator_text_selected_color,
                COLOR_TEXT_HIGHLIGHTCOLOR);

        a.recycle();

        // 初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mIndicatorColor);
        mPaint.setStyle(Style.FILL);

        /**
         *
         */
        mPaint.setPathEffect(new CornerPathEffect(3));

    }

    /**
     * 绘制指示器
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        // 画笔平移到正确的位置


        canvas.translate(mInitTranslationX + mTranslationX, getHeight() + 1);
        mPaint.setColor(mIndicatorColor);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();

        super.dispatchDraw(canvas);
    }


    private void drawBackPath(Canvas canvas) {
        canvas.save();
        canvas.translate(0, getHeight() + 1);
        mPaint.setColor(getResources().getColor(R.color.live_pager_line_color));

        canvas.drawPath(mBackPath, mPaint);
        canvas.restore();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBackPath(canvas);
        super.onDraw(canvas);
    }

    /**
     * 初始化Indicator的宽度
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTriangleWidth = (int) (w / mTabVisibleCount * RADIO_TRIANGEL);// 1/6 of
        // width
        mTriangleWidth = Math.min(DIMENSION_TRIANGEL_WIDTH, mTriangleWidth);

        // 初始化Indicator
        initTriangle();

        // 初始时的偏移量
        mInitTranslationX = getWidth() / mTabVisibleCount / 2 - mTriangleWidth
                / 2;
    }

    /**
     * 设置可见的tab的数量
     *
     * @param count
     */
    public void setVisibleTabCount(int count) {
        this.mTabVisibleCount = count;
    }

    /**
     * 设置tab的标题内容 可选，可以自己在布局文件中写死
     *
     * @param datas
     */
    public void setTabItemTitles(List<String> datas) {
        // 如果传入的list有值，则移除布局文件中设置的view
        if (datas != null && datas.size() > 0) {
            this.removeAllViews();
            this.mTabTitles = datas;
//
//            for (String title : mTabTitles) {
//                // 添加view
//                addView(generateTextView(R.drawable.love_selected, title));
//            }

            String title;
            int res = 0;
            for (int i = 0; i < datas.size(); i++) {
                // 添加view
                res = R.mipmap.ic_launcher;
                title = datas.get(i);

                addView(generateTextView(res, title));
            }

            // 设置item的click事件
            setItemClickEvent();
        }

    }

    /**
     * 对外的ViewPager的回调接口
     *
     * @author zhy
     */
    public interface PageChangeListener {
        void onPageScrolled(int position, float positionOffset,
                            int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }

    // 对外的ViewPager的回调接口
    private PageChangeListener onPageChangeListener;
    protected int CurrentPostion;

    // 对外的ViewPager的回调接口的设置
    public void setOnPageChangeListener(PageChangeListener pageChangeListener) {
        this.onPageChangeListener = pageChangeListener;
    }

    // 设置关联的ViewPager
    public void setViewPager(ViewPager mViewPager, int pos) {
        this.mViewPager = mViewPager;

        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {


                // 设置字体颜色高亮
                //resetTextViewColor();
                resetTextViewColor_2();

                // highLightTextView(position);

                highLightTextView_2(position);

                // 回调
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                // 滚动
                scroll(position, positionOffset);

                // 回调
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrolled(position,
                            positionOffset, positionOffsetPixels);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    //正在滑动   pager处于正在拖拽中


                } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                    //pager正在自动沉降，相当于松手后，pager恢复到一个完整pager的过程

                } else if (state == ViewPager.SCROLL_STATE_IDLE) {
                    //空闲状态  pager处于空闲状态
                    CurrentPostion = ViewPagerIndicator.this.mViewPager.getCurrentItem();
                }


                // 回调
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrollStateChanged(state);
                }

            }
        });
        // 设置当前页
        mViewPager.setCurrentItem(pos);
        // 高亮
        // highLightTextView(pos);
        highLightTextView_2(pos);

    }

    /**
     * 高亮文本
     *
     * @param position
     */
    protected void highLightTextView(int position) {
        View view = getChildAt(position);
        if (view instanceof LinearLayout) {

            LinearLayout ll = (LinearLayout) view;
            ((TextView) (ll.getChildAt(0))).setTextColor(mTextSelectedColor);
        }

    }


    /**
     * 高亮文本
     *
     * @param position
     */
    protected void highLightTextView_2(int position) {
        View view = getChildAt(position);
        if (view instanceof ViewPagerIndicatorItem) {

            ViewPagerIndicatorItem item = (ViewPagerIndicatorItem) view;

            item.setTextColor(mTextSelectedColor);
            item.setScaleX(1.1f);
            item.setScaleY(1.1f);

        }

    }

    /**
     * 重置文本颜色
     */
    private void resetTextViewColor() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof LinearLayout) {

                LinearLayout ll = (LinearLayout) view;
                ((TextView) (ll.getChildAt(0))).setTextColor(mTextNomalColor);

            }
        }
    }

    /**
     * 重置文本颜色
     */
    private void resetTextViewColor_2() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof ViewPagerIndicatorItem) {
                ViewPagerIndicatorItem item = (ViewPagerIndicatorItem) view;
                item.setTextColor(mTextNomalColor);

            }
        }
    }

    /**
     * 设置点击事件
     */
    public void setItemClickEvent() {
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            final int j = i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(j);
                }
            });
        }
    }

    /**
     * 根据标题生成我们的TextView
     *
     * @param text
     * @return
     */
    private View generateTextView(String text) {


        TextView tv = new TextView(getContext());

        LayoutParams lp = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(mTextNomalColor);
        tv.setText(text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tv.setLayoutParams(lp);

        LinearLayout linearLayout = new LinearLayout(getContext());

        LayoutParams lp_v = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        lp_v.width = getScreenWidth() / mTabVisibleCount;
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.addView(tv);
        linearLayout.setLayoutParams(lp_v);


        return linearLayout;
    }


    /**
     * 根据标题生成我们的TextView
     *
     * @param text
     * @return
     */
    private View generateTextView(int res, String text) {

        ViewPagerIndicatorItem item = new ViewPagerIndicatorItem(getContext());

        item.setText(text);

        item.setIconRes(res);

        LayoutParams lp_v = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        lp_v.width = getScreenWidth() / mTabVisibleCount;


        item.setLayoutParams(lp_v);

        return item;
    }

    /**
     * 初始化指示器
     */
    private void initTriangle() {
        mPath = new Path();

        mTriangleHeight = dip2px(getContext(), 3);
        mPath.moveTo(0, 0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth, -mTriangleHeight);
        mPath.lineTo(0, -mTriangleHeight);
        mPath.close();


        mBackPath = new Path();
        mBackPath.moveTo(0, 0);
        mBackPath.lineTo(getScreenWidth(), 0);
        mBackPath.lineTo(getScreenWidth(), -mTriangleHeight);
        mBackPath.lineTo(0, -mTriangleHeight);
        mBackPath.close();
    }


    /**
     * 计算indicator的宽度
     *
     * @param current
     * @param next
     * @param offset
     */
    private void calculatingndicatorWidth_2(int current, int next, float offset) {
        //动态计算
        ViewPagerIndicatorItem CurrentLayut;
        View CurrentTv = null;
        ViewPagerIndicatorItem nextLayut;
        View nextTv = null;

        CurrentLayut = (ViewPagerIndicatorItem) getChildAt(current);
        CurrentTv = CurrentLayut.getChildAt(0);

        nextLayut = (ViewPagerIndicatorItem) getChildAt(next);
        nextTv = nextLayut.getChildAt(0);
        mTriangleWidth = (int) (CurrentTv.getWidth() + (nextTv.getWidth() - CurrentTv.getWidth()) * offset);

    }

    /**
     * 计算indicator的宽度
     *
     * @param current
     * @param next
     * @param offset
     */
    private void calculatingndicatorWidth(int current, int next, float offset) {
        LinearLayout CurrentLayut;
        TextView CurrentTv = null;
        LinearLayout nextLayut;
        TextView nextTv = null;

        CurrentLayut = (LinearLayout) getChildAt(current);
        CurrentTv = (TextView) CurrentLayut.getChildAt(0);

        nextLayut = (LinearLayout) getChildAt(next);
        nextTv = (TextView) nextLayut.getChildAt(0);
        mTriangleWidth = (int) (CurrentTv.getWidth() + (nextTv.getWidth() - CurrentTv.getWidth()) * offset);
    }

    /**
     * 指示器跟随手指滚动，以及容器滚动
     *
     * @param position
     * @param offset
     */
    public void scroll(int position, float offset) {
        /**
         * <pre>
         *  0-1:position=0 ;1-0:postion=0;
         * </pre>
         */
        // 不断改变偏移量，invalidate
        mTranslationX = getWidth() / mTabVisibleCount * (position + offset);


        int tabWidth = getScreenWidth() / mTabVisibleCount;


        /**
         * 在滑动的过程中动态的计算indicator的宽度
         */
        if (position == CurrentPostion) {
            if (CurrentPostion == mTabTitles.size() - 1) {
                return;
            }

            //calculatingndicatorWidth(position, position + 1, offset);
            calculatingndicatorWidth_2(position, position + 1, offset);

        } else if (position < CurrentPostion) {

            //  calculatingndicatorWidth(CurrentPostion, position, 1 - offset);
            calculatingndicatorWidth_2(CurrentPostion, position, 1 - offset);

        } else if (position > CurrentPostion) {

            // calculatingndicatorWidth(position, CurrentPostion, offset);
            calculatingndicatorWidth_2(position, CurrentPostion, offset);

        }


        mInitTranslationX = getWidth() / mTabVisibleCount / 2 - mTriangleWidth
                / 2;

        reSetIndicatorwidth();


        // 容器滚动，当移动到倒数最后一个的时候，开始滚动
        if (offset > 0 && position >= (mTabVisibleCount - 2)
                && getChildCount() > mTabVisibleCount
                && position < (getChildCount() - 2)) {
            if (mTabVisibleCount != 1) {
                this.scrollTo((position - (mTabVisibleCount - 2)) * tabWidth
                        + (int) (tabWidth * offset), 0);
            } else
            // 为count为1时 的特殊处理
            {
                this.scrollTo(position * tabWidth + (int) (tabWidth * offset),
                        0);
            }
        }

        invalidate();
    }

    /**
     * 重置Indicator的宽度
     */
    private void reSetIndicatorwidth() {
        mPath.reset();
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth, -mTriangleHeight);
        mPath.lineTo(0, -mTriangleHeight);
        mPath.close();
    }

    /**
     * 设置布局中view的一些必要属性；如果设置了setTabTitles，布局中view则无效
     */
    @Override
    protected void onFinishInflate() {
        Log.e("TAG", "onFinishInflate");
        super.onFinishInflate();

        int cCount = getChildCount();

        if (cCount == 0)
            return;

        for (int i = 0; i < cCount; i++) {
            View view = getChildAt(i);
            LayoutParams lp = (LayoutParams) view
                    .getLayoutParams();
            lp.weight = 0;
            lp.width = getScreenWidth() / mTabVisibleCount;
            view.setLayoutParams(lp);
        }
        // 设置点击事件
        setItemClickEvent();

    }

    /**
     * 获得屏幕的宽度
     *
     * @return
     */
    public int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}