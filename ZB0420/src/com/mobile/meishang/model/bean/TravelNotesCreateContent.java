package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class TravelNotesCreateContent extends Head {
	// {"body":{
	// contentid：内容id
	// },"head":{"status":"00","msg":"操作成功"}}

	private String contentid;

	public TravelNotesCreateContent() {

	}

	public TravelNotesCreateContent(JSONObject jsonObject) throws JSONException {
		super(jsonObject);
		JSONObject json = getJsonObject(jsonObject, "body");
		contentid = getJsonStrValue(json, "contentid");

	}

	public String getContentid() {
		return contentid;
	}

}
