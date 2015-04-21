package com.mobile.meishang.core.request;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.PointActivityListLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.PointActivity;

public class PointActivityListRequest implements
		LoaderManager.LoaderCallbacks<List<PointActivity>> {

	private MActivity mLeShiHuiActivity;

	public PointActivityListRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}

	@Override
	public Loader<List<PointActivity>> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		urlString.append("?op=activity&act=list");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		PointActivityListLoader loader = new PointActivityListLoader(
				mLeShiHuiActivity, mHttpRequest);
		loader.setExceptionHandler(mLeShiHuiActivity);
		loader.setIdentit(RequestDistribute.POINT_ACTIVITY);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<List<PointActivity>> arg0,
			List<PointActivity> arg1) {
		if (arg1 != null) {
			mLeShiHuiActivity.updateUI(RequestDistribute.POINT_ACTIVITY, arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<PointActivity>> arg0) {// 返回时调用
	}

}
