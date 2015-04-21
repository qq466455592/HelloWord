package com.mobile.meishang.ui.lehuigou;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.FilterListAdapter;
import com.mobile.meishang.adapter.GoodsListviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.GoodsListRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.CategoryFilter;
import com.mobile.meishang.model.bean.Goods;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class GoodsSearchActivity extends MActivity implements ExceptionHandler,
		LoadEvent {
	// private Context mContext;
	private TextView tv_category_left;
	private TextView tv_category_right;
	// private LoadingView mLoadingView;
	// private RelativeLayout mNoDataRLayout;
	// private TextView tvNoData;
	private ListView listview;
	private LinearLayout llayout_listview;
	private ListView listview_left;
	private ListView listview_right;
	private GoodsListviewAdapter mListviewAdapter;
	private List<Goods> mGoodsListing;
	private Bundle mBundle;
	private FilterListAdapter filterLeftAdapter;
	private FilterListAdapter filterRightAdapter;
	private List<CategoryFilter> filterLeft;
	private List<CategoryFilter> filterRight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_list);
		mBundle = getIntent().getBundleExtra("bundle");
		// mContext = this;
		findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
		// findViewById(R.id.top_arrow_down).setVisibility(View.VISIBLE);
		// findViewById(R.id.top_map).setVisibility(View.VISIBLE);
		LinearLayout layout = (LinearLayout) findViewById(R.id.title_choice_llayout);
		layout.setVisibility(View.VISIBLE);
		tv_category_left = (TextView) findViewById(R.id.category_a_tv);
		if (mBundle != null && mBundle.containsKey("name")) {
			tv_category_left.setText(mBundle.getString("name"));
		}
		tv_category_right = (TextView) findViewById(R.id.tv_category_right);
		// mLoadingView = (LoadingView) findViewById(R.id.loading);
		// mLoadingView.setLoadEvent(this);
		// mNoDataRLayout = (RelativeLayout) findViewById(R.id.no_data);
		// tvNoData = (TextView) findViewById(R.id.face_tv);
		// tvNoData.setText("很抱歉，没有发现商品");
		llayout_listview = (LinearLayout) findViewById(R.id.llayout_listview);
		listview = (ListView) findViewById(R.id.listview);
		listview_left = (ListView) findViewById(R.id.listview_left);
		listview_left.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				llayout_listview.setVisibility(View.GONE);
				// Bundle bundle = new Bundle();
				// bundle.putString("id",
				// mGoodsListing.get(--position).getId());
				// goActivity(GoodsDetailActivity.class, bundle);
			}
		});
		listview_right = (ListView) findViewById(R.id.listview_right);
		listview_right.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				llayout_listview.setVisibility(View.GONE);
				// Bundle bundle = new Bundle();
				// bundle.putString("id",
				// mGoodsListing.get(--position).getId());
				// goActivity(GoodsDetailActivity.class, bundle);
			}
		});
		mListviewAdapter = new GoodsListviewAdapter(this);
		listview.setAdapter(mListviewAdapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// Bundle bundle = new Bundle();
				// bundle.putString("id",
				// mGoodsListing.get(--position).getId());
				// goActivity(GoodsDetailActivity.class, bundle);
				goActivity(GoodsDetailActivity.class, null);
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
	public void updateUI(int identity, Object data) {
		// super.updateUI(identity, data);
		// mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.GOODS_LIST:
			// mGoodsListing = (List<GoodsItem>) data;
			// if (mGoodsListing.size() > 0) {
			// mListviewAdapter.clear();
			// mListviewAdapter.addAll(mGoodsListing);
			// mListviewAdapter.notifyDataSetChanged();
			// } else {
			// mNoDataRLayout.setVisibility(View.VISIBLE);
			// }
			break;

		default:
			break;
		}

	}

	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.flayout_category_left:
			llayout_listview.setVisibility(View.VISIBLE);
			if (filterLeftAdapter == null) {
				filterLeftAdapter = new FilterListAdapter(this);
				listview_left.setAdapter(filterLeftAdapter);
				filterLeftAdapter.addAll(getFiterDataLeft());
				filterLeftAdapter.notifyDataSetChanged();
			}
			break;
		case R.id.flayout_category_right:
			llayout_listview.setVisibility(View.VISIBLE);
			if (filterRightAdapter == null) {
				filterRightAdapter = new FilterListAdapter(this);
				listview_right.setAdapter(filterRightAdapter);
				filterRightAdapter.addAll(getFiterDataRight());
				filterRightAdapter.notifyDataSetChanged();
			}
			break;

		default:
			break;
		}
		;
	}

	@Override
	public void handleException(final int identity, final Exception e) {
		super.handleException(identity, e);
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (identity == RequestDistribute.GOODS_LIST) {
					// mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void retryAgain(View v) {
		// mNoDataRLayout.setVisibility(View.GONE);
		getSupportLoaderManager().restartLoader(RequestDistribute.GOODS_LIST,
				mBundle, new GoodsListRequest(this));
	}

	private List<CategoryFilter> getFiterDataLeft() {
		if (filterLeft == null) {
			filterLeft = new ArrayList<CategoryFilter>();
			filterLeft.add(new CategoryFilter("0-999", ""));
			filterLeft.add(new CategoryFilter("1000-1999", ""));
			filterLeft.add(new CategoryFilter("2000-2999", ""));
			filterLeft.add(new CategoryFilter("3000-3999", ""));
			filterLeft.add(new CategoryFilter("4000-4999", ""));
		}
		return filterLeft;

	}

	private List<CategoryFilter> getFiterDataRight() {
		if (filterRight == null) {
			filterRight = new ArrayList<CategoryFilter>();
			filterRight.add(new CategoryFilter("类型1", ""));
			filterRight.add(new CategoryFilter("类型2", ""));
			filterRight.add(new CategoryFilter("类型3", ""));
			filterRight.add(new CategoryFilter("类型4", ""));
			filterRight.add(new CategoryFilter("类型5", ""));
			filterRight.add(new CategoryFilter("类型6", ""));

		}
		return filterRight;
	}
}
