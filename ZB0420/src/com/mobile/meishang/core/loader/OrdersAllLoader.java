package com.mobile.meishang.core.loader;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.model.bean.VoucherList;

public class OrdersAllLoader extends HttpRequestLoader<VoucherList> {

	public OrdersAllLoader(Context context, ZLNetworkRequest _HttpRequest) {
		super(context, _HttpRequest);
	}

	@Override
	public VoucherList handle(String content) throws ZLNetworkException {
		try {
			JSONObject json = new JSONObject(content);
			VoucherList voucherList = new VoucherList(json);
			return voucherList;
		} catch (JSONException e) {
			throw new ZLNetworkException(ZLNetworkException.ERROR_JSONPARSER);
		}
	}

}
