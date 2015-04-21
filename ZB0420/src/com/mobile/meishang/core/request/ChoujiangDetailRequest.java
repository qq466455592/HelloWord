package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.ChoujiangDetailLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.ChouJiangDetail;

public class ChoujiangDetailRequest implements
		LoaderManager.LoaderCallbacks<ChouJiangDetail> {

	private MActivity mLeShiHuiActivity;

	public ChoujiangDetailRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}

	@Override
	public Loader<ChouJiangDetail> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		urlString.append("?op=prize&act=showperson");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("id", bundle.getString("id"));
		ChoujiangDetailLoader loader = new ChoujiangDetailLoader(
				mLeShiHuiActivity, mHttpRequest);
		loader.setExceptionHandler(mLeShiHuiActivity);
		loader.setIdentit(RequestDistribute.MY_CHOUJIANG_Detail);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<ChouJiangDetail> arg0,
			ChouJiangDetail arg1) {
		if (arg1 != null) {
			mLeShiHuiActivity.updateUI(RequestDistribute.MY_CHOUJIANG_Detail, arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<ChouJiangDetail> arg0) {
	}

}
