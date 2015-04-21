package com.mobile.meishang.adapter;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.R;
import com.mobile.meishang.imagecache.ImageFetcher;
import com.mobile.meishang.imagecache.ImageWorker;

/**
 * 
 * @Title:
 * @Description:
 * @Author:Administrator
 * @Since:2013-5-2
 * @Version:
 */
public abstract class BaseCacheListAdapter<T> extends BaseListAdapter<T>
		implements OnScrollListener {
	protected LayoutInflater mInflater;
	protected ImageWorker mImageWorker;
	protected Context mContext;
	protected HashMap<Integer, WeakReference<ImageView>> imageViewReferenceMap = new HashMap<Integer, WeakReference<ImageView>>();
	private int mLastItem;
	private int mPageSize;
	private int mPageCount;
	private int mCurrentPage;
	private View mFootView;
	private boolean mIsLoading;
	private AbsListView mAdapterView;
	private LoadDataListener mListener;
	private boolean isScrooling = false;
	private boolean isRequestRefresh = true;
	private boolean mIsStartZearo = false;

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	public HashMap<Integer, WeakReference<ImageView>> getImageViewReferenceMap() {
		return imageViewReferenceMap;
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	public void setImageViewReferenceMap(
			HashMap<Integer, WeakReference<ImageView>> imageViewReferenceMap) {
		this.imageViewReferenceMap = imageViewReferenceMap;
	}

	/**
	 * 
	 * @param context
	 */
	public BaseCacheListAdapter(Context context) {
		super();
		mContext = context;
		mInflater = LayoutInflater.from(context);

		// The ImageWorker takes care of loading images into our ImageView
		// children asynchronously
		mImageWorker = new ImageFetcher(context,
				MApplication.getLongest());
		mImageWorker.setImageCache(MApplication.getImageLruCache());
	}

	/**
	 * 
	 * @param context
	 * @param view
	 */
	public BaseCacheListAdapter(Context context, AbsListView view) {
		super();
		mContext = context;
		mInflater = LayoutInflater.from(context);

		// The ImageWorker takes care of loading images into our ImageView
		// children asynchronously
		mImageWorker = new ImageFetcher(context,
				MApplication.getLongest());
		mImageWorker.setImageCache(MApplication.getImageLruCache());
		this.mAdapterView = view;
		initAdapterView(context);
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	public void setCacheImage(final ImageView imageView, String imageUrl,
			final int resId) {
		mImageWorker.setLoadingImage(resId);
		mImageWorker.loadImage(imageUrl, imageView);
	}

	/**
	 * should be called when Activity is destoried
	 * 
	 * @Description:
	 * @Author 11112760
	 * @Date 2012-8-17
	 */
	public void destory() {
		super.clear();
		imageViewReferenceMap.clear();
		imageViewReferenceMap = null;
		if (mFootView != null) {
			mFootView = null;
		}
		if (mImageWorker != null) {
			mImageWorker.getImageCache().clearCaches();
			mImageWorker.setImageCache(null);
			mImageWorker = null;
		}
		mInflater = null;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		mLastItem = firstVisibleItem + visibleItemCount;
		if (!isScrooling && isRequestRefresh) {
			refreshImageView(view);
			isRequestRefresh = false;
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		switch (scrollState) {
		case OnScrollListener.SCROLL_STATE_IDLE:
			refreshImageView(view);
			isScrooling = false;
			break;
		case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
			isScrooling = true;
			break;
		case OnScrollListener.SCROLL_STATE_FLING:
			isScrooling = true;
			break;
		}
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
				&& mLastItem == view.getCount()) {
			isLoadData();
		}

	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	public abstract void refreshImageView(AbsListView view);

	/**
	 * 
	 * @Description:param pageCount ��ҳ�� param isStartZearo �״�����ӵ�0ҳ��ʼ
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	public void update(int pageCount, boolean isStartZearo) {
		isRequestRefresh = true;
		mIsStartZearo = isStartZearo;
		if (mAdapterView instanceof GridView) {
			mFootView.setVisibility(View.GONE);
		}
		if (isStartZearo && mAdapterView instanceof ListView) {
			if (mCurrentPage == pageCount - 1) {
				if (((ListView) mAdapterView).getFooterViewsCount() == 1)
					((ListView) mAdapterView).removeFooterView(mFootView);

			} else {
				mFootView.setVisibility(View.VISIBLE);
			}
		} else if (mAdapterView instanceof ListView) {
			if (mCurrentPage == pageCount) {
				if (((ListView) mAdapterView).getFooterViewsCount() == 1)
					((ListView) mAdapterView).removeFooterView(mFootView);
			} else {
				mFootView.setVisibility(View.VISIBLE);
			}
		}
		mCurrentPage++;
		mPageCount = pageCount;
		mIsLoading = false;
	}

	public interface LoadDataListener {
		void loadMoreData();
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	public int getmPageSize() {
		return mPageSize;
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	public void setmPageSize(int mPageSize) {
		this.mPageSize = mPageSize;
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	public int getmPageCount() {
		return mPageCount;
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	public void setmPageCount(int mPageCount) {
		this.mPageCount = mPageCount;
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	public int getmCurrentPage() {
		return mCurrentPage;
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	public void setmCurrentPage(int mCurrentPage) {
		this.mCurrentPage = mCurrentPage;
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	public void setLoadDataListener(LoadDataListener listener) {
		this.mListener = listener;
	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	public void retryNextView() {
		mFootView.findViewById(R.id.loading_ind).setVisibility(View.GONE);
		mFootView.findViewById(R.id.retry).setVisibility(View.VISIBLE);
		((Button) mFootView.findViewById(R.id.retry))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View paramView) {
						mFootView.setVisibility(View.VISIBLE);
						mFootView.findViewById(R.id.loading_ind).setVisibility(
								View.VISIBLE);
						mFootView.findViewById(R.id.retry).setVisibility(
								View.GONE);
						mListener.loadMoreData();
					}
				});

	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	private void initAdapterView(Context context) {
		mPageSize = 20;
		mPageCount = 1;
		mCurrentPage = 1;
		mIsLoading = false;
		mFootView = mInflater.inflate(R.layout.loadingview, null);
		mFootView.setVisibility(View.GONE);
		if (mAdapterView instanceof ListView)
			((ListView) mAdapterView).addFooterView(mFootView);
		else if (mAdapterView instanceof GridView) {
			RelativeLayout layout = new RelativeLayout(context);
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			layout.addView(mFootView, params);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT);
			layout.setLayoutParams(layoutParams);
			((Activity) context).addContentView(layout, layoutParams);
		}

	}

	/**
	 * 
	 * @Description:
	 * @Author Administrator
	 * @Date 2013-5-2
	 */
	private void isLoadData() {

		if (mIsLoading) {
			return;
		}
		if (!mIsStartZearo) {
			if (mCurrentPage <= mPageCount && !mIsLoading) {
				if (mAdapterView instanceof GridView) {
					mFootView.setVisibility(View.VISIBLE);
				}
				mListener.loadMoreData();
				mIsLoading = true;
			}
		} else {
			if (mCurrentPage < mPageCount && !mIsLoading) {
				if (mAdapterView instanceof GridView) {
					mFootView.setVisibility(View.VISIBLE);
				}
				mListener.loadMoreData();
				mIsLoading = true;
			}
		}
	}

}
