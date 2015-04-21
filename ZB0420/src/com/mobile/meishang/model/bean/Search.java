package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.utils.FunctionUtil;

/**
 * 
 * @Title:
 * @Description:
 * @Author:Administrator
 * @Since:2013-5-2
 * @Version:
 */
public class Search {

	private String content;

	public Search() {
	}

	public Search(String c) {
		content = c;
	}

	public Search(JSONObject json) throws JSONException {
		FunctionUtil.getJsonStrValue(json, "");
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
