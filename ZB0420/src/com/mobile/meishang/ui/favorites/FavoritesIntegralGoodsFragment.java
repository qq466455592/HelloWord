package com.mobile.meishang.ui.favorites;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.mobile.meishang.MFragment;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.FavoritesIntegralGoodsGviewAdapter;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Head;
import com.umeng.analytics.MobclickAgent;

public class FavoritesIntegralGoodsFragment extends MFragment implements
		OnClickListener {
	private GridView gridview;
	private FrameLayout flayout_delete;
	private FavoritesIntegralGoodsGviewAdapter mAdapter;

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
		View view = inflater.inflate(
				R.layout.fragment_favorites_integral_goods, null);
		gridview = (GridView) view.findViewById(R.id.gridview);
		flayout_delete = (FrameLayout) view.findViewById(R.id.flayout_delete);
		flayout_delete.setOnClickListener(this);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mAdapter = new FavoritesIntegralGoodsGviewAdapter(getActivity());
		gridview.setAdapter(mAdapter);
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
	public void updateUI(int identity, Object data) {

		switch (identity) {
		case RequestDistribute.LOGOUT:
			Head requestResponseInfo = (Head) data;

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
		case R.id.flayout_delete:
			List<Integer> checkPositions=mAdapter.getCheckPositions();
			for (int i = 0; i < checkPositions.size(); i++) {
				System.out.println("-----"+checkPositions.get(i));
			}
			showToast("删除");
			break;
		default:
			break;
		}

	}

	public void showDelete() {
		mAdapter.setEdit(true);
		mAdapter.notifyDataSetChanged();
		flayout_delete.setVisibility(View.VISIBLE);
	}

	public void hideDelete() {
		mAdapter.setEdit(false);
		flayout_delete.setVisibility(View.GONE);
	}

}
