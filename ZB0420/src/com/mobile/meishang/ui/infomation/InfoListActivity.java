package com.mobile.meishang.ui.infomation;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.InfoFilterLvAdapter;
import com.mobile.meishang.adapter.InfoListviewAdapter;
import com.mobile.meishang.config.Constants;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.GoodsListRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Category;
import com.mobile.meishang.model.bean.Goods;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.mobile.meishang.utils.view.pulltorefresh.XListView;
import com.umeng.analytics.MobclickAgent;

public class InfoListActivity extends MActivity implements
		XListView.IXListViewListener, ExceptionHandler, LoadEvent {
	// private Context mContext;
	private LoadingView mLoadingView;
	private RelativeLayout mNoDataRLayout;
	private TextView tvNoData;
	private XListView mListView;
	private InfoListviewAdapter mListviewAdapter;
	private List<Goods> mGoodsListing;
	private Bundle mBundle;
	private LinearLayout llayout_listview;
	private InfoFilterLvAdapter mInfoFilterLvAdapter;
	private ListView lv_a;
	private ListView lv_b;
	private List<Category> levelA;
	private List<Category> levelB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_list);
		mBundle = getIntent().getBundleExtra("bundle");
		// mContext = this;
		// findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("美商资讯");
		title.setVisibility(View.VISIBLE);
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
		mListviewAdapter = new InfoListviewAdapter(this);
		mListView.setAdapter(mListviewAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				Bundle bundle = new Bundle();
				// bundle.putString("id", mGoodsListing.get(--position)
				// .getGoodsid());
				 goActivity(InfoDetailActivity.class, bundle);
			}
		});
		llayout_listview = (LinearLayout) findViewById(R.id.llayout_listview);
		lv_a = (ListView) findViewById(R.id.lv_a);
		lv_b = (ListView) findViewById(R.id.lv_b);

//		getSupportLoaderManager().restartLoader(RequestDistribute.GOODS_LIST,
//				mBundle, new GoodsListRequest(this));
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

}
