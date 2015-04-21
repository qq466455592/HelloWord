package com.mobile.meishang.utils.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * 
 * @Title:MyLetterListView
 * @Description: 城市选择右边字母
 * @Author:11112760
 * @Since:2013-4-9
 * @Version:
 */
public class MyLetterListView extends View
{

    // 字母改变监听
    OnTouchingLetterChangedListener onTouchingLetterChangedListener;

    // 字母列表
    String[] ziMu = { "","热门", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
            "X", "Y", "Z" };

    // 选择索引
    int choose = -1;

    // 画笔
    Paint paint = new Paint();

    // 背景更换
    boolean showBkg = false;

    /**
     * 字母列表组件初始化
     * 
     * @param context
     * @param attrs
     * @param defStyle
     */
    public MyLetterListView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    /**
     * 初始化字母列表
     * 
     * @param context
     * @param attrs
     */
    public MyLetterListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    /**
     * 初始化字母列表
     * 
     * @param context
     */
    public MyLetterListView(Context context)
    {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (showBkg)
        {
            canvas.drawColor(Color.parseColor("#40000000"));
        }

        int height = getHeight();
        int width = getWidth();
        int singleHeight = height / ziMu.length;
        for (int i = 0; i < ziMu.length; i++)
        {
            paint.setColor(Color.WHITE);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true);
            paint.setTextSize(getRawSize(14F));
            if (i == choose)
            {
                paint.setColor(Color.parseColor("#3399ff"));
                paint.setFakeBoldText(true);
            }
            float xPos = width / 2 - paint.measureText(ziMu[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(ziMu[i], xPos, yPos, paint);
            paint.reset();
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        final int c = (int) (y / getHeight() * ziMu.length);
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                showBkg = true;
                if (oldChoose != c && listener != null)
                {
                    if (c > 0 && c < ziMu.length)
                    {
                        listener.onTouchingLetterChanged(ziMu[c]);
                        choose = c;
                        invalidate();
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                if (oldChoose != c && listener != null)
                {
                    if (c > 0 && c < ziMu.length)
                    {
                        listener.onTouchingLetterChanged(ziMu[c]);
                        choose = c;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                showBkg = false;
                choose = -1;
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return super.onTouchEvent(event);
    }

    /**
     * 设置滑动监听器
     * 
     * @param onTouchingLetterChangedListener
     */
    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener)
    {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    /**
     * 滑动监听接口
     * 
     * @author zhangbing
     * 
     */
    public interface OnTouchingLetterChangedListener
    {
        public void onTouchingLetterChanged(String s);
    }

    /**
     * 适配字体大小
     * 
     * @param unit
     * @param size
     * @return
     */
    private float getRawSize(float size)
    {
        Context c = getContext();
        Resources r;

        if (c == null)
        {
            r = Resources.getSystem();
        }
        else
        {
            r = c.getResources();
        }

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size,
                r.getDisplayMetrics());
    }
}
