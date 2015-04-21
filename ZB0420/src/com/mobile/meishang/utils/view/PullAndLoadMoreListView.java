package com.mobile.meishang.utils.view;
//package com.ivpoints.mobile.utils.view;
//
//import com.ivpoints.mobile.R;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.View;
//import android.widget.AbsListView;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//
//
///*
// * Copyright (C) 2012 Fabian Leon Ortega <http://orleonsoft.blogspot.com/,
// *  http://yelamablog.blogspot.com/>
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//public class PullAndLoadMoreListView extends MyListView
//{
//    /** ��ǰ�� */
//    private int mLastItem;
//    private Boolean hasData = true;
//
//    // Listener to process load more items when user reaches the end of the list
//    private OnLoadMoreListener mOnLoadMoreListener;
//    // To know if the list is loading more items
//    private boolean mIsLoadingMore = false;
//
//    // footer
//    private RelativeLayout mFooterView;
//    private ProgressBar mProgressBarLoadMore;
//    private View loading_ind;
//    private Button nextPageRetry;
//
//    public PullAndLoadMoreListView(Context context)
//    {
//        super(context);
//        initComponent(context);
//    }
//
//    public PullAndLoadMoreListView(Context context, AttributeSet attrs)
//    {
//        super(context, attrs);
//        initComponent(context);
//    }
//
//    public void initComponent(Context context)
//    {
//
//        mFooterView = (RelativeLayout) mInflater.inflate(R.layout.loadfootview,
//                this, false);
//        loading_ind = mFooterView.findViewById(R.id.loading_ind);
//        nextPageRetry = (Button) mFooterView.findViewById(R.id.retry);
//        nextPageRetry.setOnClickListener(new OnClickListener()
//        {
//
//            @Override
//            public void onClick(View view)
//            {
//                if (mOnLoadMoreListener != null)
//                {
//                    loading_ind.setVisibility(View.VISIBLE);
//                    nextPageRetry.setVisibility(View.GONE);
//                    mOnLoadMoreListener.onLoadMore();
//                }
//            }
//        });
//        mFooterView.setVisibility(View.GONE);
//        addFooterView(mFooterView);
//    }
//
//    /**
//     * Register a callback to be invoked when this list reaches the end (last
//     * item be visible)
//     * 
//     * @param onLoadMoreListener
//     *            The callback to run.
//     */
//
//    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener)
//    {
//        mOnLoadMoreListener = onLoadMoreListener;
//    }
//
//    /*
//     * (non-Javadoc)
//     * 
//     * @see
//     * com.eol.mobile.teacher.utils.view.MyListView#onScrollStateChanged(android
//     * .widget.AbsListView, int)
//     */
//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState)
//    {
//        super.onScrollStateChanged(view, scrollState);
//        // System.out.println("mLastItem" + mLastItem);
//        // System.out.println("view.getLastVisiblePosition()"
//        // + view.getLastVisiblePosition());
//        // System.out.println(scrollState ==
//        // OnScrollListener.SCROLL_STATE_IDLE);
//        // System.out.println(mLastItem == view.getLastVisiblePosition());
//
//        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
//                && mLastItem == (view.getLastVisiblePosition() - 1))
//        {
//            if (hasData)
//            {
//                onLoadMore();
//                // hasData = false;
//            }
//        }
//    }
//
//    public void hasData(int totalQuantity, int currentlyItemQuantity)
//    {
//        // System.out
//        // .println(currentlyItemQuantity + "^^^^^^^^^^" + totalQuantity);
//
//        if (currentlyItemQuantity >= totalQuantity)
//        {
//            if (getFooterViewsCount() == 1)
//                removeFooterView(mFooterView);
//            hasData = false;
//        }
//        else
//        {
//            mFooterView.setVisibility(View.VISIBLE);
//            mLastItem = currentlyItemQuantity;
//            hasData = true;
//        }
//    }
//
//    public Boolean getHasData()
//    {
//        return hasData;
//    }
//
//    public void onLoadMore()
//    {
//        if (mOnLoadMoreListener != null)
//        {
//            mOnLoadMoreListener.onLoadMore();
//        }
//    }
//
//    // /**
//    // * Notify the loading more operation has finished
//    // */
//    // public void onLoadMoreComplete()
//    // {
//    // mIsLoadingMore = false;
//    // }
//
//    /**
//     * Interface definition for a callback to be invoked when list reaches the
//     * last item (the user load more items in the list)
//     */
//    public interface OnLoadMoreListener
//    {
//        /**
//         * Called when the list reaches the last item (the last item is visible
//         * to the user) A call to
//         * {@link PullAndLoadMoreListView #onLoadMoreComplete()} is expected to
//         * indicate that the loadmore has completed.
//         */
//        public void onLoadMore();
//    }
//}
