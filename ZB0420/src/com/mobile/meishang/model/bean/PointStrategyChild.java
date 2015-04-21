package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.utils.FunctionUtil;

public class PointStrategyChild {
	private String title;

	public PointStrategyChild() {

	}

	public PointStrategyChild(String temp) {
		title = temp;
	}

	public PointStrategyChild(JSONObject json) throws JSONException {
		title = FunctionUtil.getJsonStrValue(json, "content");
	}

	public String getTitle() {
		return title;
	}

}
