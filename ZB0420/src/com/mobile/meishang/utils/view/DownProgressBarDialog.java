package com.mobile.meishang.utils.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mobile.meishang.R;

public class DownProgressBarDialog extends AlertDialog
{
    private static final DecimalFormat df = new DecimalFormat("###.##");
    public static final int M = 1024 * 1024;
    public static final int K = 1024;
    private Context mContext;
    private Handler mViewUpdateHandler;
    private ProgressBar mProgress;
    private TextView mMessageView;
    private TextView mProgressNumber;
    private TextView mProgressPercent;

    private NumberFormat mProgressPercentFormat;
    private double mMax;
    private int middle = K;
    private double mProgressVal;
    private CharSequence mMessage;
    private boolean mHasStarted;
    private int prev = 0;

    /**
     * @param context
     */
    public DownProgressBarDialog(Context context)
    {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mViewUpdateHandler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);

                /* Update the number and percent */
                double percent = (double) mProgressVal / (double) mMax;
                if (prev != (int) (percent * 100))
                {
                    mProgress.setProgress((int) (percent * 100));
                    mProgressNumber.setText(df.format(mProgressVal)
                            + (middle == K ? "K" : "M") + "/" + df.format(mMax)
                            + (middle == K ? "K" : "M"));
                    SpannableString tmp = new SpannableString(
                            mProgressPercentFormat.format(percent));
                    tmp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
                            0, tmp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mProgressPercent.setText(tmp);
                    prev = (int) (percent * 100);
                }
            }
        };
        View view = inflater.inflate(R.layout.alert_dialog_progress, null);
        mMessageView = (TextView) view.findViewById(R.id.message);
        mProgress = (ProgressBar) view.findViewById(R.id.progress);
        mProgressNumber = (TextView) view.findViewById(R.id.progress_number);
        mProgressPercent = (TextView) view.findViewById(R.id.progress_percent);
        mProgressPercentFormat = NumberFormat.getPercentInstance();
        mProgressPercentFormat.setMaximumFractionDigits(0);
        setView(view);
        if (mMessage != null)
        {
            setMessage(mMessage);
        }
        onProgressChanged();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        mHasStarted = true;
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        mHasStarted = false;
    }

    /**
     * ����
     * 
     * @Description:
     * @Date 2013-5-2
     */
    public void setProgress(double value)
    {
        if (mHasStarted)
        {
            mProgressVal = value / middle;
            onProgressChanged();
        }
        else
        {
            mProgressVal = value;
        }
    }

    public double getProgress()
    {
        return mProgressVal;
    }

    public double getMax()
    {
        return mMax;
    }

    /**
     * @Description:
     * @Date 2013-5-2
     */
    public void setMax(double max)
    {
        if (max > M)
        {
            middle = M;
        }
        else
        {
            middle = K;
        }
        mMax = max / middle;
    }

    @Override
    public void setMessage(CharSequence message)
    {
        if (mMessageView != null)
        {
            mMessageView.setText(message);

        }
        else
        {
            mMessage = message;
        }
    }

    private void onProgressChanged()
    {
        mViewUpdateHandler.sendEmptyMessage(0);
    }

}
