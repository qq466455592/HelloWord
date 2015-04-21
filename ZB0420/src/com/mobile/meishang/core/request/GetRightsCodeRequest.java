package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.GetRightsCodeLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.RightsCode;

public class GetRightsCodeRequest implements
		LoaderManager.LoaderCallbacks<RightsCode> {

	private MActivity mActivity;

	public GetRightsCodeRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<RightsCode> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/limitBuy/BCI/accRight");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("actid", bundle.getString("actid"));
		mHttpRequest.addPostParameter("goodsid", bundle.getString("goodsid"));
		mHttpRequest.addPostParameter("bankCode", "alipay");
		GetRightsCodeLoader loader = new GetRightsCodeLoader(mActivity,
				mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<RightsCode> arg0, RightsCode arg1) {
		if (arg1 != null) {
			mActivity.updateUI(arg0.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<RightsCode> arg0) {
	}

}
