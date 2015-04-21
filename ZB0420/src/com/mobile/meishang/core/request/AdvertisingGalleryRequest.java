package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.content.AdvertisingGalleryLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.AdvertisingGalleryList;

public class AdvertisingGalleryRequest implements
		LoaderManager.LoaderCallbacks<AdvertisingGalleryList> {

	private MFragment mFragment;
	private MActivity mActivity;

	public AdvertisingGalleryRequest(MActivity activity) {
		this.mActivity = activity;
	}

	public AdvertisingGalleryRequest(MFragment fragment) {
		this.mFragment = fragment;
		mActivity = (MActivity) fragment.getActivity();
	}

	@Override
	public Loader<AdvertisingGalleryList> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/limitBuy/actInfoList");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("label", arg1.getString("label"));
		AdvertisingGalleryLoader loader = new AdvertisingGalleryLoader(
				mActivity, mHttpRequest);
		if (mFragment == null) {

			loader.setExceptionHandler(mActivity);
		} else {

			loader.setExceptionHandler(mFragment);
		}
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<AdvertisingGalleryList> arg0,
			AdvertisingGalleryList arg1) {
		if (arg1 != null) {
			if (mFragment == null) {
				mActivity.updateUI(arg0.getId(), arg1);
			} else {
				mFragment.updateUI(arg0.getId(), arg1);
			}

		}
	}

	@Override
	public void onLoaderReset(Loader<AdvertisingGalleryList> arg0) {
	}

}
