package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.loader.MyTNotesListLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.TNotesItemList;

public class MyTNotesListRequest implements
		LoaderManager.LoaderCallbacks<TNotesItemList> {

	private MActivity mActivity;

	public MyTNotesListRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<TNotesItemList> onCreateLoader(int id, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/travelsNote/BCI/myTravels");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		// mHttpRequest.addPostParameter("userid", "10");
		MyTNotesListLoader loader = new MyTNotesListLoader(mActivity,
				mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(id);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<TNotesItemList> loader,
			TNotesItemList arg1) {
		if (arg1 != null) {
			mActivity.updateUI(loader.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<TNotesItemList> arg0) {
	}

}
