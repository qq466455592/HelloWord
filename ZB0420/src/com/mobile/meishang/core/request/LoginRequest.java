package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.loader.LoginLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Login;

public class LoginRequest implements LoaderManager.LoaderCallbacks<Login> {

	private MActivity mActivity;
	private MFragment mFragment;

	public LoginRequest(MActivity activity) {
		this.mActivity = activity;
	}

	public LoginRequest(MFragment fragment) {
		this.mFragment = fragment;
		mActivity = (MActivity) fragment.getActivity();
	}

	@Override
	public Loader<Login> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/login/on");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("account", bundle.getString("account"));
		mHttpRequest.addPostParameter("password", bundle.getString("password"));
		LoginLoader loader = new LoginLoader(mActivity, mHttpRequest);
		if (mFragment == null) {
			loader.setExceptionHandler(mActivity);
		} else {
			loader.setExceptionHandler(mFragment);
		}
		loader.setIdentit(RequestDistribute.LOGIN);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Login> arg0, Login arg1) {
		if (arg1 != null) {
			if (mFragment == null) {
				mActivity.updateUI(RequestDistribute.LOGIN, arg1);
			} else {
				mFragment.updateUI(RequestDistribute.LOGIN, arg1);
			}
		}
	}

	@Override
	public void onLoaderReset(Loader<Login> arg0) {
	}

}
