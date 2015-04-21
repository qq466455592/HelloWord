package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.utils.FunctionUtil;

public class PointStrategyGroup {
	private String id;
	private String title;
	private List<PointStrategyChild> list = new ArrayList<PointStrategyChild>();

	public PointStrategyGroup() {

	}

	public PointStrategyGroup(JSONObject json) throws JSONException {
		id = FunctionUtil.getJsonStrValue(json, "id");
		title = FunctionUtil.getJsonStrValue(json, "title");
		if (json.has("content")) {
			JSONArray jsonArray = json.getJSONArray("content");
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				list.add(new PointStrategyChild(jsonArray.getJSONObject(i)));
			}
		}
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<PointStrategyChild> getList() {
		return list;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setList(List<PointStrategyChild> list) {
		this.list = list;
	}

}
