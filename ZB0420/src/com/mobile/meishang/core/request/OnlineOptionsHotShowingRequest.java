package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.OnlineOptionsHotShowingLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.HotShowing;

public class OnlineOptionsHotShowingRequest implements
		LoaderManager.LoaderCallbacks<HotShowing> {

	private MActivity mActivity;

	public OnlineOptionsHotShowingRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<HotShowing> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/lifeMovie/openMovieList");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		OnlineOptionsHotShowingLoader loader = new OnlineOptionsHotShowingLoader(
				mActivity, mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<HotShowing> arg0, HotShowing arg1) {
		if (arg1 != null) {
			mActivity.updateUI(arg0.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<HotShowing> arg0) {
	}

}
