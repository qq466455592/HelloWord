package com.mobile.meishang.core.request;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.CombineListLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.CombineListItem;

public class CombineListRequest implements
		LoaderManager.LoaderCallbacks<List<CombineListItem>> {

	private MActivity mLeShiHuiActivity;

	public CombineListRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}

	@Override
	public Loader<List<CombineListItem>> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		urlString.append("?op=brand&act=showbrand");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		CombineListLoader loader = new CombineListLoader(mLeShiHuiActivity,
				mHttpRequest);
		loader.setExceptionHandler(mLeShiHuiActivity);
		loader.setIdentit(RequestDistribute.COMBINE_LIST);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<List<CombineListItem>> arg0,
			List<CombineListItem> arg1) {
		if (arg1 != null) {
			mLeShiHuiActivity.updateUI(RequestDistribute.COMBINE_LIST, arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<CombineListItem>> arg0) {
	}

}
