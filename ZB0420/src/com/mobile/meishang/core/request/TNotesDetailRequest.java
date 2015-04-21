package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.loader.TNotesDetailLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.TravelNotes;

public class TNotesDetailRequest implements
		LoaderManager.LoaderCallbacks<TravelNotes> {

	private MActivity mActivity;

	public TNotesDetailRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<TravelNotes> onCreateLoader(int id, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/travelsNote/BCI/showTravelContent");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		// mHttpRequest.addPostParameter("userid", "10");
		mHttpRequest.addPostParameter("travelid", bundle.getString("travelid"));
		// mHttpRequest.addPostParameter("date", bundle.getString("date"));
		// mHttpRequest.addPostParameter("number", bundle.getString("number"));
		// mHttpRequest.addPostParameter("content",
		// bundle.getString("content"));
		// mHttpRequest.addPostParameter("photo", bundle.getString("photo"));
		TNotesDetailLoader loader = new TNotesDetailLoader(mActivity,
				mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(id);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<TravelNotes> arg0, TravelNotes arg1) {
		if (arg1 != null) {
			mActivity.updateUI(arg0.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<TravelNotes> arg0) {
	}

}
