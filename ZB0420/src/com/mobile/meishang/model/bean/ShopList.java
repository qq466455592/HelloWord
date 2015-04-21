package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShopList extends Head {
	// {
	// "status": 0,
	// "total": 4,
	// "size": 4,
	// "contents":[]
	// }
	// status 状态码 int32 0表示成功，其他值详见状态码说明
	// size 分页参数，当前页返回数量 int32
	// total 分页参数，所有召回数量 int32

	private int status;
	private int total;
	private int size;
	private List<Shop> mList;

	public ShopList() {
	}

	public ShopList(JSONObject json) throws JSONException {
		status = getJsonIntValue(json, "status");
		total = getJsonIntValue(json, "total");
		size = getJsonIntValue(json, "size");
		JSONArray jsonArray = getJsonArray(json, "contents");
		mList = new ArrayList<Shop>();
		if (jsonArray != null) {
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				mList.add(new Shop(jsonArray.getJSONObject(i)));
			}
		}
	}

	public int getStatus() {
		return status;
	}

	public int getTotal() {
		return total;
	}

	public int getSize() {
		return size;
	}

	public List<Shop> getmList() {
		return mList;
	}

}
