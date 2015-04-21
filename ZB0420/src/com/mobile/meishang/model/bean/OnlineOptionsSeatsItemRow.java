package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OnlineOptionsSeatsItemRow extends Head {
	private List<OnlineOptionsSeatsItem> mColumnList;

	public OnlineOptionsSeatsItemRow() {
	}

	public OnlineOptionsSeatsItemRow(JSONObject json) throws JSONException {
		mColumnList = new ArrayList<OnlineOptionsSeatsItem>();
		JSONArray jsonArray = json.getJSONArray("row");
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			mColumnList.add(new OnlineOptionsSeatsItem(jsonArray
					.getJSONObject(i)));
		}
	}

	public List<OnlineOptionsSeatsItem> getmColumnList() {
		return mColumnList;
	}

}
