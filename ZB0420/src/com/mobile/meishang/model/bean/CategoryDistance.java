package com.mobile.meishang.model.bean;


public class CategoryDistance {
	private String id;
	private String name;

	public CategoryDistance() {
	}

	public CategoryDistance(String id, String name) {
		this.id = id;
		this.name = name;
	}

	// public CategoryDistance(JSONObject json) throws JSONException {
	// id = FunctionUtil.getJsonStrValue(json, "cat_id");
	// value = FunctionUtil.getJsonStrValue(json, "cat_name");
	// }

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
