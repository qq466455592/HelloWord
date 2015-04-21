package com.mobile.meishang.utils;

import android.app.Activity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.mobile.meishang.R;

/**
 * 分页类
 * 
 * @Title:
 * @Description:
 * @Author:11112760
 * @Since:2012-10-22
 * @Version:
 */

public class PageScrollListener implements OnScrollListener {
	private int mLastItem;
	private int mPageSize;
	private int mPageCount;
	private int mCurrentPage;
	private View mFootView;
	private boolean mIsLoading;
	private Activity mContext;
	private ListView mListView;
	private LoadDataListener mListener;

	/**
	 * 
	 * @param context
	 * @param listView
	 */
	public PageScrollListener(Activity context, ListView listView) {
		this.mContext = context;
		this.mListView = listView;
		init();
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		mLastItem = firstVisibleItem + visibleItemCount - 1;

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
				&& mLastItem == view.getLastVisiblePosition()) {
			isLoadData();
		}
	}

	/**
	 * 初始化
	 */
	private void isLoadData() {
		if (mIsLoading) {
			return;
		}
		if (mCurrentPage <= mPageCount && !mIsLoading) {
			mListener.loadMoreData();
			mIsLoading = true;
		}

	}

	/**
	 * param pageCount 总页数 param isStartZearo 首次请求从第0页开始
	 */
	public void update(int pageCount, boolean isStartZearo) {
		if (isStartZearo) {
			if (mCurrentPage == pageCount - 1) {
				if (mListView.getFooterViewsCount() == 1)
					mListView.removeFooterView(mFootView);
			} else {
				mFootView.setVisibility(View.VISIBLE);
			}
		} else {
			if (mCurrentPage >= pageCount) {
				if (mListView.getFooterViewsCount() == 1)
					mListView.removeFooterView(mFootView);
			} else {
				mFootView.setVisibility(View.VISIBLE);
			}
		}

		mCurrentPage++;
		mPageCount = pageCount;
		mIsLoading = false;

	}

	/**
	 * @Description:设置mIsLoading
	 * @Author 13050527
	 * @Date 2014-2-13
	 */
	public void setIsLoading(boolean isLoading) {
		mIsLoading = isLoading;
	}

	/**
	 * 
	 * @author Administrator
	 * 
	 */
	public interface LoadDataListener {
		void loadMoreData();
	}

	/**
	 * 
	 * @return
	 */
	public int getmPageSize() {
		return mPageSize;
	}

	/**
	 * 
	 * @param mPageSize
	 */
	public void setmPageSize(int mPageSize) {
		this.mPageSize = mPageSize;
	}

	/**
	 * 
	 * @return
	 */
	public int getmPageCount() {
		return mPageCount;
	}

	/**
	 * 
	 * @param mPageCount
	 */
	public void setmPageCount(int mPageCount) {
		this.mPageCount = mPageCount;
	}

	/**
	 * 
	 * @return
	 */
	public int getmCurrentPage() {
		return mCurrentPage;
	}

	/**
	 * 
	 * @param mCurrentPage
	 */
	public void setmCurrentPage(int mCurrentPage) {
		this.mCurrentPage = mCurrentPage;
	}

	/**
	 * 
	 * @param listener
	 */
	public void setLoadDataListener(LoadDataListener listener) {
		this.mListener = listener;
	}

	/**
	 * 初始化
	 */
	private void init() {
		mPageSize = 20;
		mPageCount = 1;
		mCurrentPage = 1;
		mIsLoading = false;
		mFootView = mContext.getLayoutInflater().inflate(R.layout.loadingview,
				null);
		mFootView.setVisibility(View.GONE);
		mListView.addFooterView(mFootView);
	}

	/**
	 * @Description:加入底部加载的
	 * @Author 13050527
	 * @Date 2014-2-28
	 */
	public void addFootView() {
		if (mFootView != null && mListView != null) {
			if (mListView.getFooterViewsCount() == 0)// 评价页面切换的时候会把mFootViewremove
			{
				mListView.addFooterView(mFootView);
			}
		}
	}

}
