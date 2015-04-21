package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.loader.OrdersAllLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.VoucherList;

public class OrdersUnusedRequest implements
		LoaderManager.LoaderCallbacks<VoucherList> {

	private MFragment mFragment;

	public OrdersUnusedRequest(MFragment fragment) {
		this.mFragment = fragment;
	}

	@Override
	public Loader<VoucherList> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/limitBuy/BCI/myCouponList");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("flag", "2");
		OrdersAllLoader loader = new OrdersAllLoader(mFragment.getActivity(),
				mHttpRequest);
		loader.setExceptionHandler(mFragment);
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<VoucherList> arg0, VoucherList arg1) {
		if (arg1 != null) {
			mFragment.updateUI(arg0.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<VoucherList> arg0) {
	}

}
