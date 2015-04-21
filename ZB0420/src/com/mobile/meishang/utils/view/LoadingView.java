package com.mobile.meishang.utils.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobile.meishang.R;



/**
 * 
 * @Title:
 * @Description:
 * @Author:Administrator
 * @Since:2013-5-2
 * @Version:
 */
public class LoadingView extends RelativeLayout implements OnClickListener
{

    private TextView nextPageRetry;
    private View loading_ind;
    private LoadEvent loadEvent;
    private TextView mTextView;
    private ProgressBar loadprogressbar;

    public LoadingView(Context context)
    {

        super(context);
    }

    public LoadingView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        loading_ind = this.findViewById(R.id.loading_ind);
        nextPageRetry = (TextView) this.findViewById(R.id.retry);
        mTextView = (TextView) this.findViewById(R.id.hite_text);
        nextPageRetry.setOnClickListener(this);
        loadprogressbar = (ProgressBar) this.findViewById(R.id.loadprogressbar);
        showRetryBtn(false);
    }

    @Override
    public void onClick(View v)
    {
        showRetryBtn(false);
        if (loadEvent != null)
        {

            loadEvent.retryAgain(v);
        }
    }

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-2
     */
    public void showRetryBtn(boolean _show)
    {
        if (_show)
        {
            nextPageRetry.setVisibility(View.VISIBLE);
            loading_ind.setVisibility(View.GONE);
        }
        else
        {
            nextPageRetry.setVisibility(View.GONE);
            loading_ind.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 
     * @Title:
     * @Description:
     * @Author:Administrator
     * @Since:2013-5-2
     * @Version:
     */
    public interface LoadEvent
    {

        public void retryAgain(View v);

    }

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-2
     */
    public LoadEvent getLoadEvent()
    {
        return loadEvent;
    }

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-2
     */
    public void setLoadEvent(LoadEvent loadEvent)
    {
        this.loadEvent = loadEvent;
    }

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-2
     */
    public void setLoadHiteText(String _text)
    {
        if (mTextView != null)
        {
            mTextView.setText(_text);
        }

    }

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-2
     */
    public void showTextOnly()
    {
        showRetryBtn(false);
        if (loadprogressbar != null)
        {
            loadprogressbar.setVisibility(View.GONE);
        }

    }

}
