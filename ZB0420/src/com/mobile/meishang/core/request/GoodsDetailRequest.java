package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.config.Constants;
import com.mobile.meishang.core.content.GoodsDetailLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Goods;

public class GoodsDetailRequest implements LoaderManager.LoaderCallbacks<Goods> {

	private MActivity mLeShiHuiActivity;

	public GoodsDetailRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}


	@Override
	public Loader<Goods> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		urlString.append("?op=brand&act=show");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("id", bundle.getString("id"));
		mHttpRequest.addPostParameter("lng",
				MApplication.getInstance().getmConfig()
						.getPreferencesVal(Constants.LONGITUDE, "121.418803"));
		mHttpRequest.addPostParameter("lat", MApplication.getInstance()
				.getmConfig()
				.getPreferencesVal(Constants.LATITUDE, "31.192035"));
		GoodsDetailLoader loader = new GoodsDetailLoader(mLeShiHuiActivity,
				mHttpRequest);
		loader.setExceptionHandler(mLeShiHuiActivity);
		loader.setIdentit(RequestDistribute.GOODS_DETAILS);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Goods> arg0, Goods arg1) {
		if (arg1 != null) {
			mLeShiHuiActivity.updateUI(RequestDistribute.GOODS_DETAILS, arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<Goods> arg0) {
	}

}
