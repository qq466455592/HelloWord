package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.loader.MyMovieTicketLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.MovieTicketList;

public class MyMovieTicketRequest implements
		LoaderManager.LoaderCallbacks<MovieTicketList> {

	private MActivity mActivity;

	public MyMovieTicketRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<MovieTicketList> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/main/BCI/myCouponList");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("condition", "2");
		MyMovieTicketLoader loader = new MyMovieTicketLoader(mActivity,
				mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<MovieTicketList> arg0,
			MovieTicketList arg1) {
		if (arg1 != null) {
			mActivity.updateUI(arg0.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<MovieTicketList> arg0) {
	}

}
