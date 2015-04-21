package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SelectCinema extends Head {

	private List<SelectCinimaItem> list;

	public SelectCinema() {
	}

	public SelectCinema(JSONObject json) throws JSONException {
		super(json);
		JSONObject bodyObject = getJsonObject(json, "body");
		list = new ArrayList<SelectCinimaItem>();
		JSONArray jsonArray = getJsonArray(bodyObject, "cinemas");
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			list.add(new SelectCinimaItem(jsonArray.getJSONObject(i)));
		}

	}

	public List<SelectCinimaItem> getList() {
		return list;
	}

}
