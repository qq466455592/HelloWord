package com.mobile.meishang.core.request;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.ChoujiangListLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.ChoujiangItem;

public class ChoujiangListRequest implements
		LoaderManager.LoaderCallbacks<List<ChoujiangItem>> {

	private MActivity mLeShiHuiActivity;

	public ChoujiangListRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}

	@Override
	public Loader<List<ChoujiangItem>> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		urlString.append("?op=prize&act=showlist");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		ChoujiangListLoader loader = new ChoujiangListLoader(mLeShiHuiActivity,
				mHttpRequest);
		loader.setExceptionHandler(mLeShiHuiActivity);
		loader.setIdentit(RequestDistribute.MY_CHOUJIANG_LIST);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<List<ChoujiangItem>> arg0, List<ChoujiangItem> arg1) {
		if (arg1 != null) {
			mLeShiHuiActivity.updateUI(RequestDistribute.MY_CHOUJIANG_LIST, arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<ChoujiangItem>> arg0) {
	}

}
