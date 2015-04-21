package com.mobile.meishang.core.content;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.mobile.meishang.core.loader.HttpRequestLoader;
import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.model.bean.RightsCode;

public class GetRightsCodeLoader extends
		HttpRequestLoader<RightsCode> {

	public GetRightsCodeLoader(Context context, ZLNetworkRequest _HttpRequest) {
		super(context, _HttpRequest);
	}

	@Override
	public RightsCode handle(String content) throws ZLNetworkException {
		try {
			JSONObject json = new JSONObject(content);
			RightsCode object = new RightsCode(json);
			return object;
		} catch (JSONException e) {
			throw new ZLNetworkException(ZLNetworkException.ERROR_JSONPARSER);
		}
	}

}
