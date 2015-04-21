package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.utils.FunctionUtil;

public class Version {
	private String versionid;
	private String info;

	public Version() {
	}

	public Version(JSONObject json) throws JSONException {
		versionid = FunctionUtil.getJsonStrValue(json, "versionid");
		info = FunctionUtil.getJsonStrValue(json, "content");
	}

	public String getVersionid() {
		return versionid;
	}

	public String getInfo() {
		return info;
	}

}
