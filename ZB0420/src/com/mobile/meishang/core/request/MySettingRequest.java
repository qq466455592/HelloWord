package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.MySettingLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.User;

public class MySettingRequest implements LoaderManager.LoaderCallbacks<User> {

	private MActivity mLeShiHuiActivity;

	public MySettingRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}

	@Override
	public Loader<User> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		urlString.append("?op=zhangusersetting&act=info");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		MySettingLoader loader = new MySettingLoader(mLeShiHuiActivity,
				mHttpRequest);
		loader.setExceptionHandler(mLeShiHuiActivity);
		loader.setIdentit(RequestDistribute.MY_SETTING);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<User> arg0, User arg1) {
		if (arg1 != null) {
			mLeShiHuiActivity.updateUI(RequestDistribute.MY_SETTING, arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<User> arg0) {
	}

}
