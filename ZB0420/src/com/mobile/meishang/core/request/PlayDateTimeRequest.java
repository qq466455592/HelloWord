package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.PlayDateTimeLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.PlayDatesTimes;

public class PlayDateTimeRequest implements
		LoaderManager.LoaderCallbacks<PlayDatesTimes> {

	private MActivity mActivity;

	public PlayDateTimeRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<PlayDatesTimes> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/lifeMovie/playdateAndOpiList");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("movieid", "107");
		mHttpRequest.addPostParameter("cinemaid", "476");
		PlayDateTimeLoader loader = new PlayDateTimeLoader(mActivity,
				mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<PlayDatesTimes> arg0, PlayDatesTimes arg1) {
		if (arg1 != null) {
			mActivity.updateUI(arg0.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<PlayDatesTimes> arg0) {
	}

}
