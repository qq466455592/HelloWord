package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.config.Config;
import com.mobile.meishang.config.Constants;
import com.mobile.meishang.core.content.ShopListLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.ShopList;

public class ShopListRequest implements LoaderManager.LoaderCallbacks<ShopList> {

	private MActivity mActivity;

	public ShopListRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<ShopList> onCreateLoader(int arg0, Bundle arg1) {
		Config mConfig = MApplication.getInstance().getmConfig();

		String mineAK = "BFzjFzw35IjB5tM3DPmSUruF";
		String geotable_id = "92079";
		String location = mConfig.getPreferencesVal(Constants.LONGITUDE,
				"121.419073")
				+ ","
				+ mConfig.getPreferencesVal(Constants.LATITUDE, "31.191909");
		String radius = "1000";
		String tags = "店";
		String sortby = "distance:1|price:1&filter=price:200,300";
		sortby = "distance:1";
		String url = "http://api.map.baidu.com/geosearch/v3/nearby?ak="
				+ mineAK;
		url += "&geotable_id=" + geotable_id;
		url += "&location=" + location;
		url += "&radius=" + radius;
		// url += "&tags="+tags;
		url += "&sortby=" + sortby;
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(url);
		ShopListLoader loader = new ShopListLoader(mActivity, mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<ShopList> arg0, ShopList arg1) {
		if (arg1 != null) {
			mActivity.updateUI(arg0.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<ShopList> arg0) {
	}

}
