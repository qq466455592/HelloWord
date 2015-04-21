package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.loader.HeadLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Head;

public class LogoutRequest implements
		LoaderManager.LoaderCallbacks<Head> {

	private MActivity mLeShiHuiActivity;
	private MFragment mLeShiHuiFragment;

	public LogoutRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}

	public LogoutRequest(MFragment leShiHuiFragment) {
		this.mLeShiHuiFragment = leShiHuiFragment;
		mLeShiHuiActivity = (MActivity) leShiHuiFragment.getActivity();
	}

	@Override
	public Loader<Head> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		urlString.append("?op=user&act=logout");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		HeadLoader loader = new HeadLoader(mLeShiHuiActivity,
				mHttpRequest);
		loader.setExceptionHandler(mLeShiHuiActivity);
		loader.setIdentit(RequestDistribute.LOGOUT);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Head> arg0,
			Head arg1) {
		if (arg1 != null) {
			if (mLeShiHuiFragment == null) {
				mLeShiHuiActivity.updateUI(RequestDistribute.LOGOUT, arg1);
			} else {
				mLeShiHuiFragment.updateUI(RequestDistribute.LOGOUT, arg1);
			}
		}
	}

	@Override
	public void onLoaderReset(Loader<Head> arg0) {
	}

}
