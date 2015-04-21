package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.loader.HeadLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.Head;

public class TNotesUpdateRequest implements
		LoaderManager.LoaderCallbacks<Head> {

	private MActivity mActivity;

	public TNotesUpdateRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<Head> onCreateLoader(int id, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/travelsNote/BCI/updateTravel");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("travelid", bundle.getString("travelid"));
		mHttpRequest.addPostParameter("title", bundle.getString("title"));
		mHttpRequest.addPostParameter("date", bundle.getString("date"));
		HeadLoader loader = new HeadLoader(mActivity,
				mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(id);
		// System.out.println(id + "==" + RequestDistribute.NOTES_CREATE);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Head> arg0,
			Head arg1) {
		if (arg1 != null) {
			mActivity.updateUI(arg0.getId(), arg1);
			// mActivity.updateUI(RequestDistribute.NOTES_CREATE, arg1);
			// System.out.println("id:" + arg0.getId());
		}
	}

	@Override
	public void onLoaderReset(Loader<Head> arg0) {
	}

}
