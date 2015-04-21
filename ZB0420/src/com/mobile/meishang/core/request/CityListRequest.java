package com.mobile.meishang.core.request;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.CityListLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.City;

public class CityListRequest implements
		LoaderManager.LoaderCallbacks<List<City>> {

	private MActivity mLeShiHuiActivity;

	public CityListRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}

	@Override
	public Loader<List<City>> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		urlString.append("?op=citylist&act=list");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		CityListLoader loader = new CityListLoader(mLeShiHuiActivity,
				mHttpRequest);
		loader.setExceptionHandler(mLeShiHuiActivity);
		loader.setIdentit(RequestDistribute.CITY_LIST);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<List<City>> arg0, List<City> arg1) {
		if (arg1 != null) {
			mLeShiHuiActivity.updateUI(RequestDistribute.CITY_LIST, arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<City>> arg0) {
	}

}
