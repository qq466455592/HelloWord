package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.SelectCinemaListLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.SelectCinema;

public class SelectCinemaListRequest implements
		LoaderManager.LoaderCallbacks<SelectCinema> {

	private MActivity mActivity;

	public SelectCinemaListRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<SelectCinema> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/lifeMovie/openCinemaList");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		SelectCinemaListLoader loader = new SelectCinemaListLoader(mActivity,
				mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<SelectCinema> arg0, SelectCinema arg1) {
		if (arg1 != null) {
			mActivity.updateUI(arg0.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<SelectCinema> arg0) {
	}

}
