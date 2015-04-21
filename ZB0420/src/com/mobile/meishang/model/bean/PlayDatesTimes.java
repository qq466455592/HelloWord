package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlayDatesTimes extends Head {

	private List<PlayDateTimes> list;

	public PlayDatesTimes() {
	}

	public PlayDatesTimes(JSONObject json) throws JSONException {
		super(json);
		JSONObject bodyObject = getJsonObject(json, "body");
		list = new ArrayList<PlayDateTimes>();
		JSONArray jsonArray = getJsonArray(bodyObject, "opi");
		if (jsonArray != null) {
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				list.add(new PlayDateTimes(jsonArray.getJSONObject(i)));
			}
		}

	}

	public List<PlayDateTimes> getList() {
		return list;
	}

}
