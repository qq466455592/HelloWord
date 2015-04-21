package com.mobile.meishang.ui.home;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.AdvertisingGalleryAdapter;
import com.mobile.meishang.adapter.DiscoverListviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.GoodsListRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.AdvertisingGallery;
import com.mobile.meishang.model.bean.Goods;
import com.mobile.meishang.ui.ad.AdvertisingListActivity;
import com.mobile.meishang.utils.view.AdGallery;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.mobile.meishang.utils.view.pulltorefresh.XListView;
import com.umeng.analytics.MobclickAgent;

public class InsideActivity extends MActivity implements
		XListView.IXListViewListener, ExceptionHandler, LoadEvent {
	private RefreshAdvRun mRefreshAdvRun;
	private AdGallery mAdGallery;
	private LinearLayout mAdDotLayout;
	private ImageView[] dotHolder;
	private AdvertisingGalleryAdapter mAdvertisingAdapter;
	private List<AdvertisingGallery> mAdvertisings;
	private final int ADVREFRESH = 1;
	private int selectedPosition = 0;
	private int realPosition = 0;
	private int galleryImgNum;
	private int refreshTime = 2000;
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case ADVREFRESH:
				mAdGallery.setSelection(selectedPosition++);
				mHandler.postDelayed(mRefreshAdvRun, refreshTime);
				break;
			default:
				break;
			}
		}
	};
	// private Context mContext;
	private LoadingView mLoadingView;
	private RelativeLayout mNoDataRLayout;
	private TextView tvNoData;
	private XListView mListView;
	private DiscoverListviewAdapter mListviewAdapter;
	private List<Goods> mGoodsListing;

	private Bundle mBundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inside);
		View headView = LayoutInflater.from(this).inflate(
				R.layout.layout_discover_detail_hview, null);
		if (null != headView) {
			mAdGallery = (AdGallery) headView.findViewById(R.id.ad_gallery);
			mAdGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					realPosition = position % galleryImgNum;
					selectedPosition = position;
					for (int i = 0; i < galleryImgNum; i++) {
						if (i == realPosition) {
							dotHolder[i]
									.setBackgroundResource(R.drawable.banner_round_select);
						} else {
							dotHolder[i]
									.setBackgroundResource(R.drawable.banner_round_normal);
						}
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {

				}
			});

			mAdGallery.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> paramAdapterView,
						View paramView, int paramInt, long paramLong) {
					AdvertisingGallery advertising = mAdvertisings
							.get(realPosition);
					Bundle bundle = new Bundle();
					bundle.putString("name", advertising.getName());
					bundle.putString("actid", advertising.getActid());
					goActivity(AdvertisingListActivity.class, bundle);
					// goActivity(AdvertisingExpandbleActivity.class, bundle);
				}
			});
			mAdGallery.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						mHandler.removeCallbacks(mRefreshAdvRun);
						break;
					case MotionEvent.ACTION_UP:
						mHandler.postDelayed(mRefreshAdvRun, refreshTime);
						break;
					}
					return false;
				}
			});

			mAdDotLayout = (LinearLayout) headView
					.findViewById(R.id.ad_dot_llayout);

		}
		mBundle = getIntent().getBundleExtra("bundle");
		// mContext = this;
		// findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("美商云讯");
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		mLoadingView.setVisibility(View.GONE);
		mNoDataRLayout = (RelativeLayout) findViewById(R.id.no_data);
		tvNoData = (TextView) findViewById(R.id.face_tv);
		tvNoData.setText("很抱歉，没有发现商品");
		mListView = (XListView) findViewById(R.id.mlistview);
		mListView.setPullRefreshEnable(true);
		mListView.setPullLoadEnable(false);
		mListView.setXListViewListener(this);
		mListView.setRefreshTime(getTime());
		mListView.addHeaderView(headView);
		mListviewAdapter = new DiscoverListviewAdapter(this);
		mListView.setAdapter(mListviewAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				Bundle bundle = new Bundle();
				bundle.putString("id", mGoodsListing.get(--position)
						.getGoodsid());
				// goActivity(GoodsDetailActivity.class, bundle);
			}
		});
		// getSupportLoaderManager().restartLoader(RequestDistribute.GOODS_LIST,
		// mBundle, new GoodsListRequest(this));
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	public void onRefresh() {
		onLoad();
	}

	@Override
	public void onLoadMore() {
		// onLoad();
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime(getTime());
	}

	@Override
	public void updateUI(int identity, Object data) {
		// super.updateUI(identity, data);
		mNoDataRLayout.setVisibility(View.GONE);
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.GOODS_LIST:
			mGoodsListing = (List<Goods>) data;
			if (mGoodsListing.size() > 0) {

				mListviewAdapter.clear();
				mListviewAdapter.addAll(mGoodsListing);
				mListviewAdapter.notifyDataSetChanged();
			} else {
				mNoDataRLayout.setVisibility(View.VISIBLE);
			}
			break;

		default:
			break;
		}

	}

	@Override
	public void handleException(final int identity, final Exception e) {
		super.handleException(identity, e);
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (identity == RequestDistribute.GOODS_LIST) {
					mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void retryAgain(View v) {
		getSupportLoaderManager().restartLoader(RequestDistribute.GOODS_LIST,
				null, new GoodsListRequest(this));

	}

	private class RefreshAdvRun implements Runnable {

		@Override
		public void run() {
			mHandler.sendEmptyMessage(ADVREFRESH);
		}
	}
}
