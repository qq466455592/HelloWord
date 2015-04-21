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

public class SearchResultListRequest implements
		LoaderManager.LoaderCallbacks<List<Goods>> {

	private MActivity mLeShiHuiActivity;

	public SearchResultListRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}

	@Override
	public Loader<List<Goods>> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		urlString.append("?op=search");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("keywords", bundle.getString("keywords"));
		GoodsListLoader loader = new GoodsListLoader(mLeShiHuiActivity,
				mHttpRequest);
		loader.setExceptionHandler(mLeShiHuiActivity);
		loader.setIdentit(RequestDistribute.GOODS_LIST);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<List<Goods>> arg0,
			List<Goods> arg1) {
		if (arg1 != null) {
			mLeShiHuiActivity.updateUI(RequestDistribute.GOODS_LIST, arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<Goods>> arg0) {
	}

}
