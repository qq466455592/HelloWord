package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.utils.FunctionUtil;

public class Category {
	private String id;
	private String name;

	public Category() {
	}

	public Category(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Category(JSONObject json) throws JSONException {
		id = FunctionUtil.getJsonStrValue(json, "cat_id");
		name = FunctionUtil.getJsonStrValue(json, "cat_name");
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
