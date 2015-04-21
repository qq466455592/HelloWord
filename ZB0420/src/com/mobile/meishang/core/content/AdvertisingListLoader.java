package com.mobile.meishang.core.content;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.mobile.meishang.core.loader.HttpRequestLoader;
import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.model.bean.AdvertisingList;

public class AdvertisingListLoader extends
		HttpRequestLoader<AdvertisingList> {

	public AdvertisingListLoader(Context context, ZLNetworkRequest _HttpRequest) {
		super(context, _HttpRequest);
	}

	@Override
	public AdvertisingList handle(String content) throws ZLNetworkException {

		try {
			JSONObject json = new JSONObject(content);
			AdvertisingList object = new AdvertisingList(json);
			return object;
		} catch (JSONException e) {
			throw new ZLNetworkException(ZLNetworkException.ERROR_JSONPARSER);
		}
	}

}
