package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.loader.TNotesCreateContentLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.TravelNotesCreateContent;

public class TNotesCreateContentRequest implements
		LoaderManager.LoaderCallbacks<TravelNotesCreateContent> {

	private MActivity mActivity;

	public TNotesCreateContentRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<TravelNotesCreateContent> onCreateLoader(int id, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/travelsNote/BCI/creatTravelContent");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("userid", "10");
		mHttpRequest.addPostParameter("travelid", bundle.getString("travelid"));
		mHttpRequest.addPostParameter("orderNum", bundle.getString("orderNum"));
		mHttpRequest.addPostParameter("content", bundle.getString("content"));
		mHttpRequest.addPostParameter("photo", bundle.getString("photo"));
		TNotesCreateContentLoader loader = new TNotesCreateContentLoader(
				mActivity, mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(id);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<TravelNotesCreateContent> arg0,
			TravelNotesCreateContent arg1) {
		if (arg1 != null) {
			mActivity.updateUI(arg0.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<TravelNotesCreateContent> arg0) {
	}

}
