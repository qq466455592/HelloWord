package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.loader.TNotesCreateLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.TravelNotesCreate;

public class TNotesCreateRequest implements
		LoaderManager.LoaderCallbacks<TravelNotesCreate> {

	private MActivity mActivity;

	public TNotesCreateRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<TravelNotesCreate> onCreateLoader(int id, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/travelsNote/BCI/createTravel");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		TNotesCreateLoader loader = new TNotesCreateLoader(mActivity,
				mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(id);
		// System.out.println(id + "==" + RequestDistribute.NOTES_CREATE);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<TravelNotesCreate> arg0,
			TravelNotesCreate arg1) {
		if (arg1 != null) {
			mActivity.updateUI(arg0.getId(), arg1);
			// mActivity.updateUI(RequestDistribute.NOTES_CREATE, arg1);
			// System.out.println("id:" + arg0.getId());
		}
	}

	@Override
	public void onLoaderReset(Loader<TravelNotesCreate> arg0) {
	}

}
