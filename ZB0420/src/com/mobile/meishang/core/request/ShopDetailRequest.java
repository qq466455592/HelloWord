package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.ShopDetailLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.ShopDetail;

public class ShopDetailRequest implements
		LoaderManager.LoaderCallbacks<ShopDetail> {

	private MActivity mActivity;

	public ShopDetailRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<ShopDetail> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/lifeFood/storeDetail");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		// mHttpRequest.addPostParameter("bcode", arg1.getString("bcode"));
		// mHttpRequest.addPostParameter("merchantid",
		// arg1.getString("merchantid"));
		mHttpRequest.addPostParameter("bcode", "1_1");
		mHttpRequest.addPostParameter("merchantid", "1");
		ShopDetailLoader loader = new ShopDetailLoader(mActivity, mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<ShopDetail> arg0, ShopDetail arg1) {
		if (arg1 != null) {
			mActivity.updateUI(arg0.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<ShopDetail> arg0) {
	}

}
