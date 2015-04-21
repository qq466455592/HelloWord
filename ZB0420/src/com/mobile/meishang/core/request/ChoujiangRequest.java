package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.ChoujiangLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.ChouJiang;

public class ChoujiangRequest implements
		LoaderManager.LoaderCallbacks<ChouJiang> {

	private MActivity mLeShiHuiActivity;

	public ChoujiangRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}

	@Override
	public Loader<ChouJiang> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		urlString.append("?op=prize&act=show");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		ChoujiangLoader loader = new ChoujiangLoader(mLeShiHuiActivity,
				mHttpRequest);
		loader.setExceptionHandler(mLeShiHuiActivity);
		loader.setIdentit(RequestDistribute.CHOUJIANG);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<ChouJiang> arg0, ChouJiang arg1) {
		if (arg1 != null) {
			mLeShiHuiActivity.updateUI(RequestDistribute.CHOUJIANG, arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<ChouJiang> arg0) {
	}

}
