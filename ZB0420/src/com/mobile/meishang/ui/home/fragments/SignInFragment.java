package com.mobile.meishang.ui.home.fragments;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mobile.meishang.MFragment;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.SignInListviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.NearbyListRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Goods;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class SignInFragment extends MFragment implements OnClickListener,
		ExceptionHandler, LoadEvent {
	private LoadingView mLoadingView;
	private ListView listview;
	private SignInListviewAdapter adapter;
	private List<Goods> mGoodsListing;
	private Bundle mBundle;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_sign_in, null);
		view.findViewById(R.id.top_layout_back).setVisibility(View.GONE);
		TextView title = (TextView) view.findViewById(R.id.top_name);
		title.setText("每日签到");
		mLoadingView = (LoadingView) view.findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		mLoadingView.setVisibility(View.GONE);
		listview = (ListView) view.findViewById(R.id.listview);
		View headView = LayoutInflater.from(mContext).inflate(
				R.layout.layout_sign_in_head, null);
		listview.addHeaderView(headView);
		adapter = new SignInListviewAdapter(mContext);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				Bundle bundle = new Bundle();
				// bundle.putString("id",
				// mGoodsListing.get(--position).getId());
				// goActivity(ComboDetailActivity.class, bundle);
			}
		});
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mBundle = new Bundle();
		mBundle.putString("id", "");
		mBundle.putString("range", "");
		getActivity().getSupportLoaderManager().restartLoader(
				RequestDistribute.GOODS_LIST, mBundle,
				new NearbyListRequest(this));
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
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.GOODS_LIST:
			adapter.clear();
			mGoodsListing = (List<Goods>) data;
			if (mGoodsListing.size() > 0) {
				adapter.addAll(mGoodsListing);
				adapter.notifyDataSetChanged();
			} else {
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
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.category_flayout:
			// mCategoryFLayout.setVisibility(View.GONE);
			break;

		default:
			break;
		}
	}

}
