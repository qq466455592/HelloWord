package com.mobile.meishang.model.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Head {
	// {
	// "head": {
	// "status": "00",
	// "msg": "操作成功"
	// },
	// "body": {}
	// }
	private String code = "";
	private String message = "";

	public Head() {

	}

	public Head(JSONObject jsonObject) throws JSONException {
		JSONObject json = getJsonObject(jsonObject, "head");
		if (json != null) {
			code = getJsonStrValue(json, "status");
			message = getJsonStrValue(json, "msg");
		}
	}

	public boolean isSuccess() {
		return "00".equals(getCode());
	}

	public String getCode() {
		return code;
	}

	public String getCodeMessage() {
		return message;
	}

	public String getJsonStrValue(JSONObject json, String name)
			throws JSONException {
		if (json != null && json.has(name)) {
			return json.getString(name);
		}
		return "";
	}

	public int getJsonIntValue(JSONObject json, String name)
			throws JSONException {
		if (json != null && json.has(name)) {
			return json.getInt(name);
		}
		return -1;
	}

	public long getJsonLongtValue(JSONObject json, String name)
			throws JSONException {
		if (json != null && json.has(name)) {
			return json.getLong(name);
		}
		return -1;
	}

	public JSONObject getJsonObject(JSONObject json, String name)
			throws JSONException {
		if (json != null && json.has(name)) {
			return json.getJSONObject(name);
		}
		return null;
	}

	public JSONArray getJsonArray(JSONObject jsonObject, String name)
			throws JSONException {
		if (jsonObject != null && jsonObject.has(name)) {
			return jsonObject.getJSONArray(name);
		}
		return null;
	}
}
