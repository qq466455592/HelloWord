package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.AdvertisingGalleryLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.AdvertisingGalleryList;

public class AdvertisingListExpandRequest implements
		LoaderManager.LoaderCallbacks<AdvertisingGalleryList> {

	private MActivity mActivity;

	public AdvertisingListExpandRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<AdvertisingGalleryList> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/limitBuy/actInfoList");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		AdvertisingGalleryLoader loader = new AdvertisingGalleryLoader(mActivity,
				mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<AdvertisingGalleryList> arg0,
			AdvertisingGalleryList arg1) {
		if (arg1 != null) {
			mActivity.updateUI(arg0.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<AdvertisingGalleryList> arg0) {
	}

}
