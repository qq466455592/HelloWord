package com.mobile.meishang.ui.shopping;

import java.util.List;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.ShoppingCarListAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.GoodsDetailRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.FavoritesList;
import com.mobile.meishang.model.bean.Goods;
import com.mobile.meishang.ui.login.LoginActivity;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class ShoppingCarListActivity extends MActivity implements
		ExceptionHandler, LoadEvent {
	private TextView tv_top_right;
	private RelativeLayout mNoDataRLayout;
	private TextView tvNoData;
	private LoadingView mLoadingView;
	private LinearLayout mLinearLayout;
	private ListView mListView;
	private FrameLayout mDeleteBtn;
	private ShoppingCarListAdapter mListAdapter;
	private List<Goods> list;
	private List<Integer> positions;
	private LinearLayout llayout_clearing;
	private FrameLayout flayout_delete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping_car);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("购物车");
		tv_top_right = (TextView) findViewById(R.id.tv_top_right);
		tv_top_right.setText("编辑");
		tv_top_right.setVisibility(View.VISIBLE);
		llayout_clearing = (LinearLayout) findViewById(R.id.llayout_clearing);
		flayout_delete = (FrameLayout) findViewById(R.id.flayout_delete);

		mNoDataRLayout = (RelativeLayout) findViewById(R.id.no_data);
		tvNoData = (TextView) findViewById(R.id.face_tv);
		tvNoData.setText("你还没有收藏的商品");
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		mLoadingView.setVisibility(View.GONE);
		mListView = (ListView) findViewById(R.id.listview);
		mListAdapter = new ShoppingCarListAdapter(this);
		mListView.setAdapter(mListAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// if (!mListAdapter.getIsEdite()) {
				Bundle bundle = new Bundle();
				bundle.putString("id", list.get(position).getGoodsid());
				// goActivity(GoodsDetailActivity.class, bundle);
				// }

			}
		});
		// getSupportLoaderManager().restartLoader(
		// RequestDistribute.FAVORITES_LIST, null,
		// new FavoritesListRequest(this));

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

	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.tv_top_right:
			String name = tv_top_right.getText().toString();
			if ("编辑".equals(name)) {
				tv_top_right.setText("关闭");
				llayout_clearing.setVisibility(View.GONE);
				flayout_delete.setVisibility(View.VISIBLE);
			} else {
				tv_top_right.setText("编辑");
				llayout_clearing.setVisibility(View.VISIBLE);
				flayout_delete.setVisibility(View.GONE);
			}
			// if (list != null && list.size() <= 0) {
			// showToast("没有可操作的收藏");
			// } else {
			//
			// if (mListAdapter.getIsEdite()) {
			// mDeleteBtn.setVisibility(View.GONE);
			// mListAdapter.setIsEdite(false);
			// mListAdapter.getCheckPositions().clear();
			// mListAdapter.notifyDataSetChanged();
			//
			// } else {
			// mDeleteBtn.setVisibility(View.VISIBLE);
			// mListAdapter.setIsEdite(true);
			// mListAdapter.notifyDataSetChanged();
			// }
			// }
			break;
		case R.id.flayout_delete:
			// positions = mListAdapter.getCheckPositions();
			// int size = positions.size();
			// if (positions != null && size > 0) {
			// StringBuffer ids = new StringBuffer();
			// for (int i = 0; i < size; i++) {
			// if (i == 0) {
			// ids.append(list.get(positions.get(i)).getId());
			// } else {
			// ids.append(",");
			// ids.append(list.get(positions.get(i)).getId());
			// }
			//
			// }
			// Bundle bundle = new Bundle();
			// bundle.putString("ids", ids.toString());
			// getSupportLoaderManager().restartLoader(
			// RequestDistribute.FAVORITES_LIST_DELETE, bundle,
			// new FavoritesDeleteRequest(this));
			// } else {
			// showToast("请选择要删除的项");
			// }
			// 方式2
			// Set set = checkBoxTrue.entrySet();
			// Iterator it = checkBoxTrue.entrySet().iterator();
			// while (it.hasNext()) {
			// Entry entry = (Entry) it.next();
			// // entry.getKey() 返回与此项对应的键
			// // entry.getValue() 返回与此项对应的值
			// System.out.println(entry.getValue());
			// }
			break;
		default:
			break;
		}
	}

	@Override
	public void updateUI(int identity, Object data) {
		mNoDataRLayout.setVisibility(View.GONE);
		if (!mLinearLayout.isShown()) {
			mLinearLayout.setVisibility(View.VISIBLE);
		}
		switch (identity) {
		case RequestDistribute.FAVORITES_LIST:
			mLoadingView.setVisibility(View.GONE);
			FavoritesList favoritesList = (FavoritesList) data;
			list = favoritesList.getmList();
			String code = favoritesList.getCode();
			if (TextUtils.isEmpty(code)) {
				if (list.size() > 0) {
					mListAdapter.addAll(list);
					mListAdapter.notifyDataSetChanged();
				} else {
					// showToast("你还没有收藏的商品");
					mNoDataRLayout.setVisibility(View.VISIBLE);
				}
			} else {
				if ("1".equals(code)) {// 未登录
					goActivity(LoginActivity.class, null);
				} else {
					showToast(favoritesList.getCodeMessage());
				}
			}

			break;
		case RequestDistribute.FAVORITES_LIST_DELETE:
			// RequestResponseInfo requestResponseInfo = (RequestResponseInfo)
			// data;
			// if ("".equals(requestResponseInfo.getErrorCode())) {
			// if (positions != null) {
			// int size = positions.size();
			// int positionArray[] = new int[size];
			// for (int i = 0; i < size; i++) {
			// positionArray[i] = positions.get(i);
			// }
			// Arrays.sort(positionArray);
			// int length = positionArray.length;
			// for (int i = length - 1; i >= 0; i--) {
			// int temp = positionArray[i];
			// mListAdapter.remove(temp);
			// list.remove(temp);
			// }
			// mListAdapter.getCheckPositions().clear();
			// mListAdapter.setIsEdite(false);
			// mDeleteBtn.setVisibility(View.GONE);
			// mListAdapter.notifyDataSetChanged();
			// if (list.size() == 0) {
			// mNoDataRLayout.setVisibility(View.VISIBLE);
			// }
			// }
			// // mLoadingView.setVisibility(View.VISIBLE);
			// // mDeleteBtn.setVisibility(View.GONE);
			// // mListAdapter.clear();
			// // showToast(requestResponseInfo.getState());
			// // getSupportLoaderManager().restartLoader(
			// // RequestDistribute.FAVORITES_LIST, null,
			// // new FavoritesListRequest(this));
			// } else {
			// showToast(requestResponseInfo.getErrorMessage());
			// }

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
				if (identity == RequestDistribute.FAVORITES_LIST) {
					mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void retryAgain(View v) {
		getSupportLoaderManager().restartLoader(
				RequestDistribute.FAVORITES_LIST, null,
				new GoodsDetailRequest(this));

	}
}
