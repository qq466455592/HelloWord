package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @Title:
 * @Description:
 * @Author:Administrator
 * @Since:2013-5-2
 * @Version:
 */
public class HotShowing extends Head {
	// {
	// "movieList": []
	// }

	private List<HotShowingItem> list;

	public HotShowing() {
	}

	public HotShowing(JSONObject json) throws JSONException {
		super(json);
		JSONObject bodyObject = getJsonObject(json, "body");
		list = new ArrayList<HotShowingItem>();
		JSONArray jsonArray = getJsonArray(bodyObject, "movieList");
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			list.add(new HotShowingItem(jsonArray.getJSONObject(i)));
		}

	}

	public List<HotShowingItem> getList() {
		return list;
	}

}
