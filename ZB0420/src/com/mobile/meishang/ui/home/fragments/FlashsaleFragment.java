package com.mobile.meishang.ui.home.fragments;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.mobile.meishang.MFragment;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.AdvertisingGalleryAdapter;
import com.mobile.meishang.adapter.LehuigouHomeExpandAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.AdvertisingGalleryRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.AdvertisingGallery;
import com.mobile.meishang.model.bean.AdvertisingGalleryList;
import com.mobile.meishang.model.bean.PointStrategyGroup;
import com.mobile.meishang.ui.ad.AdvertisingListActivity;
import com.mobile.meishang.utils.view.AdGallery;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.mobile.meishang.utils.view.pulltorefresh.MExpandableListView;
import com.mobile.meishang.utils.view.pulltorefresh.MExpandableListView.MOnRefreshListener;
import com.umeng.analytics.MobclickAgent;

public class FlashsaleFragment extends MFragment implements MOnRefreshListener,
		OnClickListener, ExceptionHandler, LoadEvent {
	// private LoadingView mLoadingView;
	private MExpandableListView mExpandableListView;
	private LehuigouHomeExpandAdapter mExpandAdapter;
	private List<PointStrategyGroup> mGroups;
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

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBundle = new Bundle();
		mBundle.putString("label", "limitBuy");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(
				R.layout.activity_refresh_expandable_listview, null);
		mExpandableListView = (MExpandableListView) view
				.findViewById(R.id.expandabel_listview);
		mExpandableListView.setPullRefreshEnable(true);
		mExpandableListView.setPullLoadEnable(false);
		mExpandableListView.setXListViewListener(this);
		mExpandableListView.setRefreshTime(getTime());
		View headView = LayoutInflater.from(mContext).inflate(
				R.layout.layout_ad_gallery, mExpandableListView, false);
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
		mAdvertisingAdapter = new AdvertisingGalleryAdapter(mContext);
		mAdGallery.setAdapter(mAdvertisingAdapter);
		mExpandAdapter = new LehuigouHomeExpandAdapter(getActivity());
		mExpandableListView.setGroupIndicator(null);
		mExpandableListView.setAdapter(mExpandAdapter);
		mExpandableListView.addHeaderView(headView);
		// mExpandableListView
		// .setOnGroupExpandListener(new OnGroupExpandListener() {
		//
		// @Override
		// public void onGroupExpand(int groupPosition) {
		// for (int i = 0; i < 2; i++) {
		// if (groupPosition != i) {
		// mExpandableListView.collapseGroup(i);
		// }
		//
		// }
		// }
		//
		// });
		mExpandableListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				showToast("groupPosition=" + groupPosition);
				return true;
			}
		});
		mExpandableListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				showToast("groupPosition=" + groupPosition + "childPosition="
						+ childPosition);
				return true;
			}
		});
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getLoaderManager().initLoader(
				RequestDistribute.ADVERTISING_GALLERY_FLASH_SALE, mBundle,
				new AdvertisingGalleryRequest(this));
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(mContext);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(mContext);
	}

	@Override
	public void handleException(int identity, Exception e) {

	}

	@Override
	public void retryAgain(View v) {

	}

	@Override
	public void updateUI(int identity, Object data) {
		// mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.ADVERTISING_GALLERY_FLASH_SALE:
			stopRefresh();
			AdvertisingGalleryList advertisingList = (AdvertisingGalleryList) data;
			mAdvertisings = advertisingList.getList();
			initEightPicture();
			for (int i = 0; i < 2; i++) {
				mExpandableListView.expandGroup(i);
			}
			break;
		case RequestDistribute.CATEGORY:
			break;

		default:
			break;
		}
	}

	@Override
	public void resetUI(int identity, Object data) {

	}

	@Override
	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				mExpandableListView.stopRefresh();
				mExpandableListView.stopLoadMore();
			}
		}, 5000);
	}

	@Override
	public void onLoadMore() {
		// onLoad();
	}

	private void stopRefresh() {

		mExpandableListView.stopRefresh();
		mExpandableListView.stopLoadMore();
		mExpandableListView.setRefreshTime(getTime());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.category_flayout:
			break;
		case R.id.category_a_flayout:
			break;
		default:
			break;
		}
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
				dotHolder[i] = new ImageView(mContext);
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
