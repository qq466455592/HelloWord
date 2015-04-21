package com.mobile.meishang.core.request;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.content.GoodsListLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Goods;

public class GoodsListRequest implements
		LoaderManager.LoaderCallbacks<List<Goods>> {

	private MActivity mLeShiHuiActivity;
	private MFragment mLeShiHuiFragment;

	public GoodsListRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}

	public GoodsListRequest(MFragment leShiHuiFragment) {
		this.mLeShiHuiFragment = leShiHuiFragment;
		mLeShiHuiActivity = (MActivity) leShiHuiFragment.getActivity();
	}

	@Override
	public Loader<List<Goods>> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		if ("category".equals(bundle.getString("from"))) {
			urlString.append("?op=goodslist&act=category&id=");
			urlString.append(bundle.getString("id"));
		} else if ("shop".equals(bundle.getString("from"))) {
			urlString.append("?op=brand&act=list&brandid=");
			urlString.append(bundle.getString("id"));
		}
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		GoodsListLoader loader = new GoodsListLoader(mLeShiHuiActivity,
				mHttpRequest);
		if (mLeShiHuiFragment == null) {

			loader.setExceptionHandler(mLeShiHuiActivity);
		} else {

			loader.setExceptionHandler(mLeShiHuiFragment);
		}
		loader.setIdentit(RequestDistribute.GOODS_LIST);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<List<Goods>> arg0,
			List<Goods> arg1) {
		if (arg1 != null) {
			if (mLeShiHuiFragment == null) {
				mLeShiHuiActivity.updateUI(RequestDistribute.GOODS_LIST, arg1);
			} else {
				mLeShiHuiFragment.updateUI(RequestDistribute.GOODS_LIST, arg1);
			}
		}
	}

	@Override
	public void onLoaderReset(Loader<List<Goods>> arg0) {
	}

}
