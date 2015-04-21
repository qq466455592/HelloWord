package com.mobile.meishang.core.request;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.content.CategoryLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Category;

public class CategoryListRequest implements
		LoaderManager.LoaderCallbacks<List<Category>> {

	private MActivity mLeShiHuiActivity;
	private MFragment mLeShiHuiFragment;

	public CategoryListRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}

	public CategoryListRequest(MFragment leShiHuiFragment) {
		this.mLeShiHuiFragment = leShiHuiFragment;
		mLeShiHuiActivity = (MActivity) leShiHuiFragment.getActivity();
	}

	@Override
	public Loader<List<Category>> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		urlString.append("?op=category&act=list");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		CategoryLoader loader = new CategoryLoader(mLeShiHuiActivity,
				mHttpRequest);
		if (mLeShiHuiFragment == null) {
			loader.setExceptionHandler(mLeShiHuiActivity);
		} else {
			loader.setExceptionHandler(mLeShiHuiFragment);
		}
		loader.setIdentit(RequestDistribute.CATEGORY);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<List<Category>> arg0, List<Category> arg1) {
		if (arg1 != null) {
			if (mLeShiHuiFragment == null) {
				mLeShiHuiActivity.updateUI(RequestDistribute.CATEGORY, arg1);

			} else {
				mLeShiHuiFragment.updateUI(RequestDistribute.CATEGORY, arg1);
			}
		}
	}

	@Override
	public void onLoaderReset(Loader<List<Category>> arg0) {
	}

}
