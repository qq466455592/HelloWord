package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.content.CertificateListLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.CertificateList;

public class CertificateListRequest implements
		LoaderManager.LoaderCallbacks<CertificateList> {

	// private LeShiHuiActivity mLeShiHuiActivity;
	private MFragment mLeShiHuiFragment;

	// public CertificateListRequest(LeShiHuiActivity leShiHuiActivity) {
	// this.mLeShiHuiActivity = leShiHuiActivity;
	// }

	public CertificateListRequest(MFragment leShiHuiFragment) {
		this.mLeShiHuiFragment = leShiHuiFragment;
	}

	@Override
	public Loader<CertificateList> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication
				.getInstance().getmConfig().urlRootApi);
		urlString.append("?op=code&act=select");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("state", bundle.getString("useState"));
		CertificateListLoader loader = new CertificateListLoader(
				mLeShiHuiFragment.getActivity(), mHttpRequest);
		loader.setExceptionHandler(mLeShiHuiFragment);
		loader.setIdentit(RequestDistribute.CERTIFICATE_LIST);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<CertificateList> arg0,
			CertificateList arg1) {
		if (arg1 != null) {
			mLeShiHuiFragment
					.updateUI(RequestDistribute.CERTIFICATE_LIST, arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<CertificateList> arg0) {
	}

}
