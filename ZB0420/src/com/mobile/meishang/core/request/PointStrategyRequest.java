package com.mobile.meishang.core.request;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.PointStrategyLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.PointStrategyGroup;

public class PointStrategyRequest implements
		LoaderManager.LoaderCallbacks<List<PointStrategyGroup>> {

	private MActivity mLeShiHuiActivity;

	public PointStrategyRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}

	@Override
	public Loader<List<PointStrategyGroup>> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		urlString.append("?op=guide&act=list");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		PointStrategyLoader loader = new PointStrategyLoader(mLeShiHuiActivity,
				mHttpRequest);
		loader.setExceptionHandler(mLeShiHuiActivity);
		loader.setIdentit(RequestDistribute.POINT_STRATEGY);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<List<PointStrategyGroup>> arg0,
			List<PointStrategyGroup> arg1) {
		if (arg1 != null) {
			mLeShiHuiActivity.updateUI(RequestDistribute.POINT_STRATEGY, arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<PointStrategyGroup>> arg0) {
	}

}
