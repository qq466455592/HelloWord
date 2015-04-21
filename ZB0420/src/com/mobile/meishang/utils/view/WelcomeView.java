/**
 *@Copyright:Copyright (c) 2012 - 2100
 *@Company:suning.com
 */
package com.mobile.meishang.utils.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @Title:��ӭ���涯��
 * @Description:
 * @Author:wei
 * @Since:2012-11-27
 * @Version:
 */
public class WelcomeView extends ImageView implements Runnable
{
    private boolean isStop = false;

    private int[] imageIds;
    private int index = 0;
    private int length = 1;

    public WelcomeView(Context context)
    {
        this(context, null);
    }

    public WelcomeView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void setImageIds(int[] imageId)
    {
        this.imageIds = imageId;
        if (imageIds != null && imageIds.length > 0)
        {
            length = imageIds.length;
        }
    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        isStop = true;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (imageIds != null && imageIds.length > 0)
        {
            this.setImageResource(imageIds[index]);
        }
    }

    @Override
    public void run()
    {
        while (!isStop)
        {
            // index = ++index % length;
            index = (index + 1) % length;
            postInvalidate();
            try
            {
                Thread.sleep(300);
            }
            catch (InterruptedException e)
            {
                // e.printStackTrace();
            }
        }
    }

    public void startAnim()
    {
        new Thread(this).start();
    }

}
