package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.OnlineOptionsSeatLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.OnlineOptionsSeats;

public class OnlineOptionsSeatRequest implements
		LoaderManager.LoaderCallbacks<OnlineOptionsSeats> {

	private MActivity mActivity;

	public OnlineOptionsSeatRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<OnlineOptionsSeats> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/lifeMovie/opiSeatAndLockSeatInfo");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		// mHttpRequest.addPostParameter("mpid", "571");
		mHttpRequest.addPostParameter("mpid", arg1.getString("mpid"));
		OnlineOptionsSeatLoader loader = new OnlineOptionsSeatLoader(mActivity,
				mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<OnlineOptionsSeats> arg0,
			OnlineOptionsSeats arg1) {
		if (arg1 != null) {
			mActivity.updateUI(arg0.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<OnlineOptionsSeats> arg0) {
	}

}
