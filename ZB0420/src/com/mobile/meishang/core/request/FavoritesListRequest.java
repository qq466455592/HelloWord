package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.FavoritesListLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.FavoritesList;

public class FavoritesListRequest implements
		LoaderManager.LoaderCallbacks<FavoritesList> {

	private MActivity mLeShiHuiActivity;
	private String mPage = "1";

	public FavoritesListRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}

	public FavoritesListRequest(MActivity leShiHuiActivity, String page) {
		this.mLeShiHuiActivity = leShiHuiActivity;
		mPage = page;
	}

	@Override
	public Loader<FavoritesList> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		urlString.append("?op=collect&act=list&page=");
		urlString.append(mPage);
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		FavoritesListLoader loader = new FavoritesListLoader(mLeShiHuiActivity,
				mHttpRequest);
		loader.setExceptionHandler(mLeShiHuiActivity);
		loader.setIdentit(RequestDistribute.FAVORITES_LIST);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<FavoritesList> arg0, FavoritesList arg1) {
		if (arg1 != null) {
			mLeShiHuiActivity.updateUI(RequestDistribute.FAVORITES_LIST, arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<FavoritesList> arg0) {
	}

}
