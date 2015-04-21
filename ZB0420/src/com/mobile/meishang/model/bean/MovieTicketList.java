package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieTicketList extends Head {

	private List<MovieTicketItem> list;

	public MovieTicketList(JSONObject json) throws JSONException {
		super(json);
		JSONObject jsonObject = getJsonObject(json, "body");
		JSONArray jsonArray = getJsonArray(jsonObject, "coupons");
		list = new ArrayList<MovieTicketItem>();
		if (jsonArray != null) {
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				list.add(new MovieTicketItem(jsonArray.getJSONObject(i)));
			}
		}

	}

	public List<MovieTicketItem> getList() {
		return list;
	}

}
