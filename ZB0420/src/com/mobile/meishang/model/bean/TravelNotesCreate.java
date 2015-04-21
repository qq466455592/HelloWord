package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class TravelNotesCreate extends Head {
	// {
	// "body": {
	// "isNew": "1"，0为新建，1为已有草稿
	// "travelsId": 56
	// },
	// "head": {
	// "status": "00",
	// "msg": "操作成功"
	// }
	// }

	private String travelsId;
	private int isNew;

	public TravelNotesCreate() {

	}

	public TravelNotesCreate(JSONObject jsonObject) throws JSONException {
		super(jsonObject);
		JSONObject json = getJsonObject(jsonObject, "body");
		travelsId = getJsonStrValue(json, "travelsId");
		isNew = getJsonIntValue(json, "isNew");

	}

	public String getTravelsId() {
		return travelsId;
	}

	public int getIsNew() {
		return isNew;
	}

}
