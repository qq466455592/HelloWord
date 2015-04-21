package com.mobile.meishang.ui.ad;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.AdvertisingListviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.AdvertisingListRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.AdvertisingList;
import com.mobile.meishang.model.bean.Goods;
import com.mobile.meishang.utils.FunctionUtil;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.mobile.meishang.utils.view.pulltorefresh.XListView;
import com.umeng.analytics.MobclickAgent;

public class AdvertisingListActivity extends MActivity implements
		XListView.IXListViewListener, ExceptionHandler, LoadEvent {
	private LoadingView mLoadingView;
	private LinearLayout h_layout_qbuy_time;
	private TextView h_tv_qbuy_status;
	private TextView h_tv_qbuy_time;
	private TextView h_tv_qbuy_time_hour;
	private TextView h_tv_qbuy_time_minite;
	private TextView h_tv_qbuy_time_seconds;
	private XListView mListView;
	private AdvertisingListviewAdapter mAdapter;
	private AdvertisingList mAdvertisings;
	private List<Goods> mList;
	private Bundle mBundle;
	private Handler mHandler = new Handler();
	private long timeToStart;
	private long timeToEnd;
	private Runnable runnable = new Runnable() {

		@Override
		public void run() {
			timeToStart -= 1000;
			timeToEnd -= 1000;
			if (timeToStart >= 0) {// 即将开始
				setTime(changeTimer(timeToStart));
				swtichQBuyStatus(0);
				mHandler.postDelayed(this, 1000);
			} else if (timeToEnd > 0) {
				setTime(changeTimer(timeToEnd));
				swtichQBuyStatus(1);
				mHandler.postDelayed(this, 1000);
			} else {
				swtichQBuyStatus(2);
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBundle = getIntent().getBundleExtra("bundle");
		setContentView(R.layout.activity_advertising_list);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText(mBundle.getString("name", ""));
		title.setVisibility(View.VISIBLE);
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		mListView = (XListView) findViewById(R.id.mlistview);
		mListView.setPullRefreshEnable(false);
		mListView.setPullLoadEnable(false);
		mListView.setXListViewListener(this);
		mListView.setRefreshTime(getTime());
		View headView = LayoutInflater.from(this).inflate(
				R.layout.item_advertising_lh, mListView, false);
		h_tv_qbuy_status = (TextView) headView
				.findViewById(R.id.h_tv_qbuy_status);
		h_layout_qbuy_time = (LinearLayout) headView
				.findViewById(R.id.h_layout_qbuy_time);
		h_tv_qbuy_time = (TextView) headView.findViewById(R.id.h_tv_qbuy_time);
		h_tv_qbuy_time_hour = (TextView) headView
				.findViewById(R.id.h_tv_qbuy_time_hour);
		h_tv_qbuy_time_minite = (TextView) headView
				.findViewById(R.id.h_tv_qbuy_time_minite);
		h_tv_qbuy_time_seconds = (TextView) headView
				.findViewById(R.id.h_tv_qbuy_time_seconds);

		mListView.addHeaderView(headView);
		mAdapter = new AdvertisingListviewAdapter(this);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				position -= 2;
				mBundle.clear();
				mBundle.putString("actid", mAdapter.getItem(position)
						.getActid());
				mBundle.putString("goodsid", mAdapter.getItem(position)
						.getGoodsid());
				mBundle.putString("name", mAdapter.getItem(position).getName());
				mBundle.putString("desp", mAdapter.getItem(position).getDesp());
				mBundle.putString("price", mAdapter.getItem(position)
						.getPrice());
				mBundle.putString("price1", mAdapter.getItem(position)
						.getPrice1());
				mBundle.putString("url", mAdapter.getItem(position)
						.getDetailUrl());
				goActivity(AdvertisingDetailActivity.class, mBundle);
			}
		});
		net();
	}

	private void net() {
		getSupportLoaderManager().restartLoader(
				RequestDistribute.ADVERTISING_LIST, mBundle,
				new AdvertisingListRequest(this));
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
		// for (int i = 30; i < 30; i++) {
		// mList.add(new ItemGoods("超级优惠日来袭" + i));
		// }
		// mAdapter.addAll(mList);
		// mAdapter.notifyDataSetChanged();
		// mListView.setPullLoadEnable(false);
		onLoad();
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime(getTime());
	}

	@Override
	public void updateUI(int identity, Object data) {
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.ADVERTISING_LIST:
			mAdvertisings = (AdvertisingList) data;
			timeToStart = mAdvertisings.getTimeToStart();
			timeToEnd = mAdvertisings.getTimeToEnd();
			runOnUiThread(runnable);
			mList = mAdvertisings.getmList();
			mAdapter.clear();
			mAdapter.addAll(mList);
			mAdapter.notifyDataSetChanged();
			break;

		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void handleException(final int identity, final Exception e) {
		super.handleException(identity, e);
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (identity == RequestDistribute.ADVERTISING_LIST) {
					mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void retryAgain(View v) {
		net();
	}

	private void swtichQBuyStatus(int status) {
		switch (status) {
		case 0:
			h_tv_qbuy_status.setText("即将开始");
			h_tv_qbuy_status.setTextColor(getResources().getColor(
					R.color.orange1));
			h_tv_qbuy_time.setText("距开抢时间还有");
			h_layout_qbuy_time.setVisibility(View.VISIBLE);
			break;
		case 1:
			h_tv_qbuy_status.setText("火爆开抢中");
			h_tv_qbuy_status.setTextColor(getResources().getColor(
					R.color.orange1));
			h_tv_qbuy_time.setText("距结束时间还有");
			h_layout_qbuy_time.setVisibility(View.VISIBLE);
			break;
		case 2:
			h_tv_qbuy_status.setText("抢购已结束");
			h_tv_qbuy_status.setTextColor(getResources().getColor(
					R.color.black2));
			// h_tv_qbuy_time.setText("距下次开抢时间还有");
			// h_tv_qbuy_time.setVisibility(View.VISIBLE);
			h_layout_qbuy_time.setVisibility(View.GONE);
			break;

		default:
			break;
		}
	}

	private void setTime(int[] time) {
		String[] timeStr = FunctionUtil.getTimer(time);
		h_tv_qbuy_time_hour.setText(timeStr[2]);
		h_tv_qbuy_time_minite.setText(timeStr[1]);
		h_tv_qbuy_time_seconds.setText(timeStr[0]);
	}

	/**
	 * 
	 * @Description:对毫秒时间进行处理
	 * @Author 12071520
	 * @Date 2013-5-7
	 */
	private int[] changeTimer(long timeMillisecond) {
		int ts = (int) (timeMillisecond / 1000);// 总秒数
		int s = ts % 60;// 秒
		int tm = ts / 60;// 总分钟数
		int m = tm % 60;// 分
		int th = tm / 60;// 总小时数
		int h = th % 24;// 时
		int day = th / 24;// 总天数
		int mTime[] = new int[4];
		if (s > 0) {
			mTime[0] = s;
		} else {
			mTime[0] = 0;
		}
		if (m > 0) {
			mTime[1] = m;
		} else {
			mTime[1] = 0;
		}
		if (h > 0) {
			mTime[2] = h;
		} else {
			mTime[2] = 0;
		}
		if (day > 0) {
			mTime[3] = day;
		} else {
			mTime[3] = 0;
		}

		return mTime;
	}

}
