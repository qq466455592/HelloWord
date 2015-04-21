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
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.AdvertisingGalleryAdapter;
import com.mobile.meishang.adapter.DiscoverListviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.AdvertisingGalleryRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.AdvertisingGallery;
import com.mobile.meishang.model.bean.AdvertisingGalleryList;
import com.mobile.meishang.model.bean.GoodsItem;
import com.mobile.meishang.ui.ad.AdvertisingListActivity;
import com.mobile.meishang.utils.view.AdGallery;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class DiscoverDetailActivity extends MActivity implements
		ExceptionHandler, LoadEvent {
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
	private ListView mListView;
	private DiscoverListviewAdapter mListviewAdapter;
	private List<GoodsItem> mGoodsListing;
	private Bundle mBundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discover_detail);
//		mBundle = getIntent().getBundleExtra("bundle");
		// mContext = this;
//		findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
//		TextView title = (TextView) findViewById(R.id.top_name);
//		title.setText("发现详情页");
//		title.setVisibility(View.VISIBLE);
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		mLoadingView.setVisibility(View.GONE);
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
		mAdvertisingAdapter = new AdvertisingGalleryAdapter(this);
		mAdGallery.setAdapter(mAdvertisingAdapter);
		mListView = (ListView) findViewById(R.id.listview);
		mListviewAdapter = new DiscoverListviewAdapter(this);
		mListView.addHeaderView(headView);
		mListView.setAdapter(mListviewAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// Bundle bundle = new Bundle();
				// bundle.putString("id",
				// mGoodsListing.get(--position).getId());
				// goActivity(GoodsDetailActivity.class, bundle);
			}
		});
		// getSupportLoaderManager().restartLoader(RequestDistribute.GOODS_LIST,
		// mBundle, new GoodsListRequest(this));
		mBundle = new Bundle();
		mBundle.putString("label", "limitBuy");
		getSupportLoaderManager().initLoader(
				RequestDistribute.ADVERTISING_GALLERY_FLASH_SALE, mBundle,
				new AdvertisingGalleryRequest(this));
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
	public void updateUI(int identity, Object data) {
		// super.updateUI(identity, data);
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.GOODS_LIST:
			mGoodsListing = (List<GoodsItem>) data;
			break;
		case RequestDistribute.ADVERTISING_GALLERY_FLASH_SALE:
			AdvertisingGalleryList advertisingList = (AdvertisingGalleryList) data;
			mAdvertisings = advertisingList.getList();
			initEightPicture();
			// for (int i = 0; i < 2; i++) {
			// mExpandableListView.expandGroup(i);
			// }
			break;
		default:
			break;
		}

	}

	public void onclick(View v) {
		super.onclick(v);
		Bundle bundle = new Bundle();
		switch (v.getId()) {
		case R.id.category_a_flayout:
			break;
		// case R.id.category_b_flayout:
		// goActivity(CategoryActivity.class, null);
		// break;
		case R.id.category_c_flayout:
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
		// getSupportLoaderManager().restartLoader(RequestDistribute.GOODS_LIST,
		// mBundle, new GoodsListRequest(this));
	}
	
	private void initEightPicture() {
		if (mAdvertisings != null && mAdvertisings.size() > 0) {
			galleryImgNum = mAdvertisings.size();
			mAdvertisingAdapter.clear();
			mAdvertisingAdapter.addAll(mAdvertisings);
			mAdvertisingAdapter.setImagelength(galleryImgNum);
			mAdvertisingAdapter.notifyDataSetChanged();
			mAdGallery.setSelection(100 * galleryImgNum);
			selectedPosition = 100 * galleryImgNum;
			mAdDotLayout.removeAllViews();
			dotHolder = new ImageView[galleryImgNum];
			for (int i = 0; i < galleryImgNum; i++) {
				dotHolder[i] = new ImageView(this);
				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				layoutParams.setMargins(5, 0, 0, 0);
				if (i == 0) {
					dotHolder[i]
							.setBackgroundResource(R.drawable.banner_round_select);
				} else {
					dotHolder[i]
							.setBackgroundResource(R.drawable.banner_round_normal);
				}
				mAdDotLayout.addView(dotHolder[i], layoutParams);
			}

			if (mRefreshAdvRun == null) {
				mRefreshAdvRun = new RefreshAdvRun();
			} else {
				mHandler.removeCallbacks(mRefreshAdvRun);
			}
			mHandler.postDelayed(mRefreshAdvRun, refreshTime);

		}
	}
	private class RefreshAdvRun implements Runnable {

		@Override
		public void run() {
			mHandler.sendEmptyMessage(ADVREFRESH);
		}
	}
}
