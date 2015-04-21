package com.mobile.meishang.core.content;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;

import com.mobile.meishang.core.loader.HttpRequestLoader;
import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.model.bean.City;

public class CityListLoader extends HttpRequestLoader<List<City>> {

	public CityListLoader(Context context, ZLNetworkRequest _HttpRequest) {
		super(context, _HttpRequest);
	}

	@Override
	public List<City> handle(String content) throws ZLNetworkException {
		List<City> shops = new ArrayList<City>();
		try {
			JSONArray jsonArray = new JSONArray(content);
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				shops.add(new City(jsonArray.getJSONObject(i)));
			}
			return shops;
		} catch (JSONException e) {
			throw new ZLNetworkException(ZLNetworkException.ERROR_JSONPARSER);
		}
	}

}
