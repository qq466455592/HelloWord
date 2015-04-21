package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.VersionLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Version;

public class VersionRequest implements LoaderManager.LoaderCallbacks<Version> {

	private MActivity mLeShiHuiActivity;

	public VersionRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}

	@Override
	public Loader<Version> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		urlString.append("?op=version");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		VersionLoader loader = new VersionLoader(mLeShiHuiActivity,
				mHttpRequest);
		loader.setExceptionHandler(mLeShiHuiActivity);
		loader.setIdentit(RequestDistribute.VERSION);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Version> arg0, Version arg1) {
		if (arg1 != null) {
			mLeShiHuiActivity.updateUI(RequestDistribute.VERSION, arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<Version> arg0) {
	}

}
