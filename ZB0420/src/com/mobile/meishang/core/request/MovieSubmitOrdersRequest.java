package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.MovieSubmitOrdersLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;

public class MovieSubmitOrdersRequest implements
		LoaderManager.LoaderCallbacks<Object> {

	private MActivity mActivity;

	public MovieSubmitOrdersRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<Object> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/lifeMovie/addTicketOrder");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		// mHttpRequest.addPostParameter("mpid", "701");
		// mHttpRequest.addPostParameter("seatLabel", "5:5");//1:2,2:2
		mHttpRequest.addPostParameter("mpid", arg1.getString("mpid"));
		mHttpRequest.addPostParameter("seatLabel", arg1.getString("seatLabel"));// 1:2,2:2
		mHttpRequest.addPostParameter("ukey", "123456789");
		mHttpRequest.addPostParameter("mobile", "13776636043");
		MovieSubmitOrdersLoader loader = new MovieSubmitOrdersLoader(mActivity,
				mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Object> arg0, Object arg1) {
		if (arg1 != null) {
			mActivity.updateUI(arg0.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<Object> arg0) {
	}

}
