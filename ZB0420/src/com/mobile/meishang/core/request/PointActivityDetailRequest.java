package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.PointActivityDetailLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.PointActivityDetail;

public class PointActivityDetailRequest implements
		LoaderManager.LoaderCallbacks<PointActivityDetail> {

	private MActivity mLeShiHuiActivity;

	public PointActivityDetailRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}

	@Override
	public Loader<PointActivityDetail> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		urlString.append("?op=activity&act=show");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("id", bundle.getString("id"));
		PointActivityDetailLoader loader = new PointActivityDetailLoader(
				mLeShiHuiActivity, mHttpRequest);
		loader.setExceptionHandler(mLeShiHuiActivity);
		loader.setIdentit(RequestDistribute.POINT_ACTIVITY_DETAIL);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<PointActivityDetail> arg0,
			PointActivityDetail arg1) {
		if (arg1 != null) {
			mLeShiHuiActivity.updateUI(RequestDistribute.POINT_ACTIVITY_DETAIL,
					arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<PointActivityDetail> arg0) {
	}

}
