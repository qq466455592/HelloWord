package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdvertisingGalleryList extends Head {
	private List<AdvertisingGallery> list;

	public AdvertisingGalleryList(JSONObject json) throws JSONException {
		super(json);
		JSONObject jsonObject = getJsonObject(json, "body");
		JSONArray jsonArray = getJsonArray(jsonObject, "actInfo");
		int length = jsonArray.length();
		list = new ArrayList<AdvertisingGallery>();
		for (int i = 0; i < length; i++) {
			list.add(new AdvertisingGallery(jsonArray.getJSONObject(i)));
		}
	}

	public List<AdvertisingGallery> getList() {
		return list;
	}

}
