package com.mobile.meishang.utils.view;
///**
// *@Copyright:Copyright (c) 2012 - 2100
// *@Company:suning.com
// */
//package com.ivpoints.mobile.utils.view;
//
//import com.ivpoints.mobile.R;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.LinearInterpolator;
//import android.view.animation.RotateAnimation;
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
//import android.widget.ImageView;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//
///**
// * @Title:
// * @Description:
// * @Author:12071520
// * @Since:2013-10-11
// * @Version:
// */
//public class PullToRefreshListView extends ListView implements OnScrollListener
//{
//    private static final int TAP_TO_REFRESH = 1;
//    private static final int PULL_TO_REFRESH = 2;
//    private static final int RELEASE_TO_REFRESH = 3;
//    protected static final int REFRESHING = 4;
//
//    private RotateAnimation mFlipAnimation;
//    private RotateAnimation mReverseFlipAnimation;
//    protected LayoutInflater mInflater;
//    // header
//    private RelativeLayout mRefreshView;
//    private TextView mRefreshViewText;
//    private ImageView mRefreshViewImage;
//    private ProgressBar mRefreshViewProgress;
//    private TextView mRefreshViewLastUpdated;
//
//    private int mRefreshOriginalTopPadding;
//    protected int mRefreshState;
//
//    private OnRefreshListener mOnRefreshListener;
//    private int mRefreshViewHeight;
//    private OnScrollListener mOnScrollListener;
//    private boolean mBounceHack;
//    private int mLastMotionY;
//
//    protected int mCurrentScrollState;
//
//    /**
//     * @param context
//     */
//    public PullToRefreshListView(Context context)
//    {
//        super(context);
//        init(context);
//    }
//
//    public PullToRefreshListView(Context context, AttributeSet attrs)
//    {
//        super(context, attrs);
//        init(context);
//    }
//
//    public PullToRefreshListView(Context context, AttributeSet attrs,
//            int defStyle)
//    {
//        super(context, attrs, defStyle);
//        init(context);
//    }
//
//    protected void init(Context context)
//    {
//        mFlipAnimation = new RotateAnimation(0, -180,
//                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
//                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//        mFlipAnimation.setInterpolator(new LinearInterpolator());
//        mFlipAnimation.setDuration(250);
//        mFlipAnimation.setFillAfter(true);
//        mReverseFlipAnimation = new RotateAnimation(-180, 0,
//                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
//                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//        mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
//        mReverseFlipAnimation.setDuration(250);
//        mReverseFlipAnimation.setFillAfter(true);
//
//        mInflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        // header
//        mRefreshView = (RelativeLayout) mInflater.inflate(
//                R.layout.pull_to_refresh_header, this, false);
//        mRefreshViewText = (TextView) mRefreshView
//                .findViewById(R.id.pull_to_refresh_text);
//        mRefreshViewImage = (ImageView) mRefreshView
//                .findViewById(R.id.pull_to_refresh_image);
//        mRefreshViewProgress = (ProgressBar) mRefreshView
//                .findViewById(R.id.pull_to_refresh_progress);
//        mRefreshViewLastUpdated = (TextView) mRefreshView
//                .findViewById(R.id.pull_to_refresh_updated_at);
//
//        mRefreshViewImage.setMinimumHeight(50);
//        mRefreshView.setOnClickListener(new OnClickRefreshListener());
//        mRefreshOriginalTopPadding = mRefreshView.getPaddingTop();
//
//        mRefreshState = TAP_TO_REFRESH;
//
//        addHeaderView(mRefreshView);
//
//        super.setOnScrollListener(this);
//
//        measureView(mRefreshView);
//        mRefreshViewHeight = mRefreshView.getMeasuredHeight();
//
//    }
//
//    @Override
//    protected void onAttachedToWindow()
//    {
//        // have to ask super to attach to window, otherwise it won't scroll in
//        // jelly bean.
//        super.onAttachedToWindow();
//        setSelection(1);
//    }
//
//    @Override
//    public void setAdapter(ListAdapter adapter)
//    {
//        super.setAdapter(adapter);
//
//        setSelection(1);
//    }
//
//    /*
//     * (non-Javadoc)
//     * 
//     * @see
//     * android.widget.AbsListView#setOnScrollListener(android.widget.AbsListView
//     * .OnScrollListener)
//     */
//    @Override
//    public void setOnScrollListener(OnScrollListener l)
//    {
//        mOnScrollListener = l;
//    }
//
//    /**
//     * Register a callback to be invoked when this list should be refreshed.
//     * 
//     * @param onRefreshListener
//     *            The callback to run.
//     */
//    public void setOnRefreshListener(OnRefreshListener onRefreshListener)
//    {
//        mOnRefreshListener = onRefreshListener;
//    }
//
//    /**
//     * Set a text to represent when the list was last updated.
//     * 
//     * @param lastUpdated
//     *            Last updated at.
//     */
//    public void setLastUpdated(CharSequence lastUpdated)
//    {
//        if (lastUpdated != null)
//        {
//            mRefreshViewLastUpdated.setVisibility(View.VISIBLE);
//            mRefreshViewLastUpdated.setText(lastUpdated);
//        }
//        else
//        {
//            mRefreshViewLastUpdated.setVisibility(View.GONE);
//        }
//    }
//
//    /*
//     * (non-Javadoc)
//     * 
//     * @see android.widget.AbsListView#onTouchEvent(android.view.MotionEvent)
//     */
//    @Override
//    public boolean onTouchEvent(MotionEvent event)
//    {
//        final int y = (int) event.getY();
//        mBounceHack = false;
//
//        switch (event.getAction())
//        {
//            case MotionEvent.ACTION_UP:
//                if (!isVerticalScrollBarEnabled())
//                {
//                    setVerticalScrollBarEnabled(true);
//                }
//                if (getFirstVisiblePosition() == 0
//                        && mRefreshState != REFRESHING)
//                {
//                    if ((mRefreshView.getBottom() >= mRefreshViewHeight || mRefreshView
//                            .getTop() >= 0)
//                            && mRefreshState == RELEASE_TO_REFRESH)
//                    {
//                        // Initiate the refresh
//                        mRefreshState = REFRESHING;
//                        prepareForRefresh();
//                        onRefresh();
//                    }
//                    else if (mRefreshView.getBottom() < mRefreshViewHeight
//                            || mRefreshView.getTop() <= 0)
//                    {
//                        // Abort refresh and scroll down below the refresh view
//                        resetHeader();
//                        setSelection(1);
//                    }
//                }
//                break;
//            case MotionEvent.ACTION_DOWN:
//                mLastMotionY = y;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                applyHeaderPadding(event);
//                break;
//        }
//        return super.onTouchEvent(event);
//    }
//
//    /*
//     * (non-Javadoc)
//     * 
//     * @see
//     * android.widget.AbsListView.OnScrollListener#onScrollStateChanged(android
//     * .widget.AbsListView, int)
//     */
//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState)
//    {
//        mCurrentScrollState = scrollState;
//
//        if (mCurrentScrollState == SCROLL_STATE_IDLE)
//        {
//            mBounceHack = false;
//        }
//
//        if (mOnScrollListener != null)
//        {
//            mOnScrollListener.onScrollStateChanged(view, scrollState);
//        }
//    }
//
//    /*
//     * (non-Javadoc)
//     * 
//     * @see android.widget.AbsListView.OnScrollListener#onScroll(android.widget.
//     * AbsListView, int, int, int)
//     */
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem,
//            int visibleItemCount, int totalItemCount)
//    {
//        // When the refresh view is completely visible, change the text to say
//        // "Release to refresh..." and flip the arrow drawable.
//        if (mCurrentScrollState == SCROLL_STATE_TOUCH_SCROLL
//                && mRefreshState != REFRESHING)
//        {
//            if (firstVisibleItem == 0)
//            {
//                mRefreshViewImage.setVisibility(View.VISIBLE);
//                if ((mRefreshView.getBottom() >= mRefreshViewHeight + 20 || mRefreshView
//                        .getTop() >= 0) && mRefreshState != RELEASE_TO_REFRESH)
//                {
//                    mRefreshViewText
//                            .setText(R.string.pull_to_refresh_release_label);
//                    mRefreshViewImage.clearAnimation();
//                    mRefreshViewImage.startAnimation(mFlipAnimation);
//                    mRefreshState = RELEASE_TO_REFRESH;
//                }
//                else if (mRefreshView.getBottom() < mRefreshViewHeight + 20
//                        && mRefreshState != PULL_TO_REFRESH)
//                {
//                    mRefreshViewText
//                            .setText(R.string.pull_to_refresh_pull_label);
//                    if (mRefreshState != TAP_TO_REFRESH)
//                    {
//                        mRefreshViewImage.clearAnimation();
//                        mRefreshViewImage.startAnimation(mReverseFlipAnimation);
//                    }
//                    mRefreshState = PULL_TO_REFRESH;
//                }
//            }
//            else
//            {
//                mRefreshViewImage.setVisibility(View.GONE);
//                resetHeader();
//            }
//        }
//        else if (mCurrentScrollState == SCROLL_STATE_FLING
//                && firstVisibleItem == 0 && mRefreshState != REFRESHING)
//        {
//            setSelection(1);
//            mBounceHack = true;
//        }
//        else if (mBounceHack && mCurrentScrollState == SCROLL_STATE_FLING)
//        {
//            setSelection(1);
//        }
//
//        if (mOnScrollListener != null)
//        {
//            mOnScrollListener.onScroll(view, firstVisibleItem,
//                    visibleItemCount, totalItemCount);
//        }
//    }
//
//    /**
//     * Invoked when the refresh view is clicked on. This is mainly used when
//     * there's only a few items in the list and it's not possible to drag the
//     * list.
//     */
//    private class OnClickRefreshListener implements OnClickListener
//    {
//
//        public void onClick(View v)
//        {
//            if (mRefreshState != REFRESHING)
//            {
//                prepareForRefresh();
//                onRefresh();
//            }
//        }
//
//    }
//
//    public void prepareForRefresh()
//    {
//        resetHeaderPadding();
//
//        mRefreshViewImage.setVisibility(View.GONE);
//        // We need this hack, otherwise it will keep the previous drawable.
//        mRefreshViewImage.setImageDrawable(null);
//        mRefreshViewProgress.setVisibility(View.VISIBLE);
//
//        // Set refresh view text to the refreshing label
//        mRefreshViewText.setText(R.string.pull_to_refresh_refreshing_label);
//
//        mRefreshState = REFRESHING;
//    }
//
//    /**
//     * Sets the header padding back to original size.
//     */
//    private void resetHeaderPadding()
//    {
//        mRefreshView.setPadding(mRefreshView.getPaddingLeft(),
//                mRefreshOriginalTopPadding, mRefreshView.getPaddingRight(),
//                mRefreshView.getPaddingBottom());
//    }
//
//    public void onRefresh()
//    {
//        if (mOnRefreshListener != null)
//        {
//            mOnRefreshListener.onRefresh();
//        }
//    }
//
//    /**
//     * Resets the list to a normal state after a refresh.
//     * 
//     * @param lastUpdated
//     *            Last updated at.
//     */
//    public void onRefreshComplete(CharSequence lastUpdated)
//    {
//        setLastUpdated(lastUpdated);
//        onRefreshComplete();
//    }
//
//    /**
//     * Resets the list to a normal state after a refresh.
//     */
//    public void onRefreshComplete()
//    {
//        resetHeader();
//
//        // If refresh view is visible when loading completes, scroll down to
//        // the next item.
//        if (mRefreshView.getBottom() > 0)
//        {
//            invalidateViews();
//            setSelection(1);
//        }
//    }
//
//    /**
//     * Interface definition for a callback to be invoked when list should be
//     * refreshed.
//     */
//    public interface OnRefreshListener
//    {
//        /**
//         * Called when the list should be refreshed.
//         * <p>
//         * A call to {@link PullToRefreshListView #onRefreshComplete()} is
//         * expected to indicate that the refresh has completed.
//         */
//        public void onRefresh();
//    }
//
//    private void measureView(View child)
//    {
//        ViewGroup.LayoutParams p = child.getLayoutParams();
//        if (p == null)
//        {
//            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//        }
//
//        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
//        int lpHeight = p.height;
//        int childHeightSpec;
//        if (lpHeight > 0)
//        {
//            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
//                    MeasureSpec.EXACTLY);
//        }
//        else
//        {
//            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
//                    MeasureSpec.UNSPECIFIED);
//        }
//        child.measure(childWidthSpec, childHeightSpec);
//    }
//
//    /**
//     * Resets the header to the original state.
//     */
//    private void resetHeader()
//    {
//        if (mRefreshState != TAP_TO_REFRESH)
//        {
//            mRefreshState = TAP_TO_REFRESH;
//
//            resetHeaderPadding();
//
//            // Set refresh view text to the pull label
//            mRefreshViewText.setText(R.string.pull_to_refresh_tap_label);
//            // Replace refresh drawable with arrow drawable
//            mRefreshViewImage
//                    .setImageResource(R.drawable.ic_pulltorefresh_arrow);
//            // Clear the full rotation animation
//            mRefreshViewImage.clearAnimation();
//            // Hide progress bar and arrow.
//            mRefreshViewImage.setVisibility(View.GONE);
//            mRefreshViewProgress.setVisibility(View.GONE);
//        }
//    }
//
//    private void applyHeaderPadding(MotionEvent ev)
//    {
//        // getHistorySize has been available since API 1
//        int pointerCount = ev.getHistorySize();
//
//        for (int p = 0; p < pointerCount; p++)
//        {
//            if (mRefreshState == RELEASE_TO_REFRESH)
//            {
//                if (isVerticalFadingEdgeEnabled())
//                {
//                    setVerticalScrollBarEnabled(false);
//                }
//
//                int historicalY = (int) ev.getHistoricalY(p);
//
//                // Calculate the padding to apply, we divide by 1.7 to
//                // simulate a more resistant effect during pull.
//                int topPadding = (int) (((historicalY - mLastMotionY) - mRefreshViewHeight) / 1.7);
//
//                mRefreshView.setPadding(mRefreshView.getPaddingLeft(),
//                        topPadding, mRefreshView.getPaddingRight(),
//                        mRefreshView.getPaddingBottom());
//            }
//        }
//    }
//}