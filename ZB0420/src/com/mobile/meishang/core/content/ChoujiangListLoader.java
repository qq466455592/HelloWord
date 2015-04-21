package com.mobile.meishang.core.content;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;

import com.mobile.meishang.core.loader.HttpRequestLoader;
import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.model.bean.ChoujiangItem;

public class ChoujiangListLoader extends HttpRequestLoader<List<ChoujiangItem>> {

	public ChoujiangListLoader(Context context, ZLNetworkRequest _HttpRequest) {
		super(context, _HttpRequest);
	}

	@Override
	public List<ChoujiangItem> handle(String content) throws ZLNetworkException {
		List<ChoujiangItem> list = new ArrayList<ChoujiangItem>();
		try {
			JSONArray jsonArray = new JSONArray(content);
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				list.add(new ChoujiangItem(jsonArray.getJSONObject(i)));
			}
			return list;
		} catch (JSONException e) {
			throw new ZLNetworkException(ZLNetworkException.ERROR_JSONPARSER);
		}
	}
}
