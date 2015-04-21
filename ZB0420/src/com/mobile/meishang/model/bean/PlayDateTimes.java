package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlayDateTimes extends Head {

	private String playdate;
	private List<PlayDateTimesItem> list;

	public PlayDateTimes() {
	}

	public PlayDateTimes(JSONObject json) throws JSONException {
		playdate = getJsonStrValue(json, "playdate");
		list = new ArrayList<PlayDateTimesItem>();
		JSONArray jsonArray = getJsonArray(json, "opiList");
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			list.add(new PlayDateTimesItem(jsonArray.getJSONObject(i)));
		}

	}

	public List<PlayDateTimesItem> getList() {
		return list;
	}

	public String getPlaydate() {
		return playdate;
	}

}
