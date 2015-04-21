package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.AdvertisingListLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.AdvertisingList;

public class AdvertisingListRequest implements
		LoaderManager.LoaderCallbacks<AdvertisingList> {

	private MActivity mActivity;

	public AdvertisingListRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<AdvertisingList> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/limitBuy/busGoodsList");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("actid", bundle.getString("actid"));
		AdvertisingListLoader loader = new AdvertisingListLoader(mActivity,
				mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<AdvertisingList> arg0,
			AdvertisingList arg1) {
		if (arg1 != null) {
			mActivity.updateUI(arg0.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<AdvertisingList> arg0) {
	}

}
