package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdvertisingList extends Head {
	private long startTime;
	private long endTime;
	private long timeToStart;
	private long timeToEnd;
	private List<Goods> mList;

	public AdvertisingList() {
	}

	public AdvertisingList(JSONObject json) throws JSONException {
		super(json);
		JSONObject jsonObject = getJsonObject(json, "body");
		startTime = getJsonLongtValue(jsonObject, "startTime");
		endTime = getJsonLongtValue(jsonObject, "endTime");
		timeToStart = getJsonLongtValue(jsonObject, "timeToStart");
		timeToEnd = getJsonLongtValue(jsonObject, "timeToEnd");
		mList = new ArrayList<Goods>();
		JSONArray jsonArray = jsonObject.getJSONArray("goods");
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			mList.add(new Goods(jsonArray.getJSONObject(i)));
		}
	}

	public List<Goods> getmList() {
		return mList;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public long getTimeToStart() {
		return timeToStart;
	}

	public long getTimeToEnd() {
		return timeToEnd;
	}

}
